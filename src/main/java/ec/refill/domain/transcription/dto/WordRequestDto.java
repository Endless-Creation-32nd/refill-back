package ec.refill.domain.transcription.dto;

import ec.refill.domain.transcription.domain.Transcription;
import ec.refill.domain.transcription.domain.Word;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WordRequestDto {


  @NotBlank
  private String word;

  @NotBlank
  private String definition;

  @NotNull
  private String pos;

  @NotNull
  private String cat;

  public Word toEntity(Transcription transcription){
    return Word.builder()
        .word(this.word)
        .definition(this.definition)
        .pos(this.pos)
        .cat(this.cat)
        .transcription(transcription)
        .build();
  }
}
