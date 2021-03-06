package ec.refill.domain.member.domain;

import ec.refill.common.exception.ErrorType;
import ec.refill.common.exception.JwtAuthenticationException;
import ec.refill.common.property.JwtProperty;
import ec.refill.domain.member.vo.TokenInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtResolver {

  private final Key accessKey;
  private final Key refreshKey;

  public JwtResolver(JwtProperty jwtProperty) {
    byte[] accessKeyBytes = jwtProperty.getAccessKey().getBytes(StandardCharsets.UTF_8);
    byte[] refreshKeyBytes = jwtProperty.getAccessKey().getBytes(StandardCharsets.UTF_8);

    this.accessKey = Keys.hmacShaKeyFor(accessKeyBytes);
    this.refreshKey = Keys.hmacShaKeyFor(refreshKeyBytes);
  }

  public Authentication getAuthentication(String token) {
    Claims claims = Jwts.parserBuilder().setSigningKey(accessKey).build().parseClaimsJws(token)
        .getBody();
    //1. principal , 2 credential(ex password) 3. Authorities(ex Role..)
    TokenInfo tokenInfo = new TokenInfo(Long.valueOf(claims.get("memberId").toString()),
        claims.get("nickname").toString());
    return new UsernamePasswordAuthenticationToken(tokenInfo, "", new ArrayList<>());
  }

  public boolean validateAccessToken(String token) {
    try {
      Jwts.parserBuilder().setSigningKey(accessKey).build().parse(token);
      return true;
    } catch (SecurityException | MalformedJwtException | SignatureException e) {
      log.error("잘못된 JWT 서명");
      throw new JwtAuthenticationException(ErrorType.INVALID_JWT, "잘못된 JWT 서명");
    } catch (UnsupportedJwtException e) {
      log.error("지원하지 않는 JWT 토큰");
      throw new JwtAuthenticationException(ErrorType.INVALID_JWT, "지원하지 않는 JWT 토큰");
    } catch (IllegalArgumentException e) {
      log.error("잘못된 토큰 값 ");
      throw new JwtAuthenticationException(ErrorType.INVALID_JWT, "잘못된 토큰 값.");
    } catch (ExpiredJwtException e) {
      log.error("JWT 만료");
      throw new JwtAuthenticationException(ErrorType.EXPIRED_JWT, "JWT 토큰 만료");
    }
  }

  public boolean validateRefreshToken(String refreshToken) {
    try {
      Jwts.parserBuilder().setSigningKey(refreshKey).build().parse(refreshToken);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public String getMemberIdByJwt(String token) {
    try {
      Claims claims = Jwts.parserBuilder().setSigningKey(accessKey).build().parseClaimsJws(token)
          .getBody();
      return claims.get("memberId").toString();
    } catch (ExpiredJwtException e) {
      return e.getClaims().get("memberId").toString();
    } catch (Exception e) {
      throw new JwtAuthenticationException(ErrorType.INVALID_JWT, "잘못된 토큰 값.");
    }
  }

}
