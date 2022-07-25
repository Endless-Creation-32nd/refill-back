package ec.refill.domain.member.application;

import ec.refill.common.exception.InvalidInputException;
import ec.refill.common.exception.NotFoundResourceException;
import ec.refill.domain.member.dao.MemberRepository;
import ec.refill.domain.member.domain.JwtProvider;
import ec.refill.domain.member.domain.JwtResolver;
import ec.refill.domain.member.domain.Member;
import ec.refill.domain.member.dto.TokenDto;
import ec.refill.domain.member.domain.TokenPayload;
import ec.refill.domain.member.dto.RefreshAuthRequest;
import ec.refill.domain.member.exception.NotLoginMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshAuthService {

  private final MemberRepository memberRepository;
  private final JwtResolver resolver;
  private final JwtProvider jwtProvider;

  public TokenDto refresh(RefreshAuthRequest dto, String refreshToken){
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
    String accessToken = jwtProvider.accessToken(TokenPayload.accessTokenPayload(findMember));

    return new TokenDto(accessToken);
  }
}
