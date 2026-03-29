package academy.devdojo.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.util.DefaultUriBuilderFactory;

@TestConfiguration
@Lazy
public class TestRestTemplateConfig {
    @LocalServerPort
    int port;

    @Bean
    public TestRestTemplate testRestTemplate() {
        DefaultUriBuilderFactory uri = new DefaultUriBuilderFactory("http://localhost:" + port);
        TestRestTemplate testRestTemplate = new TestRestTemplate()
                .withBasicAuth("luffy@strawhat", "test"); //Add your credentials here
        testRestTemplate.setUriTemplateHandler(uri);
        return testRestTemplate;
    }
}
