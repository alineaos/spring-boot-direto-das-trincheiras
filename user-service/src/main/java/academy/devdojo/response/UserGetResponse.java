package academy.devdojo.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserGetResponse {

  @Schema(description = "User's id.", example = "1")
  private Long id;
  @Schema(description = "User's first name", example = "Satoru")
  private String firstName;
  @Schema(description = "User's last name", example = "Gojou")
  private String lastName;
  @Schema(description = "User's email. Must be unique.", example = "satoru.gojou@gmail.com")
  private String email;
}
