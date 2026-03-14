package academy.devdojo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@RequiredArgsConstructor
public class ConnectionBeanConfiguration {
    private final ConnectionConfigurationProperties connectionProperties;

//    @Value("${database.url}")
//    private String url;
//    @Value("${database.username}")
//    private String username;
//    @Value("${database.password}")
//    private String password;

    @Bean
    //@Profile("mysql")
    public Connection connectionMySql() {
        return new Connection(connectionProperties.url(),
                connectionProperties.username(),
                connectionProperties.password());
    }

    //@Primary
    @Bean(name = "connectionMongoDB")
    @Profile("mongo")
    public Connection connectionMongo() {
        return new Connection("localhost", "rootMongo", "root");
    }
}
