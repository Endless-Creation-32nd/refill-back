package ec.refill.domain.writing.dao;

import ec.refill.domain.writing.domain.Writing;
import ec.refill.domain.writing.domain.WritingCategory;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WritingRepository extends JpaRepository<Writing, Long> {

  List<Writing> findByCategoryOrderByIdDesc(WritingCategory category, PageRequest pageable);
}
