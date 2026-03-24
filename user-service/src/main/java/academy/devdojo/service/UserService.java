package academy.devdojo.service;

import academy.devdojo.domain.User;
import academy.devdojo.exception.EmailAlreadyExistsException;
import academy.devdojo.exception.NotFoundException;
import academy.devdojo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public List<User> findAll(String firstName){
        return firstName == null ? repository.findAll() : repository.findByFirstNameIgnoreCase(firstName);
    }

    public User findByIdOrThrowNotFound(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not Found"));
    }

    @Transactional
    public User save(User user){
        assertEmailDoesNotExist(user.getEmail());
        User userSaved = repository.save(user);
        if (true){
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
        return userSaved;
    }

    public void delete(Long id){
        User userToDelete = findByIdOrThrowNotFound(id);
        repository.delete(userToDelete);
    }

    public void update(User userToUpdate){
        assertUserExists(userToUpdate.getId());
        assertEmailDoesNotExist(userToUpdate.getEmail(), userToUpdate.getId());
        repository.save(userToUpdate);
    }

    public void assertUserExists(Long id){
        findByIdOrThrowNotFound(id);
    }

    public void assertEmailDoesNotExist(String email){
        repository.findByEmail(email).ifPresent(this::throwEmailExistsException);
    }

    public void assertEmailDoesNotExist(String email, Long id){
        repository.findByEmailAndIdNot(email, id).ifPresent(this::throwEmailExistsException);
    }

    private void throwEmailExistsException(User user){
        throw new EmailAlreadyExistsException("E-mail '%s' already exists".formatted(user.getEmail()));
    }
}
