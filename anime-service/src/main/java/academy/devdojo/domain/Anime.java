package academy.devdojo.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Anime {
    Long id;
    String name;


    public static List<Anime> getAnimes() {
        return List.of(
                Anime.builder().id(1L).name("Attack On Titan").build(),
                Anime.builder().id(2L).name("One Piece").build(),
                Anime.builder().id(3L).name("Sousou no Frieren").build(),
                Anime.builder().id(4L).name("Naruto").build(),
                Anime.builder().id(5L).name("Dragon Ball").build()
        );
    }

}