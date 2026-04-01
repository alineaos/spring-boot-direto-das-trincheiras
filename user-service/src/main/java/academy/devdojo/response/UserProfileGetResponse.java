package academy.devdojo.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserProfileGetResponse {

  public record User(Long id, String firstName) {

  }

  public record Profile(Long id, String name) {

  }

  private Long id;
  private User user;
  private Profile profile;

}
