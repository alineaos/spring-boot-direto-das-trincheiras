package academy.devdojo.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserPutRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
