package academy.devdojo.commons;

import academy.devdojo.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserUtils {

    public List<User> newUserList() {

        User yuuji = User.builder().id(1L).firstName("Yuuji").lastName("Itadori").email("yuuji.itadori@gmail.com").build();
        User izuku = User.builder().id(2L).firstName("Izuku").lastName("Midoriya").email("izuku.midoriya@gmail.com").build();
        User tanjirou = User.builder().id(3L).firstName("Tanjirou").lastName("Kamado").email("tanjirou.kamado@gmail.com").build();
        User chihiro = User.builder().id(4L).firstName("Chihiro").lastName("Rokuhira").email("chihiro.rokuhira@gmail.com").build();
        User shoyo = User.builder().id(5L).firstName("Shoyo").lastName("Hinata").email("shoyo.hinata@gmail.com").build();
        return new ArrayList<>(List.of(yuuji, izuku, tanjirou, chihiro, shoyo));

    }

    public User newUserToSave() {
        return User.builder().firstName("Rin").lastName("Okumura").email("rin.okumura@gmail.com").build();
    }

    public User newUserSaved() {
        return User.builder().id(99L).firstName("Rin").lastName("Okumura").email("rin.okumura@gmail.com").build();
    }
}
