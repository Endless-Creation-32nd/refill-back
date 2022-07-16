package ec.refill.domain.member.domain;

import javax.servlet.http.Cookie;
import org.springframework.stereotype.Component;

@Component
public class CookieFactory {

  public static Cookie generateRefreshCooke(String refreshToken, Integer refreshExpiredDay){
    Cookie cookie = new Cookie("refresh", refreshToken);
    cookie.setHttpOnly(true);
    cookie.setPath("/");
    cookie.setMaxAge(refreshExpiredDay * 3600 * 24);
    return cookie;
  }
}
