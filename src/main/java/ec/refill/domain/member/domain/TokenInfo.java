package ec.refill.domain.member.domain;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

@Getter
public class TokenInfo {

  private Map<String, Object> payload;
  private Instant expiredTime;

  private TokenInfo(Map<String, Object> payload, Instant time){
    this.payload = payload;
    this.expiredTime = time;
  }

  public static TokenInfo accessTokenInfo(Long memberId, Integer min){
    Map<String, Object> accessPayload = new HashMap<>();
    accessPayload.put("memberId", memberId);

    Instant accessExpired = Instant.now().plus(min, ChronoUnit.MINUTES);
    return new TokenInfo(accessPayload, accessExpired);
  }

  public static TokenInfo refreshTokenInfo(Integer days){
      Instant refreshExpired = Instant.now().plus(days, ChronoUnit.DAYS);
      return new TokenInfo(new HashMap<>(), refreshExpired);
  }

}
