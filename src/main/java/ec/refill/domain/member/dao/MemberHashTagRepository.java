package ec.refill.domain.member.dao;

import ec.refill.domain.member.domain.MemberHashTag;
import ec.refill.domain.member.domain.MemberHashTagId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberHashTagRepository extends JpaRepository<MemberHashTag, MemberHashTagId> {

}
