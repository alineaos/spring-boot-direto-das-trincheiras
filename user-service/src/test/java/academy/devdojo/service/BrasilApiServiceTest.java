package academy.devdojo.service;

import academy.devdojo.commons.CepUtils;
import academy.devdojo.config.BrasilApiConfigurationProperties;
import academy.devdojo.config.RestClientConfiguration;
import academy.devdojo.exception.NotFoundException;
import academy.devdojo.response.CepErrorResponse;
import academy.devdojo.response.CepGetResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.RequestMatcher;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.DefaultResponseCreator;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestClient;

@RestClientTest({BrasilApiService.class,
        RestClientConfiguration.class,
        BrasilApiConfigurationProperties.class,
        ObjectMapper.class,
        CepUtils.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BrasilApiServiceTest {
    @Autowired
    private BrasilApiService service;
    @Autowired
    @Qualifier("brasilApiClient")
    private RestClient.Builder brasilApiClientBuilder;
    @Autowired
    private MockRestServiceServer server;
    @Autowired
    private BrasilApiConfigurationProperties properties;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private CepUtils cepUtils;

    @AfterEach
    void reset(){
        server.reset();
    }

    @Test
    @Order(1)
    @DisplayName("findCep returns CepGetResponse when successful")
    void findCep_ReturnsCepGetResponse_WhenSuccessful() throws JsonProcessingException {
        server = MockRestServiceServer.bindTo(brasilApiClientBuilder).build();

        String cep = "00000000";
        CepGetResponse cepGetResponse = cepUtils.newCepGetResponse();
        String jsonResponse = mapper.writeValueAsString(cepGetResponse);

        RequestMatcher requestTo = MockRestRequestMatchers.requestToUriTemplate(properties.baseUrl() + properties.cepUri(), cep);
        DefaultResponseCreator withSuccess = MockRestResponseCreators.withSuccess(jsonResponse, MediaType.APPLICATION_JSON);
        server.expect(requestTo).andRespond(withSuccess);

        Assertions.assertThat(service.findCep(cep))
                .isNotNull()
                .isEqualTo(cepGetResponse);
    }

    @Test
    @Order(2)
    @DisplayName("findCep returns CepErrorResponse when unsuccessful")
    void findCep_ReturnsCepErrorResponse_WhenUnsuccessful() throws JsonProcessingException {
        server = MockRestServiceServer.bindTo(brasilApiClientBuilder).build();

        String cep = "40400000";
        CepErrorResponse cepErrorResponse = cepUtils.newCepErrorResponse();
        String jsonResponse = mapper.writeValueAsString(cepErrorResponse);
        String expectedErrorMessage = """
                404 NOT_FOUND "CepErrorResponse[name=CepPromiseError, message=Todos os serviços de CEP retornaram erro., type=service_error, errors=[CepInnerErrorResponse[name=ServiceError, message=CEP INVÁLIDO, service=correios]]]"
                """.trim();
        RequestMatcher requestTo = MockRestRequestMatchers.requestToUriTemplate(properties.baseUrl() + properties.cepUri(), cep);
        DefaultResponseCreator withSuccess = MockRestResponseCreators.withResourceNotFound().body(jsonResponse);
        server.expect(requestTo).andRespond(withSuccess);

        Assertions.assertThatException()
                .isThrownBy(() -> service.findCep(cep))
                .withMessage(expectedErrorMessage)
                .isInstanceOf(NotFoundException.class);
    }
}
