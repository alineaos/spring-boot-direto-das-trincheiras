package academy.devdojo.service;

import academy.devdojo.domain.User;
import academy.devdojo.exception.EmailAlreadyExistsException;
import academy.devdojo.exception.NotFoundException;
import academy.devdojo.mapper.UserMapper;
import academy.devdojo.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository repository;
  private final UserMapper mapper;

  public List<User> findAll(String firstName) {
    return firstName == null ? repository.findAll() : repository.findByFirstNameIgnoreCase(firstName);
  }

  public User findByIdOrThrowNotFound(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new NotFoundException("User not Found"));
  }

  //@Transactional
  public User save(User user) {
    assertEmailDoesNotExist(user.getEmail());
    return repository.save(user);
  }

  public void delete(Long id) {
    User userToDelete = findByIdOrThrowNotFound(id);
    repository.delete(userToDelete);
  }

  public void update(User userToUpdate) {
    User savedUser = findByIdOrThrowNotFound(userToUpdate.getId());
    assertEmailDoesNotExist(userToUpdate.getEmail(), userToUpdate.getId());
    User userWithPasswordAndRoles = mapper.toUserWithPasswordAndRoles(userToUpdate, userToUpdate.getPassword(), savedUser);
    //userToUpdate.setRoles(savedUser.getRoles());
    //if(userToUpdate.getPassword() == null){
    //userToUpdate.setPassword(savedUser.getPassword());
    //}
    repository.save(userWithPasswordAndRoles);
  }

  public void assertUserExists(Long id) {
    findByIdOrThrowNotFound(id);
  }

  public void assertEmailDoesNotExist(String email) {
    repository.findByEmail(email).ifPresent(this::throwEmailExistsException);
  }

  public void assertEmailDoesNotExist(String email, Long id) {
    repository.findByEmailAndIdNot(email, id).ifPresent(this::throwEmailExistsException);
  }

  private void throwEmailExistsException(User user) {
    throw new EmailAlreadyExistsException("E-mail '%s' already exists".formatted(user.getEmail()));
  }
}
