package academy.devdojo.repository;

import academy.devdojo.domain.Anime;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AnimeHardCodedRepository {
    private static final List<Anime> ANIMES = new ArrayList<>();

    static {
        Anime attackOnTitan = Anime.builder().id(1L).name("Attack On Titan").build();
        Anime onePiece = Anime.builder().id(2L).name("One Piece").build();
        Anime sousouNoFrieren = Anime.builder().id(3L).name("Sousou no Frieren").build();
        Anime naruto = Anime.builder().id(4L).name("Naruto").build();
        Anime dragonBall = Anime.builder().id(5L).name("Dragon Ball").build();
        ANIMES.addAll(List.of(attackOnTitan, onePiece, sousouNoFrieren, naruto, dragonBall));
    }

    public List<Anime> findAll(){
        return ANIMES;
    }

    public Optional<Anime> findById(Long id){
        return ANIMES.stream()
                .filter(anime -> anime.getId().equals(id))
                .findFirst();
    }

    public List<Anime> findByName(String name){
        return ANIMES.stream()
                .filter(anime -> anime.getName().equalsIgnoreCase(name))
                .toList();
    }

    public Anime save(Anime anime){
        ANIMES.add(anime);
        return anime;
    }

    public void delete(Anime anime){
        ANIMES.remove(anime);
    }

    public void update(Anime anime){
        delete(anime);
        save(anime);
    }
}
