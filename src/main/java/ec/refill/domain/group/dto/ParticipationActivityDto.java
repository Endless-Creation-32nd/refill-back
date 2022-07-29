package ec.refill.domain.group.dto;


import ec.refill.domain.group.domain.Participation;
import ec.refill.domain.group.domain.ParticipationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParticipationActivityDto {

  private Long memberId;
  private String nickname;
  private String image;
  private ParticipationStatus status;
  private Integer penaltyCount;
  private Integer lastWeekRemainActivity;

  public static ParticipationActivityDto toDto(Participation participation, Integer lastWeekRemainActivity) {
    return ParticipationActivityDto.builder()
        .memberId(participation.getMember().getId())
        .nickname(participation.getMember().getNickname())
        .image(participation.getMember().getImage())
        .status(participation.getParticipationStatus())
        .penaltyCount(participation.getPenalty())
        .lastWeekRemainActivity(lastWeekRemainActivity)
        .build();
  }
}
