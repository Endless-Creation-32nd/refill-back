package ec.refill.domain.group.dao;

import ec.refill.domain.group.domain.Group;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
  List<Group> findTop4ByOrderByIdDesc();
}
