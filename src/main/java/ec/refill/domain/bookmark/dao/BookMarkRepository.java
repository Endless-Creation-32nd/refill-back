package ec.refill.domain.bookmark.dao;

import ec.refill.domain.bookmark.domain.BookMark;
import ec.refill.domain.bookmark.domain.BookMarkId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookMarkRepository extends JpaRepository<BookMark, BookMarkId> {

}
