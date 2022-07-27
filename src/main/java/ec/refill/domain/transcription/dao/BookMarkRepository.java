package ec.refill.domain.transcription.dao;

import ec.refill.domain.member.domain.Member;
import ec.refill.domain.transcription.domain.BookMark;
import ec.refill.domain.transcription.domain.BookMarkId;
import ec.refill.domain.transcription.domain.Transcription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookMarkRepository extends JpaRepository<BookMark, BookMarkId> {
  boolean existsByMemberAndTranscription(Member member, Transcription transcription);
}
