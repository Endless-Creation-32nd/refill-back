package ec.refill.domain.transcription.dao;

import ec.refill.domain.member.domain.Member;
import ec.refill.domain.transcription.domain.Transcription;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranscriptionRepository extends JpaRepository<Transcription, Long> {
  Long countByMember(Member member);

  List<Transcription> findByMemberOrderByIdDesc(Member member, PageRequest pageRequest);
  List<Transcription> findByGroupIdOrderByIdDesc(Long groupId, PageRequest pageRequest);
}
