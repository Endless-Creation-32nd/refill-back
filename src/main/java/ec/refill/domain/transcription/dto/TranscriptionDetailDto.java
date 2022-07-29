package ec.refill.domain.transcription.dto;

import ec.refill.domain.transcription.domain.Transcription;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TranscriptionDetailDto {

  private Long transcriptionId;
  private String title;
  private String author;
  private String original;
  private String image;
  private Boolean isBookMark;
  private List<WordDto> wordList;
  private List<CommentDto> commentList;

  public static TranscriptionDetailDto toDto(Transcription transcription, boolean isBookMark) {
    return TranscriptionDetailDto.builder()
        .transcriptionId(transcription.getId())
        .title(transcription.getTitle())
        .author(transcription.getAuthor())
        .original(transcription.getOriginal())
        .image(transcription.getImage())
        .isBookMark(isBookMark)
        .wordList(transcription.getWordList().stream().map(WordDto::toDto).toList())
        .commentList(transcription.getComment().stream().map(CommentDto::toDto).toList())
        .build();
  }
}
