package ec.refill.domain.transcription.application;

import ec.refill.common.exception.NotFoundResourceException;
import ec.refill.domain.transcription.dao.TranscriptionRepository;
import ec.refill.domain.transcription.domain.Transcription;
import ec.refill.domain.transcription.dto.TranscriptionDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TranscriptionQueryService {

  private final TranscriptionRepository transcriptionRepository;

  public TranscriptionDetailDto getDetail(Long transcriptionId){
    Transcription transcription = transcriptionRepository.findById(transcriptionId)
        .orElseThrow(() -> new NotFoundResourceException("해당 필사 글을 찾을 수 없습니다."));
    return TranscriptionDetailDto.toDto(transcription);
  }
}
