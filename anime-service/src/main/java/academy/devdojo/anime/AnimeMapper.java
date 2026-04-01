package academy.devdojo.anime;

import academy.devdojo.domain.Anime;
import academy.devdojo.dto.AnimeGetResponse;
import academy.devdojo.dto.AnimePostRequest;
import academy.devdojo.dto.AnimePostResponse;
import academy.devdojo.dto.AnimePutRequest;
import academy.devdojo.dto.PageAnimeGetResponse;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.data.domain.Page;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AnimeMapper {

  Anime toAnime(AnimePostRequest postRequest);

  Anime toAnime(AnimePutRequest putRequest);

  AnimePostResponse toAnimePostResponse(Anime anime);

  AnimeGetResponse toAnimeGetResponse(Anime anime);

  List<AnimeGetResponse> toAnimeGetResponseList(List<Anime> animes);

  PageAnimeGetResponse toPageAnimeGetResponse(Page<Anime> jpaPageAnimeGetResponse);
}