package ec.refill.domain.member.application;

import ec.refill.common.exception.InvalidInputException;
import ec.refill.common.exception.NotFoundResourceException;
import ec.refill.domain.member.dao.MemberRepository;
import ec.refill.domain.member.domain.CookieFactory;
import ec.refill.domain.member.domain.Member;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LogoutService {

  private final MemberRepository memberRepository;

  @Transactional(readOnly = true)
  public void logout(Long memberId, String refreshToken, HttpServletResponse response) {
    Member findMember = memberRepository.findById(memberId)
        .orElseThrow(() -> new NotFoundResourceException("User 을 찾을 수 없습니다."));

    if(!findMember.getRefreshToken().equals(refreshToken)){
      throw new InvalidInputException("로그인 정보가 일치하지 않습니다.");
    }

    Cookie removeCooke = CookieFactory.removeCookie();
    response.addCookie(removeCooke);
    findMember.setRefreshToken(null);
  }
}
