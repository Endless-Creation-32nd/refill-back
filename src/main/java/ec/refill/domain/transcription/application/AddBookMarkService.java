package ec.refill.domain.transcription.application;

import ec.refill.common.exception.NotFoundResourceException;
import ec.refill.domain.member.dao.MemberRepository;
import ec.refill.domain.member.domain.Member;
import ec.refill.domain.transcription.dao.BookMarkRepository;
import ec.refill.domain.transcription.dao.TranscriptionRepository;
import ec.refill.domain.transcription.domain.BookMark;
import ec.refill.domain.transcription.domain.BookMarkId;
import ec.refill.domain.transcription.domain.Transcription;
import ec.refill.domain.transcription.exception.AlreadyBookMarkException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddBookMarkService {

  private final MemberRepository memberRepository;
  private final BookMarkRepository bookMarkRepository;
  private final TranscriptionRepository transcriptionRepository;

  @Transactional
  public void addBookMark(Long memberId, Long transcriptionId){
    Member requestMember = memberRepository.findById(memberId)
        .orElseThrow(() -> new NotFoundResourceException("해당 멤버를 찾을 수 없습니다."));
    Transcription transcription = transcriptionRepository.findById(transcriptionId)
        .orElseThrow(() -> new NotFoundResourceException("해당 필사 글을 찾을 수 없습니다."));

    if(bookMarkRepository.existsByMemberAndTranscription(requestMember, transcription)){
      throw new AlreadyBookMarkException("이미 해당 필사를 북마크했습니다.");
    }

    bookMarkRepository.save(new BookMark(requestMember, transcription));
  }
}
