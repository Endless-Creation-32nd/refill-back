package ec.refill.domain.group.dto;

import ec.refill.domain.group.domain.Participation;
import ec.refill.domain.group.domain.ParticipationStatus;
import ec.refill.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ParticipationMemberDto {

  private Long memberId;
  private String nickname;
  private String image;
  private ParticipationStatus status;

  public static ParticipationMemberDto toDtoByParticipation(Participation participation) {
    return ParticipationMemberDto.builder()
        .memberId(participation.getMember().getId())
        .nickname(participation.getMember().getNickname())
        .image(participation.getMember().getImage())
        .status(participation.getParticipationStatus())
        .build();
  }

  public static ParticipationMemberDto toDtoByMember(Member member){
    return ParticipationMemberDto.builder()
        .memberId(member.getId())
        .nickname(member.getNickname())
        .image(member.getImage())
        .status(ParticipationStatus.PARTICIPATE)
        .build();
  }
}
