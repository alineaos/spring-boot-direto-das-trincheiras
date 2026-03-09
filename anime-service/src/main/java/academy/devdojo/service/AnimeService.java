package academy.devdojo.service;

import academy.devdojo.domain.Anime;
import academy.devdojo.repository.AnimeHardCodedRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class AnimeService {
    private AnimeHardCodedRepository repository;

    public AnimeService() {
        this.repository = new AnimeHardCodedRepository();
    }

    public List<Anime> findAll(String name){
        return name == null ? repository.findAll() : repository.findByName(name);
    }

    public Anime findByIdOrThrowNotFound(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not Found"));
    }

    public Anime save(Anime anime){
        return repository.save(anime);
    }

    public void delete(Long id){
        Anime animeToDelete = findByIdOrThrowNotFound(id);
        repository.delete(animeToDelete);
    }

    public void update(Anime animeToUpdate){
        assertAnimeExists(animeToUpdate.getId());

        repository.update(animeToUpdate);
    }

    private void assertAnimeExists(Long id){
        findByIdOrThrowNotFound(id);
    }
}
