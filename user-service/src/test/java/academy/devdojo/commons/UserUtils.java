package academy.devdojo.commons;

import academy.devdojo.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserUtils {

    public List<User> newUserList() {

        User yuuji = User.builder()
                .id(1L)
                .firstName("Yuuji")
                .lastName("Itadori")
                .email("yuuji.itadori@gmail.com")
                .roles("USER")
                .password("{bcrypt}$2a$10$iH/HtJTJWkT0hvoW4Zmy7eArfljCXKauBoek0dxmdkybM926ZF4Ie")
                .build();
        User izuku = User.builder()
                .id(2L)
                .firstName("Izuku")
                .lastName("Midoriya")
                .email("izuku.midoriya@gmail.com")
                .roles("USER")
                .password("{bcrypt}$2a$10$iH/HtJTJWkT0hvoW4Zmy7eArfljCXKauBoek0dxmdkybM926ZF4Ie")
                .build();
        User tanjirou = User.builder()
                .id(3L).firstName("Tanjirou")
                .lastName("Kamado")
                .email("tanjirou.kamado@gmail.com")
                .roles("USER")
                .password("{bcrypt}$2a$10$iH/HtJTJWkT0hvoW4Zmy7eArfljCXKauBoek0dxmdkybM926ZF4Ie")
                .build();
        return new ArrayList<>(List.of(yuuji, izuku, tanjirou));

    }

    public User newUserToSave() {
        return User.builder()
                .firstName("Rin")
                .lastName("Okumura")
                .email("rin.okumura@gmail.com")
                .roles("USER")
                .password("{bcrypt}$2a$10$iH/HtJTJWkT0hvoW4Zmy7eArfljCXKauBoek0dxmdkybM926ZF4Ie")
                .build();
    }

    public User newUserSaved() {
        return User.builder()
                .id(99L)
                .firstName("Rin")
                .lastName("Okumura")
                .email("rin.okumura@gmail.com")
                .roles("USER")
                .password("{bcrypt}$2a$10$iH/HtJTJWkT0hvoW4Zmy7eArfljCXKauBoek0dxmdkybM926ZF4Ie")
                .build();
    }
}
