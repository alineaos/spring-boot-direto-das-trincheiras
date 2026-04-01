package academy.devdojo.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Log4j2
public class SecurityConfig {

  private static final String[] WHITE_LIST = {"/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**", "/csrf"};

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(AbstractHttpConfigurer::disable) // Desativa o csrf
        //.csrf(csrf -> csrf.csrfTokenRepository(new CookieCsrfTokenRepository()) //Cria um novo token
        //.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) //Permite que o JavaScript acess o Token
        // .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler()))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(WHITE_LIST).permitAll()
            .requestMatchers(HttpMethod.POST, "v1/users").permitAll()
            .requestMatchers(HttpMethod.GET, "v1/users").hasAuthority("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "v1/users/*").hasAuthority("ADMIN")
            .anyRequest()
            .authenticated())
        .httpBasic(Customizer.withDefaults())
        .build();
  }

}

