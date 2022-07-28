package ec.refill.domain.transcription.dao;

import ec.refill.domain.member.domain.Member;
import ec.refill.domain.transcription.domain.BookMark;
import ec.refill.domain.transcription.domain.BookMarkId;
import ec.refill.domain.transcription.domain.Transcription;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookMarkRepository extends JpaRepository<BookMark, BookMarkId> {
  boolean existsByMemberAndTranscription(Member member, Transcription transcription);
  Long countByMember(Member member);
  Long countByTranscription(Transcription transcription);
  List<BookMark> findByMemberOrderByCreatedAtDesc(Member member, PageRequest pageRequest);
}
