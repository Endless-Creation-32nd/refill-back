package ec.refill.domain.group.dto;

import ec.refill.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ParticipationMemberDto {

  private Long memberId;
  private String nickname;
  private String image;

  public ParticipationMemberDto(Member member) {
    this.memberId = member.getId();
    this.nickname = member.getNickname();
    this.image = member.getImage();
  }
}
