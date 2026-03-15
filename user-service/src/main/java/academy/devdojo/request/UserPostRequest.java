package academy.devdojo.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserPostRequest {
    private String firstName;
    private String lastName;
    private String email;
}
