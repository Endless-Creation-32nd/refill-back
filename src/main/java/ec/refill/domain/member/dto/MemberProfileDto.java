package ec.refill.domain.member.dto;

import ec.refill.domain.group.domain.Participation;
import ec.refill.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberProfileDto {

  private Long memberId;
  private String nickname;
  private String image;
  private Long uploadCount;
  private Long bookMarkCount;
  private Integer penaltyCount;
  private String groupName;

  public static MemberProfileDto toDto(Member member, Long uploadCount, Long bookMarkCount,
      Integer penaltyCount, String groupName) {
    return MemberProfileDto.builder()
        .memberId(member.getId())
        .nickname(member.getNickname())
        .image(member.getImage())
        .uploadCount(uploadCount)
        .bookMarkCount(bookMarkCount)
        .penaltyCount(penaltyCount)
        .groupName(groupName)
        .build();
  }
}
