package academy.devdojo.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class ProducerPutRequest {
    private Long id;
    private String name;
}
