package academy.devdojo.anime;


import academy.devdojo.domain.Anime;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import java.util.List;

@RestController
@RequestMapping("v1/animes")
@Slf4j
@RequiredArgsConstructor
public class AnimeController {
    private final AnimeMapper mapper;
    private final AnimeService service;

    @GetMapping()
    public ResponseEntity<List<AnimeGetResponse>> findAll(@RequestParam(required = false) String name) {
        log.debug("Request received to list all animes, param name '{}'", name);

        List<Anime> animes = service.findAll(name);
        List<AnimeGetResponse> animeGetResponses = mapper.toAnimeGetResponseList(animes);


        return ResponseEntity.ok(animeGetResponses);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<AnimeGetResponse>> findAllPaginated(@ParameterObject Pageable pageable) {
        log.debug("Request received to list all animes paginated");

        Page<AnimeGetResponse> pageAnimeGetResponse = service.findAllPaginated(pageable).map(mapper::toAnimeGetResponse);


        return ResponseEntity.ok(pageAnimeGetResponse);
    }

    @GetMapping("{id}")
    public ResponseEntity<AnimeGetResponse> findById(@PathVariable Long id) {
        log.debug("Request to find anime by id: '{}'", id);

        Anime anime = service.findByIdOrThrowNotFound(id);
        AnimeGetResponse animeGetResponse = mapper.toAnimeGetResponse(anime);

        return ResponseEntity.ok(animeGetResponse);
    }

    @PostMapping
    public ResponseEntity<AnimePostResponse> save(@RequestBody @Valid AnimePostRequest animePostRequest) {
        log.debug("request to save anime: '{}'", animePostRequest.getName());

        Anime anime = mapper.toAnime(animePostRequest);
        Anime animeSaved = service.save(anime);
        AnimePostResponse animePostResponse = mapper.toAnimePostResponse(animeSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(animePostResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.debug("Request to delete anime by id: {}", id);

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid AnimePutRequest request) {
        log.debug("Request to update anime {}", request);

        Anime animeToUpdate = mapper.toAnime(request);
        service.update(animeToUpdate);

        return ResponseEntity.noContent().build();
    }
}
