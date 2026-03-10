package academy.devdojo.config;

import external.dependency.Connection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ConnectionConfiguration {

    @Bean
    @Primary
    public Connection connectionMySQL() {
        return new Connection("localhost", "rootMySQL", "root");
    }

    @Bean(name = "connectionMongoDB")
    public Connection connectionMongo() {
        return new Connection("localhost", "rootMongo", "root");
    }
}
