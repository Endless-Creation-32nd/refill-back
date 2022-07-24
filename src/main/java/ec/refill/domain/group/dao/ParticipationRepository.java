package ec.refill.domain.group.dao;

import ec.refill.domain.group.domain.Participation;
import ec.refill.domain.group.domain.ParticipationId;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipationRepository extends JpaRepository<Participation, ParticipationId> {
}
