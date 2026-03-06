package academy.devdojo.controller;


import academy.devdojo.domain.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("v1/producers")
@Slf4j
public class ProducerController {
    private List<Producer> producers = new ArrayList<>(Producer.getProducers());
    @GetMapping()
    public List<Producer> listAll(@RequestParam(required = false) String name) {
        if (name == null) return producers;
        return producers.stream()
                .filter(producer -> producer.getName().equalsIgnoreCase(name))
                .toList();
    }

    @GetMapping("{id}")
    public Producer findById(@PathVariable long id) {
        return Producer.getProducers()
                .stream()
                .filter(producer -> producer.getId().equals(id))
                .findFirst().orElse(null);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            headers = "x-api-key")
    public Producer save(@RequestBody Producer producer, @RequestHeader HttpHeaders headers){
        log.info("{}", headers);
        producer.setId(ThreadLocalRandom.current().nextLong(100_000)); //settando o id nesse momento, o que foi passado na requisição será ignorado
        producers.add(producer);
        return producer;
    }

}
