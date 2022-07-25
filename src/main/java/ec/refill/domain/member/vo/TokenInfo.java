package ec.refill.domain.member.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenInfo {
  private Long memberId;
  private String nickname;
}
