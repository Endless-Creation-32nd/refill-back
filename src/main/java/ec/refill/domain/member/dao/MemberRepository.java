package ec.refill.domain.member.dao;

import ec.refill.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
  boolean existsByEmail(String email);
  boolean existsByNickname(String nickname);
}
