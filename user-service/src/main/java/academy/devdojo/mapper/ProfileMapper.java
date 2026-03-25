package academy.devdojo.mapper;

import academy.devdojo.domain.Profile;
import academy.devdojo.request.ProfilePostRequest;
import academy.devdojo.response.ProfileGetResponse;
import academy.devdojo.response.ProfilePostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProfileMapper {
    Profile toProfile(ProfilePostRequest postRequest);

    ProfileGetResponse toProfileGetResponse(Profile profile);

    ProfilePostResponse toProfilePostResponse(Profile profile);

    List<ProfileGetResponse> toProfileGetResponseList(List<Profile> profiles);
}
