package academy.devdojo.controller;


import academy.devdojo.domain.Anime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/animes")
@Slf4j
public class AnimeController {

    @GetMapping()
    public List<Anime> listAll(@RequestParam(required = false) String name) {
        List<Anime> animes = Anime.getAnimes();
        if (name == null) return animes;
        return animes.stream()
                .filter(anime -> anime.getName().equalsIgnoreCase(name))
                .toList();
    }


    // Outro jeito de fazer
    /*@GetMapping("filter")
    public List<Anime> findByName(@RequestParam String name) {
        return Anime.getAnimes().stream()
                .filter(anime -> anime.getName().equalsIgnoreCase(name))
                .toList();
    }*/

    @GetMapping("{id}")
    public Anime findById(@PathVariable long id) {
        return Anime.getAnimes()
                .stream()
                .filter(anime -> anime.getId().equals(id))
                .findFirst().orElse(null);
    }

}
