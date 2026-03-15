package academy.devdojo.repository;

import academy.devdojo.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserData {
    private final List<User> users = new ArrayList<>();

    {
        User eren = User.builder().id(1L).firstName("Eren").lastName("Yeager").email("eren.yeager@gmail.com").build();
        User naruto = User.builder().id(2L).firstName("Naruto").lastName("Uzumaki").email("naruto.uzumaki@gmail.com").build();
        User boruto = User.builder().id(3L).firstName("Boruto").lastName("Uzumaki").email("boruto.uzumaki@gmail.com").build();
        User shinra = User.builder().id(4L).firstName("Shinra").lastName("Kusakabe").email("shinra.kusakabe@gmail.com").build();
        User edward = User.builder().id(5L).firstName("Edward").lastName("Elric").email("edward.elric@gmail.com").build();
        users.addAll(List.of(eren, naruto, boruto, shinra, edward));
    }

    public List<User> getUsers(){
        return users;
    }
}
