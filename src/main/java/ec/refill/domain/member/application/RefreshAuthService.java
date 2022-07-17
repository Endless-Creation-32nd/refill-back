package ec.refill.domain.member.application;

import ec.refill.common.exception.InvalidInputException;
import ec.refill.common.exception.NotFoundResourceException;
import ec.refill.common.property.JwtProperty;
import ec.refill.domain.member.dao.MemberRepository;
import ec.refill.domain.member.domain.CookieFactory;
import ec.refill.domain.member.domain.JwtProvider;
import ec.refill.domain.member.domain.JwtResolver;
import ec.refill.domain.member.domain.Member;
import ec.refill.domain.member.domain.Token;
import ec.refill.domain.member.domain.TokenInfo;
import ec.refill.domain.member.dto.RefreshAuthRequest;
import ec.refill.domain.member.exception.NotLoginMemberException;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshAuthService {

  private final MemberRepository memberRepository;
  private final JwtResolver resolver;
  private final JwtProvider jwtProvider;
  private final JwtProperty property;

  public Token refresh(RefreshAuthRequest dto, String refreshToken){
    String memberId = resolver.getMemberIdByJwt(dto.getAccessToken());

    Member findMember = memberRepository.findById(Long.valueOf(memberId))
        .orElseThrow(() -> new NotFoundResourceException("User 을 찾을 수 없습니다."));

    if(findMember.getRefreshToken() == null){
      throw new NotLoginMemberException();
    }

    if(!findMember.getRefreshToken().equals(refreshToken)){
      throw new InvalidInputException("로그인 정보가 일치하지 않습니다.");
    }

    /*
    *  일치하는 경우 JWT 발급
    */
    String accessToken = jwtProvider.accessToken(TokenInfo.accessTokenInfo(findMember.getId(),
        property.getAccessExpiredMin()));

    return new Token(accessToken);
  }
}
