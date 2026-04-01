package academy.devdojo.controller;

import academy.devdojo.domain.User;
import academy.devdojo.domain.UserProfile;
import academy.devdojo.mapper.UserProfileMapper;
import academy.devdojo.response.UserProfileGetResponse;
import academy.devdojo.response.UserProfileUserGetResponse;
import academy.devdojo.service.UserProfileService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/user-profiles")
@Slf4j
@RequiredArgsConstructor
@SecurityRequirement(name = "basicAuth")
public class UserProfileController {

  private final UserProfileService service;
  private final UserProfileMapper mapper;

  @GetMapping
  public ResponseEntity<List<UserProfileGetResponse>> findAll() {
    log.debug("Request received to list all user profiles");

    List<UserProfile> usersProfiles = service.findAll();
    List<UserProfileGetResponse> userProfileGetResponses = mapper.toUserProfileGetResponseList(usersProfiles);

    return ResponseEntity.ok(userProfileGetResponses);
  }

  @GetMapping("profiles/{id}/users")
  public ResponseEntity<List<UserProfileUserGetResponse>> findAll(@PathVariable Long id) {
    log.debug("Request to received to list all users by profile id: {}", id);

    List<User> users = service.findAllUserByProfileId(id);
    List<UserProfileUserGetResponse> userProfileUserGetResponses = mapper.toUserProfileUserGetResponseList(users);

    return ResponseEntity.ok(userProfileUserGetResponses);
  }
}
