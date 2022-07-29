package ec.refill.domain.transcription.application;

import ec.refill.common.exception.NotFoundResourceException;
import ec.refill.domain.member.dao.MemberRepository;
import ec.refill.domain.member.domain.Member;
import ec.refill.domain.transcription.dao.BookMarkRepository;
import ec.refill.domain.transcription.dao.TranscriptionRepository;
import ec.refill.domain.transcription.domain.Transcription;
import ec.refill.domain.transcription.dto.TranscriptionDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TranscriptionQueryService {

  private final TranscriptionRepository transcriptionRepository;
  private final MemberRepository memberRepository;
  private final BookMarkRepository bookMarkRepository;
  @Transactional(readOnly = true)
  public TranscriptionDetailDto getDetail(Long transcriptionId, Long viewMemberId){
    Member member = memberRepository.findById(viewMemberId)
        .orElseThrow(() -> new NotFoundResourceException("해당 유저를 찾을 수 없습니다."));
    Transcription transcription = transcriptionRepository.findById(transcriptionId)
        .orElseThrow(() -> new NotFoundResourceException("해당 필사 글을 찾을 수 없습니다."));
    boolean isBookMark = bookMarkRepository.existsByMemberAndTranscription(member, transcription);
    return TranscriptionDetailDto.toDto(transcription, isBookMark);
  }
}
