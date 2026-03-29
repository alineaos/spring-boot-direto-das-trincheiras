package academy.devdojo.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.util.DefaultUriBuilderFactory;

import static academy.devdojo.commons.Constants.BASE_URI;
import static academy.devdojo.commons.Constants.REGULAR_USERNAME;
import static academy.devdojo.commons.Constants.PASSWORD;

@TestConfiguration
@Lazy
public class TestRestTemplateConfig {
    @LocalServerPort
    int port;

    @Bean
    public TestRestTemplate testRestTemplate() {
        DefaultUriBuilderFactory uri = new DefaultUriBuilderFactory(BASE_URI + port);
        TestRestTemplate testRestTemplate = new TestRestTemplate()
                .withBasicAuth(REGULAR_USERNAME, PASSWORD); //Add your credentials here
        testRestTemplate.setUriTemplateHandler(uri);
        return testRestTemplate;
    }
}
