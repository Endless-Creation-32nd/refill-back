package ec.refill.domain.transcription.dto;

import ec.refill.domain.transcription.domain.Word;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WordDto {

  private Long wordId;
  private String word;
  private String definition;
  private String pos;
  private String cat;

  public static WordDto toDto(Word entity){
    return WordDto.builder()
        .wordId(entity.getId())
        .word(entity.getWord())
        .definition(entity.getDefinition())
        .pos(entity.getPos())
        .cat(entity.getCat())
        .build();
  }
}
