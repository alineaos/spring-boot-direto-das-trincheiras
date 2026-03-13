package academy.devdojo.commons;

import academy.devdojo.domain.Anime;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnimeUtils {
    public List<Anime> newAnimeList() {
        Anime bananaFish = Anime.builder().id(1L).name("Banana Fish").build();
        Anime bleach = Anime.builder().id(2L).name("Bleach").build();
        Anime bokuNoHeroAcademia = Anime.builder().id(3L).name("Boku no Hero Academia").build();
        Anime pokemon = Anime.builder().id(4L).name("Pokémon").build();
        Anime toBeHeroX = Anime.builder().id(5L).name("To be Hero X").build();
        return new ArrayList<>(List.of(bananaFish, bleach, bokuNoHeroAcademia, pokemon, toBeHeroX));
    }

    public Anime newAnimeToSave() {
        return Anime.builder().id(99L).name("Hunter X Hunter").build();
    }
}
