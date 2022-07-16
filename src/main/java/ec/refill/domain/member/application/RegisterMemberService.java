package ec.refill.domain.member.application;

import ec.refill.domain.hashtag.dao.HashTagRepository;
import ec.refill.domain.hashtag.domain.HashTag;
import ec.refill.domain.member.dao.MemberHashTagRepository;
import ec.refill.domain.member.dao.MemberRepository;
import ec.refill.domain.member.domain.Member;
import ec.refill.domain.member.domain.MemberHashTag;
import ec.refill.domain.member.dto.RegisterMemberRequest;
import ec.refill.domain.member.exception.DuplicateEmailException;
import ec.refill.domain.member.exception.DuplicateNicknameException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RegisterMemberService {

  private final MemberRepository memberRepository;
  private final HashTagRepository hashTagRepository;
  private final MemberHashTagRepository memberHashTagRepository;
  private final PasswordEncoder passwordEncoder;

  public void register(RegisterMemberRequest request) {
    if (memberRepository.existsByEmail(request.getEmail())) {
      throw new DuplicateEmailException();
    }

    if (memberRepository.existsByNickname(request.getNickname())) {
      throw new DuplicateNicknameException();
    }

    Member member = request.toEntity(passwordEncoder.encode(request.getPassword()));
    memberRepository.save(member);

    for (String tag : request.getTagList()) {
      HashTag hashTag = hashTagRepository.findByHashTag(tag)
          .orElseGet(() -> hashTagRepository.save(new HashTag(tag)));

      memberHashTagRepository.save(new MemberHashTag(member, hashTag));
    }
  }
}
