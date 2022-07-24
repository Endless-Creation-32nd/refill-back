package ec.refill.domain.group.dao;

import ec.refill.domain.group.domain.Participation;
import ec.refill.domain.group.domain.ParticipationId;
import ec.refill.domain.group.domain.ParticipationStatus;
import ec.refill.domain.member.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipationRepository extends JpaRepository<Participation, ParticipationId> {
  Optional<Participation> findByMemberAndParticipationStatus(Member member, ParticipationStatus status);
}

