package academy.devdojo.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ProducerPostResponse {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
}
