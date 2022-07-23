package ec.refill.domain.writing.dto;

import ec.refill.domain.writing.domain.Writing;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class WritingDto {

  private Long writingId;
  private String title;
  private String imageUrl;
  private String author;
  private String description;
  private String linkUrl;

  public static WritingDto toDto(Writing writing){
    return WritingDto.builder()
        .writingId(writing.getId())
        .title(writing.getTitle())
        .imageUrl(writing.getImageUrl())
        .author(writing.getAuthor())
        .description(writing.getDescription())
        .linkUrl(writing.getLinkUrl())
        .build();
  }
}
