package academy.devdojo.repository;

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
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserProfileRepositoryTest  extends IntegrationTestConfig {
    @Autowired
    private UserProfileRepository repository;

    @Test
    @DisplayName("findAllUsersByProfileId returns a list with all users by profile id")
    @Order(1)
    @Sql("/sql/init_user_profile_2_users_1_profile.sql")
    void findAllUsersByProfileId_ReturnsAllUsers_WhenSuccesful() {
        Long profileId = 1L;
        List<User> users = repository.findAllUsersByProfileId(profileId);

        Assertions.assertThat(users).isNotEmpty()
                .hasSize(2)
                .doesNotContainNull();

        users.forEach(user -> Assertions.assertThat(user).hasNoNullFieldsOrProperties());
    }
}
