package academy.devdojo.repository;

import academy.devdojo.commons.UserUtils;
import academy.devdojo.config.IntegrationTestConfig;
import academy.devdojo.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//Configura um database de testes a ser utilizado.
//@Transactional(propagation = Propagation.NOT_SUPPORTED) //O que foi realizado no teste anterior, será reaproveitado no próximo teste. Não recomendado.
@Import(UserUtils.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepositoryTest  extends IntegrationTestConfig {
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserUtils userUtils;


    @Test
    @DisplayName("save creates a user")
    @Order(1)
    void save_CreatesUser_WhenSuccessful() {
        User userToSave = userUtils.newUserToSave();

        User savedUser = repository.save(userToSave);

        Assertions.assertThat(savedUser).hasNoNullFieldsOrProperties();
        Assertions.assertThat(savedUser.getId()).isNotNull().isPositive();
    }

    @Test
    @DisplayName("findAll returns a list with all users when name is null")
    @Order(2)
    @Sql("/sql/init_one_user.sql")
    void findAll_ReturnsAllUsers_WhenNameIsNull() {
        List<User> users = repository.findAll();
        Assertions.assertThat(users).isNotEmpty();

    }
}
