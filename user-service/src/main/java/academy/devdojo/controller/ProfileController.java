package academy.devdojo.controller;

import academy.devdojo.domain.Profile;
import academy.devdojo.mapper.ProfileMapper;
import academy.devdojo.request.ProfilePostRequest;
import academy.devdojo.response.ProfileGetResponse;
import academy.devdojo.response.ProfilePostResponse;
import academy.devdojo.service.ProfileService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/profiles")
@Slf4j
@RequiredArgsConstructor
@SecurityRequirement(name = "basicAuth")
public class ProfileController {

  private final ProfileService service;
  private final ProfileMapper mapper;

  @GetMapping
  public ResponseEntity<List<ProfileGetResponse>> findAll() {
    log.debug("Request received to list all profiles");
    List<Profile> profiles = service.findAll();
    List<ProfileGetResponse> profileGetResponseList = mapper.toProfileGetResponseList(profiles);

    return ResponseEntity.ok(profileGetResponseList);
  }

  @PostMapping
  public ResponseEntity<ProfilePostResponse> save(@RequestBody @Valid ProfilePostRequest profilePostRequest) {
    Profile profileToSave = mapper.toProfile(profilePostRequest);
    Profile savedProfile = service.save(profileToSave);
    ProfilePostResponse profilePostResponse = mapper.toProfilePostResponse(savedProfile);

    return ResponseEntity.status(HttpStatus.CREATED).body(profilePostResponse);
  }
}
