package academy.devdojo.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserProfileUserGetResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
