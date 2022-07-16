package ec.refill.domain.member.application;

import ec.refill.domain.member.dao.MemberRepository;
import ec.refill.domain.member.exception.DuplicateEmailException;
import ec.refill.domain.member.exception.DuplicateNicknameException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DuplicateCheckService {

  private final MemberRepository memberRepository;

  public void check(String nickname, String email){
    if (memberRepository.existsByEmail(email)) {
      throw new DuplicateEmailException();
    }

    if (memberRepository.existsByNickname(nickname)) {
      throw new DuplicateNicknameException();
    }
  }
}
