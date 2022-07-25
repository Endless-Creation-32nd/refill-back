package ec.refill.domain.member.domain;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

@Getter
public class TokenPayload {

  private Map<String, Object> payload;

  private TokenPayload(Map<String, Object> payload){
    this.payload = payload;
  }

  public static TokenPayload accessTokenPayload(Member member){
    Map<String, Object> accessPayload = new HashMap<>();
    accessPayload.put("memberId", member.getId());
    accessPayload.put("nickname", member.getNickname());
    return new TokenPayload(accessPayload);
  }

}
