package academy.devdojo.service;

import academy.devdojo.commons.ProfileUtils;
import academy.devdojo.domain.Profile;
import academy.devdojo.repository.ProfileRepository;
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
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProfileServiceTest {
    @InjectMocks
    private ProfileService service;
    @Mock
    private ProfileRepository repository;
    private List<Profile> profileList;
    @InjectMocks
    private ProfileUtils profileUtils;

    @BeforeEach
    void init(){
        profileList = profileUtils.newProfileList();
    }

    @Test
    @DisplayName("findAll returns a list with all profiles")
    @Order(1)
    void findAll_ReturnsAllProfiles_WhenSuccessful(){
        BDDMockito.when(repository.findAll()).thenReturn(profileList);

        List<Profile> profiles = service.findAll();
        Assertions.assertThat(profiles).isNotNull().hasSize(profileList.size());
    }

    @Test
    @DisplayName("save creates a profile")
    @Order(2)
    void save_CreatesProfile_WhenSuccessful() {
        Profile profileToSave = profileUtils.newProfileToSave();
        Profile profileSaved = profileUtils.newProfileSaved();

        BDDMockito.when(repository.save(profileToSave)).thenReturn(profileSaved);

        Profile savedProfile = service.save(profileToSave);

        Assertions.assertThat(savedProfile).isEqualTo(profileSaved).hasNoNullFieldsOrProperties();
    }
}
