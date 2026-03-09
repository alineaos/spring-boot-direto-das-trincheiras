package academy.devdojo.repository;

import academy.devdojo.domain.Producer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProducerHardCodedRepository {
    private static final List<Producer> PRODUCERS = new ArrayList<>();

    static {
        Producer mappa = Producer.builder().id(1L).name("Mappa").createdAt(LocalDateTime.now()).build();
        Producer toeiAnimation = Producer.builder().id(2L).name("Toei Animation").createdAt(LocalDateTime.now()).build();
        Producer madhouse = Producer.builder().id(3L).name("Madhouse").createdAt(LocalDateTime.now()).build();
        Producer pierrotStudio = Producer.builder().id(4L).name("Studio Pierrot").createdAt(LocalDateTime.now()).build();
        Producer witStudio = Producer.builder().id(5L).name("Wit Studio").createdAt(LocalDateTime.now()).build();
        PRODUCERS.addAll(List.of(mappa, toeiAnimation, madhouse, pierrotStudio, witStudio));
    }

    public List<Producer> findAll() {
        return PRODUCERS;
    }

    public Optional<Producer> findById(Long id){
        return PRODUCERS.stream()
                .filter(producer ->  producer.getId().equals(id))
                .findFirst();
    }

    public List<Producer> findByName(String name){
        return PRODUCERS.stream()
                .filter(producer -> producer.getName().equalsIgnoreCase(name))
                .toList();
    }

    public Producer save(Producer producer){
        PRODUCERS.add(producer);
        return producer;
    }

    public void delete(Producer producer){
        PRODUCERS.remove(producer);
    }

    public void update(Producer producer){
        delete(producer);
        save(producer);
    }
}
