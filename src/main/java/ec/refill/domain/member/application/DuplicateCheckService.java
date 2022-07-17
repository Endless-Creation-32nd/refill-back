package ec.refill.domain.member.application;

import ec.refill.domain.member.dao.MemberRepository;
import ec.refill.domain.member.exception.DuplicateEmailException;
import ec.refill.domain.member.exception.DuplicateNicknameException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DuplicateCheckService {

  private final MemberRepository memberRepository;

  @Transactional(readOnly = true)
  public void check(String nickname, String email){
    if (memberRepository.existsByEmail(email)) {
      throw new DuplicateEmailException();
    }

    if (memberRepository.existsByNickname(nickname)) {
      throw new DuplicateNicknameException();
    }
  }
}
