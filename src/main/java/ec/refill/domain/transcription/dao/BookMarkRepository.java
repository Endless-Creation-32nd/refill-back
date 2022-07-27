package ec.refill.domain.transcription.dao;

import ec.refill.domain.transcription.domain.BookMark;
import ec.refill.domain.transcription.domain.BookMarkId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookMarkRepository extends JpaRepository<BookMark, BookMarkId> {

}
