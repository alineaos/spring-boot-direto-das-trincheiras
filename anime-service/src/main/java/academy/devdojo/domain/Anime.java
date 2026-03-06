package academy.devdojo.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Anime {
    @EqualsAndHashCode.Include
    Long id;
    String name;
    @Getter
    private static List<Anime> animes = new ArrayList<>();

    static {
        Anime attackOnTitan = Anime.builder().id(1L).name("Attack On Titan").build();
        Anime onePiece = Anime.builder().id(2L).name("One Piece").build();
        Anime sousouNoFrieren = Anime.builder().id(3L).name("Sousou no Frieren").build();
        Anime naruto = Anime.builder().id(4L).name("Naruto").build();
        Anime dragonBall = Anime.builder().id(5L).name("Dragon Ball").build();
        animes.addAll(List.of(attackOnTitan, onePiece, sousouNoFrieren, naruto, dragonBall));
    }

}