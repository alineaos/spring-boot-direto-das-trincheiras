package academy.devdojo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
public class Producer {
    private Long id;
    @JsonProperty("name") // Se o nome na api estiver diferente do que no sistema, essa anotação faz uma "conversão". Mais detalhes Notion
    private String name;
    private LocalDateTime createdAt;
    private static List<Producer> producers = new ArrayList<>();

    static {
        Producer mappa = Producer.builder().id(1L).name("Mappa").createdAt(LocalDateTime.now()).build();
        Producer toeiAnimation = Producer.builder().id(2L).name("Toei Animation").createdAt(LocalDateTime.now()).build();
        Producer madhouse = Producer.builder().id(3L).name("Madhouse").createdAt(LocalDateTime.now()).build();
        Producer pierrotStudio = Producer.builder().id(4L).name("Studio Pierrot").createdAt(LocalDateTime.now()).build();
        Producer witStudio = Producer.builder().id(5L).name("Wit Studio").createdAt(LocalDateTime.now()).build();
        producers.addAll(List.of(mappa, toeiAnimation, madhouse, pierrotStudio, witStudio));
    }

    public static List<Producer> getProducers() {
        return producers;
    }
}
