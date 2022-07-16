package ec.refill.domain.member.application;

import ec.refill.common.exception.NotFoundResourceException;
import ec.refill.common.property.JwtProperty;
import ec.refill.domain.member.dao.MemberRepository;
import ec.refill.domain.member.domain.CookieFactory;
import ec.refill.domain.member.domain.JwtProvider;
import ec.refill.domain.member.domain.Member;
import ec.refill.domain.member.domain.Token;
import ec.refill.domain.member.domain.TokenInfo;
import ec.refill.domain.member.dto.LoginRequest;
import ec.refill.domain.member.exception.NotMatchPasswordException;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

  private final MemberRepository memberRepository;
  private final JwtProvider jwtProvider;
  private final JwtProperty property;
  private final PasswordEncoder passwordEncoder;

  public Token login(LoginRequest loginRequest, HttpServletResponse response){
    Member findMember = memberRepository.findByEmail(loginRequest.getEmail())
        .orElseThrow(() -> new NotFoundResourceException(loginRequest.getEmail() + "User 을 찾을 수 없습니다."));

    if(!passwordEncoder.matches(loginRequest.getPassword(), findMember.getPassword())){
      throw new NotMatchPasswordException();
    }

    String accessToken = jwtProvider.accessToken(TokenInfo.accessTokenInfo(findMember.getId(), property.getAccessExpiredMin()));
    String refreshToken = jwtProvider.refreshToken(TokenInfo.refreshTokenInfo(property.getRefreshExpiredDay()));

    response.addCookie(CookieFactory.generateRefreshCooke(refreshToken,property.getRefreshExpiredDay()));

    return new Token(accessToken);
  }
}
