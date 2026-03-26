package academy.devdojo.mapper;

import academy.devdojo.domain.Profile;
import academy.devdojo.domain.User;
import academy.devdojo.domain.UserProfile;
import academy.devdojo.request.ProfilePostRequest;
import academy.devdojo.response.ProfileGetResponse;
import academy.devdojo.response.ProfilePostResponse;
import academy.devdojo.response.UserGetResponse;
import academy.devdojo.response.UserProfileGetResponse;
import academy.devdojo.response.UserProfileUserGetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserProfileMapper {
    List<UserProfileGetResponse> toUserProfileGetResponseList(List<UserProfile> userProfiles);

    List<UserProfileUserGetResponse> toUserProfileUserGetResponseList(List<User> users);

}
