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
  private final Instant accessTime;
  private final Instant refreshTime;

  public JwtProvider(JwtProperty jwtProperty) {
    byte[] accessKeyBytes = jwtProperty.getAccessKey().getBytes(StandardCharsets.UTF_8);
    byte[] refreshKeyBytes = jwtProperty.getAccessKey().getBytes(StandardCharsets.UTF_8);
    this.accessTime =  Instant.now().plus(jwtProperty.getAccessExpiredMin(), ChronoUnit.MINUTES);
    this.refreshTime = Instant.now().plus(jwtProperty.getRefreshExpiredDay(), ChronoUnit.DAYS);

    this.accessKey = Keys.hmacShaKeyFor(accessKeyBytes);
    this.refreshKey = Keys.hmacShaKeyFor(refreshKeyBytes);
  }

  public String accessToken(TokenPayload tokenPayload){
    return Jwts.builder()
        .addClaims(tokenPayload.getPayload())
        .setExpiration(Date.from(accessTime))
        .signWith(accessKey)
        .compact();
  }

  public String refreshToken(){
    return Jwts.builder()
        .setExpiration(Date.from(refreshTime))
        .signWith(refreshKey)
        .compact();
  }
}
