package academy.devdojo.repository;

import academy.devdojo.domain.Anime;

import java.util.ArrayList;
import java.util.List;

public class AnimeData {
    private final List<Anime> animes = new ArrayList<>();

    {
        Anime attackOnTitan = Anime.builder().id(1L).name("Attack On Titan").build();
        Anime onePiece = Anime.builder().id(2L).name("One Piece").build();
        Anime sousouNoFrieren = Anime.builder().id(3L).name("Sousou no Frieren").build();
        Anime naruto = Anime.builder().id(4L).name("Naruto").build();
        Anime dragonBall = Anime.builder().id(5L).name("Dragon Ball").build();
        animes.addAll(List.of(attackOnTitan, onePiece, sousouNoFrieren, naruto, dragonBall));
    }

    public List<Anime> getAnimes() {
        return animes;
    }
}
