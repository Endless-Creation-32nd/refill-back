package ec.refill.domain.group.dto;

import ec.refill.domain.transcription.domain.Transcription;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class GroupTranscriptionDto {

  private Long transcriptionId;
  private String transcriptionImage;
  private String title;
  private LocalDateTime createdAt;

  private ParticipationMemberDto participation;
  private Long bookmarkCount;
  private Long commentCount;

  public static GroupTranscriptionDto toDto(Transcription entity, Long bookmarkCount, Long commentCount){
    return GroupTranscriptionDto.builder()
        .transcriptionId(entity.getId())
        .transcriptionImage(entity.getImage())
        .title(entity.getTitle())
        .createdAt(entity.getCreatedAt())
        .participation(ParticipationMemberDto.toDtoByMember(entity.getMember()))
        .bookmarkCount(bookmarkCount)
        .commentCount(commentCount)
        .build();
  }
}
