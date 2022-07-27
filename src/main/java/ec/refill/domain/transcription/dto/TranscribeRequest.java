package ec.refill.domain.transcription.dto;

import ec.refill.domain.member.domain.Member;
import ec.refill.domain.transcription.domain.Transcription;
import ec.refill.domain.transcription.domain.Word;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TranscribeRequest {

  @NotBlank
  @Length(min = 1, max=30)
  private String title;

  @NotBlank
  @Length(min =1 , max = 20)
  private String author;

  private String original;

  @NotBlank
  private String imageUrl;

  @NotNull
  private Boolean isGroup;

  private Long groupId;

  @Valid
  private List<WordRequest> wordList;

  public Transcription toEntityAndGroup(Member member){
    Transcription transcription = Transcription.builder()
        .title(this.title)
        .author(this.author)
        .groupId(this.groupId)
        .image(this.imageUrl)
        .member(member)
        .original(original)
        .build();
    return addWordList(transcription);
  }

  public Transcription toEntity(Member member){
    Transcription transcription = Transcription.builder()
        .title(this.title)
        .author(this.author)
        .groupId(null)
        .image(this.imageUrl)
        .member(member)
        .original(original)
        .build();
    return addWordList(transcription);
  }

  private Transcription addWordList(Transcription transcription){
    if(wordList == null){
      return transcription;
    }
    List<Word> wordList = this.wordList.stream().map(wordDto -> wordDto.toEntity(transcription))
        .toList();
    transcription.setWordList(wordList);
    return transcription;
  }
}
