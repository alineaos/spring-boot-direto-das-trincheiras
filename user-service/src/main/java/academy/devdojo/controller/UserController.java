package academy.devdojo.controller;

import academy.devdojo.domain.User;
import academy.devdojo.mapper.UserMapper;
import academy.devdojo.request.UserPostRequest;
import academy.devdojo.request.UserPutRequest;
import academy.devdojo.response.UserGetResponse;
import academy.devdojo.response.UserPostResponse;
import academy.devdojo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserMapper mapper;
    private final UserService service;

    @GetMapping
    public ResponseEntity<List<UserGetResponse>> findAll(@RequestParam(required = false) String firstName){
        log.debug("Request received to list all user, param name '{}'", firstName);

        List<User> users = service.findAll(firstName);
        List<UserGetResponse> userGetResponseList = mapper.toUserGetResponseList(users);

        return ResponseEntity.ok(userGetResponseList);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserGetResponse> findById(@PathVariable Long id){
        log.debug("Request to find profile by id: {}", id);

        User user = service.findByIdOrThrowNotFound(id);
        UserGetResponse userGetResponse = mapper.toUserGetResponse(user);
        return ResponseEntity.ok(userGetResponse);
    }

    @PostMapping
    public ResponseEntity<UserPostResponse> save(@RequestBody @Valid UserPostRequest userPostRequest){
        User userToSave = mapper.toUser(userPostRequest);
        User userSaved = service.save(userToSave);
        UserPostResponse userPostResponse = mapper.toUserPostResponse(userSaved);
        return ResponseEntity.status(HttpStatus.CREATED).body(userPostResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        log.debug("Request to delete user by id: {}", id);

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid UserPutRequest userPutRequest){
        log.debug("Request to update user {}", userPutRequest);

        User userToUpdate = mapper.toUser(userPutRequest);

        service.update(userToUpdate);

        return ResponseEntity.noContent().build();
    }
}
