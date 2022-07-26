package ec.refill.domain.member.domain;

import ec.refill.common.property.JwtProperty;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.stereotype.Component;


@Component
public class JwtProvider {

  private final Key accessKey;
  private final Key refreshKey;

  public JwtProvider(JwtProperty jwtProperty) {
    byte[] accessKeyBytes = jwtProperty.getAccessKey().getBytes(StandardCharsets.UTF_8);
    byte[] refreshKeyBytes = jwtProperty.getAccessKey().getBytes(StandardCharsets.UTF_8);

    this.accessKey = Keys.hmacShaKeyFor(accessKeyBytes);
    this.refreshKey = Keys.hmacShaKeyFor(refreshKeyBytes);
  }

  public String accessToken(TokenPayload tokenPayload, Integer mins){
    Instant accessExpiredTime = Instant.now().plus(mins, ChronoUnit.MINUTES);
    return Jwts.builder()
        .addClaims(tokenPayload.getPayload())
        .setExpiration(Date.from(accessExpiredTime))
        .signWith(accessKey)
        .compact();
  }

  public String refreshToken(Integer days){
    Instant refreshExpiredTime = Instant.now().plus(days, ChronoUnit.MINUTES);
    return Jwts.builder()
        .setExpiration(Date.from(refreshExpiredTime))
        .signWith(refreshKey)
        .compact();
  }
}
