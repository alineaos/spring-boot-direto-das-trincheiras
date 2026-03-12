package academy.devdojo.repository;

import academy.devdojo.domain.Anime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AnimeHardCodedRepositoryTest {

    @InjectMocks
    private AnimeHardCodedRepository repository;
    @Mock
    private AnimeData animeData;
    private List<Anime> animeList = new ArrayList<>();

    @BeforeEach
    void init() {
        Anime bananaFish = Anime.builder().id(1L).name("Banana Fish").build();
        Anime bleach = Anime.builder().id(2L).name("Bleach").build();
        Anime bokuNoHeroAcademia = Anime.builder().id(3L).name("Boku no Hero Academia").build();
        Anime pokemon = Anime.builder().id(4L).name("Pokémon").build();
        Anime toBeHeroX = Anime.builder().id(5L).name("To be Hero X").build();
        animeList.addAll(List.of(bananaFish, bleach, bokuNoHeroAcademia, pokemon, toBeHeroX));
    }

    @Test
    @DisplayName("findAll returns a list with all animes")
    @Order(1)
    void findAll_ReturnsAllAnimes_WhenSuccessful() {
        BDDMockito.when(animeData.getAnimes()).thenReturn(animeList);

        List<Anime> animes = repository.findAll();
        Assertions.assertThat(animes).isNotNull().hasSize(animeList.size());
    }

    @Test
    @DisplayName("findById returns a anime with given id")
    @Order(2)
    void findById_ReturnsAnimeById_WhenSuccessful() {
        BDDMockito.when(animeData.getAnimes()).thenReturn(animeList);

        Anime expectedAnime = animeList.getFirst();
        Optional<Anime> anime = repository.findById(expectedAnime.getId());
        Assertions.assertThat(anime).isPresent().contains(expectedAnime);
    }

    @Test
    @DisplayName("findByName returns empty list when name is null")
    @Order(3)
    void findByName_ReturnsEmptyList_WhenNameIsNull() {
        BDDMockito.when(animeData.getAnimes()).thenReturn(animeList);

        List<Anime> animes = repository.findByName(null);
        Assertions.assertThat(animes).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("findByName returns list with found objects when name exists")
    @Order(4)
    void findByName_ReturnsFoundAnimeInList_WhenNameIsFound() {
        BDDMockito.when(animeData.getAnimes()).thenReturn(animeList);

        Anime expectedAnime = animeList.getFirst();
        List<Anime> animes = repository.findByName(expectedAnime.getName());
        Assertions.assertThat(animes).contains(expectedAnime);
    }

    @Test
    @DisplayName("save creates a anime")
    @Order(5)
    void save_CreatesAnime_WhenSuccessful() {
        BDDMockito.when(animeData.getAnimes()).thenReturn(animeList);

        Anime animeToSave = Anime.builder().name("Jujutsu Kaisen").id(99L).build();
        Anime animeSaved = repository.save(animeToSave);

        Assertions.assertThat(animeToSave).isEqualTo(animeSaved).hasNoNullFieldsOrProperties();

        Optional<Anime> animeSavedOptional = repository.findById(animeToSave.getId());
        Assertions.assertThat(animeSavedOptional).isPresent().contains(animeToSave);

    }

    @Test
    @DisplayName("delete removes a anime")
    @Order(6)
    void delete_RemoveAnime_WhenSuccessul() {
        BDDMockito.when(animeData.getAnimes()).thenReturn(animeList);

        Anime animeToDelete = animeList.getFirst();
        repository.delete(animeToDelete);

        Assertions.assertThat(animeList).doesNotContain(animeToDelete);
    }

    @Test
    @DisplayName("update updates a anime")
    @Order(7)
    void update_UpdatesAnime_WhenSuccessful() {
        BDDMockito.when(animeData.getAnimes()).thenReturn(animeList);

        Anime animeToUpdate = animeList.getFirst();
        animeToUpdate.setName("Kimetsu No Yaiba");

        repository.update(animeToUpdate);

        Assertions.assertThat(this.animeList).contains(animeToUpdate);

        Optional<Anime> animeUpdatedOptional = repository.findById(animeToUpdate.getId());
        Assertions.assertThat(animeUpdatedOptional).isPresent();
        Assertions.assertThat(animeUpdatedOptional.get().getName()).isEqualTo(animeToUpdate.getName());
    }
}