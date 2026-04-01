package academy.devdojo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

// Classe utilizada para usar as configurações no application.yml
@ConfigurationProperties(prefix = "brasil-api")
public record BrasilApiConfigurationProperties(String baseUrl, String cepUri) {

}
