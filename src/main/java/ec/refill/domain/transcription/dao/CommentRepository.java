package ec.refill.domain.transcription.dao;

import ec.refill.domain.transcription.domain.Comment;
import ec.refill.domain.transcription.domain.Transcription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  Long countByTranscription(Transcription transcription);
}
