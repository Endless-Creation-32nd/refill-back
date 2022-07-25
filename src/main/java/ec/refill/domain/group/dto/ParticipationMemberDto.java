package ec.refill.domain.group.dto;

import ec.refill.domain.group.domain.Participation;
import ec.refill.domain.group.domain.ParticipationStatus;
import lombok.Getter;

@Getter
public class ParticipationMemberDto {

  private Long memberId;
  private String nickname;
  private String image;
  private ParticipationStatus status;

  public ParticipationMemberDto(Participation participation) {
    this.memberId = participation.getMember().getId();
    this.nickname = participation.getMember().getNickname();
    this.image = participation.getMember().getImage();
    this.status = participation.getParticipationStatus();
  }
}
