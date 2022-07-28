package ec.refill.domain.member.dto;

import ec.refill.domain.transcription.domain.Transcription;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProfileTranscriptionDto {

  private Long transcriptionId;
  private String image;

  public static ProfileTranscriptionDto toDto(Transcription entity){
    return new ProfileTranscriptionDto(entity.getId(), entity.getImage());
  }
}
