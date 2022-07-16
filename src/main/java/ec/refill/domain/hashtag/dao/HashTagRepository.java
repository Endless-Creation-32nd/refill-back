package ec.refill.domain.hashtag.dao;

import ec.refill.domain.hashtag.domain.HashTag;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HashTagRepository extends JpaRepository<HashTag, Long> {

  Optional<HashTag> findByHashTag(String hashtag);
}
