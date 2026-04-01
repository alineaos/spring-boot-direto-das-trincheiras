package academy.devdojo.anime;


import academy.devdojo.api.AnimeControllerApi;
import academy.devdojo.domain.Anime;
import academy.devdojo.dto.AnimeGetResponse;
import academy.devdojo.dto.AnimePostRequest;
import academy.devdojo.dto.AnimePostResponse;
import academy.devdojo.dto.AnimePutRequest;
import academy.devdojo.dto.PageAnimeGetResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

@RestController
@RequestMapping("v1/animes")
@Slf4j
@RequiredArgsConstructor
@SecurityRequirement(name = "basicAuth")
public class AnimeController implements AnimeControllerApi {

  private final AnimeMapper mapper;
  private final AnimeService service;

  @GetMapping()
  public ResponseEntity<List<AnimeGetResponse>> findAllAnimes(@RequestParam(required = false) String name) {
    log.debug("Request received to list all animes, param name '{}'", name);

    List<Anime> animes = service.findAll(name);
    List<AnimeGetResponse> animeGetResponses = mapper.toAnimeGetResponseList(animes);

    return ResponseEntity.ok(animeGetResponses);
  }

  @Override
  @GetMapping("/paginated")
  public ResponseEntity<PageAnimeGetResponse> findAllAnimesPaginated(
      @Min(0) @Parameter(name = "page", description = "Zero-based page index (0..N)",
          in = ParameterIn.QUERY) @Valid @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
      @Min(1) @Parameter(name = "size", description = "The size of the page to be returned",
          in = ParameterIn.QUERY) @Valid @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
      @Parameter(name = "sort",
          description = "Sorting criteria in the format: property,(asc|desc)."
              + " Default sort order is ascending. Multiple sort criteria are supported.",
          in = ParameterIn.QUERY) @Valid @RequestParam(value = "sort", required = false) List<String> sort,
      @ParameterObject final Pageable pageable) {
    log.debug("Request received to list all animes paginated");

    Page<Anime> jpaPageAnimeGetResponse = service.findAllPaginated(pageable);
    PageAnimeGetResponse pageAnimeGetResponse = mapper.toPageAnimeGetResponse(jpaPageAnimeGetResponse);

    return ResponseEntity.ok(pageAnimeGetResponse);
  }

  @GetMapping("{id}")
  public ResponseEntity<AnimeGetResponse> findAnimeById(@PathVariable Long id) {
    log.debug("Request to find anime by id: '{}'", id);

    Anime anime = service.findByIdOrThrowNotFound(id);
    AnimeGetResponse animeGetResponse = mapper.toAnimeGetResponse(anime);

    return ResponseEntity.ok(animeGetResponse);
  }

  @PostMapping
  public ResponseEntity<AnimePostResponse> saveAnime(@RequestBody @Valid AnimePostRequest animePostRequest) {
    log.debug("request to save anime: '{}'", animePostRequest.getName());

    Anime anime = mapper.toAnime(animePostRequest);
    Anime animeSaved = service.save(anime);
    AnimePostResponse animePostResponse = mapper.toAnimePostResponse(animeSaved);

    return ResponseEntity.status(HttpStatus.CREATED).body(animePostResponse);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteAnimeById(@PathVariable Long id) {
    log.debug("Request to delete anime by id: {}", id);

    service.delete(id);

    return ResponseEntity.noContent().build();
  }

  @PutMapping
  public ResponseEntity<Void> updateAnime(@RequestBody @Valid AnimePutRequest request) {
    log.debug("Request to update anime {}", request);

    Anime animeToUpdate = mapper.toAnime(request);
    service.update(animeToUpdate);

    return ResponseEntity.noContent().build();
  }
}
