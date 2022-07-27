package ec.refill.domain.transcription.dao;

import ec.refill.domain.transcription.domain.Transcription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranscriptionRepository extends JpaRepository<Transcription, Long> {

}