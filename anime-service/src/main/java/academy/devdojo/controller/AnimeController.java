package academy.devdojo.controller;


import academy.devdojo.domain.Anime;
import academy.devdojo.mapper.AnimeMapper;
import academy.devdojo.request.AnimePostRequest;
import academy.devdojo.request.AnimePutRequest;
import academy.devdojo.response.AnimeGetResponse;
import academy.devdojo.response.AnimePostResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/animes")
@Slf4j
public class AnimeController {
    private static AnimeMapper MAPPER = AnimeMapper.INSTANCE;
    private List<Anime> animes = new ArrayList<>(Anime.getAnimes());

    @GetMapping()
    public ResponseEntity<List<AnimeGetResponse>> listAll(@RequestParam(required = false) String name) {
        log.debug("Request received to list all animes, param name '{}'", name);
        
        List<AnimeGetResponse> responseList = MAPPER.toAnimeGetResponseList(animes);

        if (name == null) return ResponseEntity.ok(responseList);

        List<AnimeGetResponse> response = responseList.stream()
                .filter(a -> a.getName().equalsIgnoreCase(name))
                .toList();

        return ResponseEntity.ok(response);
    }


    // Outro jeito de fazer
    /*@GetMapping("filter")
    public List<Anime> findByName(@RequestParam String name) {
        return Anime.getAnimes().stream()
                .filter(anime -> anime.getName().equalsIgnoreCase(name))
                .toList();
    }*/

    @GetMapping("{id}")
    public ResponseEntity<AnimeGetResponse> findById(@PathVariable long id) {
        log.debug("Request to find anime by id: '{}'", id);

        AnimeGetResponse response = animes
                .stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .map(MAPPER::toAnimeGetResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not Found"));

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<AnimePostResponse> save(@RequestBody AnimePostRequest animePostRequest){
        log.debug("request to save anime: '{}'", animePostRequest.getName());
        Anime anime = MAPPER.toAnime(animePostRequest);

        animes.add(anime);

        AnimePostResponse response = MAPPER.toAnimePostResponse(anime);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        log.debug("Request to delete anime by id: {}", id);
        Anime anime = animes
                .stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not Found"));

        animes.remove(anime);

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody AnimePutRequest request){
        log.debug("Request to update anime {}", request);

        Anime animeToRemove = animes
                .stream()
                .filter(a -> a.getId().equals(request.getId()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not Found"));

        Anime animeUpdated = MAPPER.toAnime(request);

        animes.remove(animeToRemove);
        animes.add(animeUpdated);

        return ResponseEntity.noContent().build();
    }

}
