package ec.refill.domain.transcription.application;

import ec.refill.common.exception.NotFoundResourceException;
import ec.refill.domain.member.dao.MemberRepository;
import ec.refill.domain.member.domain.Member;
import ec.refill.domain.transcription.dao.CommentRepository;
import ec.refill.domain.transcription.dao.TranscriptionRepository;
import ec.refill.domain.transcription.domain.Comment;
import ec.refill.domain.transcription.domain.Transcription;
import ec.refill.domain.transcription.dto.AddCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddCommentService {

  private final MemberRepository memberRepository;
  private final TranscriptionRepository transcriptionRepository;
  private final CommentRepository commentRepository;

  @Transactional
  public void addComment(AddCommentRequest request, Long memberId, Long transcriptionId){
    Member requestMember = memberRepository.findById(memberId)
        .orElseThrow(() -> new NotFoundResourceException("해당 멤버를 찾을 수 없습니다."));
    Transcription transcription = transcriptionRepository.findById(transcriptionId)
        .orElseThrow(() -> new NotFoundResourceException("해당 필사 글을 찾을 수 없습니다."));

    Comment newComment = request.toEntity(requestMember, transcription);
    commentRepository.save(newComment);
  }
}
