package academy.devdojo.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProfileGetResponse {
    private Long id;
    private String name;
    private String description;
}
