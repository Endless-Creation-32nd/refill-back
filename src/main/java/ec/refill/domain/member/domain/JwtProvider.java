package ec.refill.domain.member.domain;

import ec.refill.common.property.JwtProperty;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.sql.Date;
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

  public String accessToken(TokenInfo tokenInfo){
    return Jwts.builder()
        .addClaims(tokenInfo.getPayload())
        .setExpiration(Date.from(tokenInfo.getExpiredTime()))
        .signWith(accessKey)
        .compact();
  }

  public String refreshToken(TokenInfo tokenInfo){
    return Jwts.builder()
        .setExpiration(Date.from(tokenInfo.getExpiredTime()))
        .signWith(refreshKey)
        .compact();
  }
}
