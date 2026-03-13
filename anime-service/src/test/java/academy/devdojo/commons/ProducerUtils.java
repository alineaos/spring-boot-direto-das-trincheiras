package academy.devdojo.commons;

import academy.devdojo.domain.Producer;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProducerUtils {
    public List<Producer> newProducerList(){
        String dateTime = "2026-03-12T16:23:08.5225638";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);

        Producer ufotable = Producer.builder().id(1L).name("Ufotable").createdAt(localDateTime).build();
        Producer a1Pictures = Producer.builder().id(2L).name("A-1 Pictures").createdAt(localDateTime).build();
        Producer davidProduction = Producer.builder().id(3L).name("David Production").createdAt(localDateTime).build();
        Producer studioBones = Producer.builder().id(4L).name("Studio Bones").createdAt(localDateTime).build();
        Producer productionIG = Producer.builder().id(5L).name("Production I. G.").createdAt(localDateTime).build();
        return new ArrayList<>(List.of(ufotable, a1Pictures, davidProduction, studioBones, productionIG));
    }

    public Producer newProducerToSave(){
        return Producer.builder().id(99L).name("MAPPA").createdAt(LocalDateTime.now()).build();
    }
}
