package academy.devdojo;

import academy.devdojo.config.ConnectionConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties(ConnectionConfigurationProperties.class)
//@ComponentScan(basePackages = {"outside.devdojo", "academy.devdojo"}) | O App escaneia tanto a pasta interna quanto a externa.
public class AnimeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnimeServiceApplication.class, args);
	}

}
