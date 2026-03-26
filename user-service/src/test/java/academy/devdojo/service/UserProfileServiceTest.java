package academy.devdojo.service;

import academy.devdojo.commons.ProfileUtils;
import academy.devdojo.commons.UserProfileUtils;
import academy.devdojo.commons.UserUtils;
import academy.devdojo.domain.User;
import academy.devdojo.domain.UserProfile;
import academy.devdojo.repository.UserProfileRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserProfileServiceTest {
    @InjectMocks
    private UserProfileService service;
    @Mock
    private UserProfileRepository repository;
    private List<UserProfile> userProfileList;
    @InjectMocks
    private UserProfileUtils userProfileUtils;
    @Spy
    private UserUtils userUtils;
    @Spy
    private ProfileUtils profileUtils;

    @BeforeEach
    void init() {
        userProfileList = userProfileUtils.newUserProfileList();
    }

    @Test
    @DisplayName("findAll() returns a list with all user profiles ")
    @Order(1)
    void findAll_ReturnsAllUserProfiles_WhenSuccessful() {
        BDDMockito.when(repository.findAll()).thenReturn(userProfileList);

        List<UserProfile> userProfiles = service.findAll();
        Assertions.assertThat(userProfiles).isNotNull().hasSameElementsAs(userProfileList);

        userProfiles.forEach(userProfile -> Assertions.assertThat(userProfile).hasNoNullFieldsOrProperties());
    }

    @Test
    @DisplayName("findAllUsersByProfileId() returns a list of users for a given profile id")
    @Order(2)
    void findAllUsersByProfileId_ReturnsAllUsersForGivenProfile_WhenSuccessful() {
        Long profileId = 99L;
        List<User> usersByProfile = this.userProfileList.stream()
                .filter(userProfile -> userProfile.getProfile().getId().equals(profileId))
                .map(UserProfile::getUser)
                .toList();

        BDDMockito.when(repository.findAllUsersByProfileId(profileId)).thenReturn(usersByProfile);

        List<User> users = service.findAllUserByProfileId(profileId);

        Assertions.assertThat(users).hasSize(1)
                .doesNotContainNull()
                .hasSameElementsAs(usersByProfile);

        users.forEach(user -> Assertions.assertThat(user).hasNoNullFieldsOrProperties());
    }
}