package ec.refill.domain.group.dao;

import ec.refill.domain.group.domain.GroupHashTag;
import ec.refill.domain.group.domain.GroupHashTagId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupHashTagRepository extends JpaRepository<GroupHashTag, GroupHashTagId> {
}
