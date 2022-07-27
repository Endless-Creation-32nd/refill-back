package ec.refill.domain.transcription.application;

import ec.refill.common.exception.InvalidInputException;
import ec.refill.common.exception.NotFoundResourceException;
import ec.refill.domain.group.dao.GroupRepository;
import ec.refill.domain.group.dao.ParticipationRepository;
import ec.refill.domain.group.domain.Group;
import ec.refill.domain.group.domain.ParticipationId;
import ec.refill.domain.group.domain.ParticipationStatus;
import ec.refill.domain.group.exception.NotParticipateException;
import ec.refill.domain.member.dao.MemberRepository;
import ec.refill.domain.member.domain.Member;
import ec.refill.domain.transcription.dao.TranscriptionRepository;
import ec.refill.domain.transcription.domain.Transcription;
import ec.refill.domain.transcription.dto.TranscribeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TranscribeService {

  private final TranscriptionRepository transcriptionRepository;
  private final MemberRepository memberRepository;
  private final GroupRepository groupRepository;
  private final ParticipationRepository participationRepository;

  @Transactional
  public void transcribe(TranscribeRequest request, Long memberId){
    Member requestMember = memberRepository.findById(memberId)
        .orElseThrow(() -> new NotFoundResourceException("해당 유저를 찾을 수 없습니다."));

    if(request.getIsGroup()){
      checkGroupId(request);

      Group group = groupRepository.findById(request.getGroupId())
          .orElseThrow(() -> new NotFoundResourceException("해당 그룹을 찾을 수 없습니다."));

      if(!participationRepository.existsByGroupAndMemberAndParticipationStatus(group,requestMember,
          ParticipationStatus.PARTICIPATE)){
        throw new NotParticipateException("해당 그룹에 참여 중이 아닙니다.");
      }

      Transcription transcription = request.toEntityAndGroup(requestMember);


      transcriptionRepository.save(transcription);
    }else{
      Transcription transcription = request.toEntity(requestMember);
      transcriptionRepository.save(transcription);
    }
  }

  private void checkGroupId(TranscribeRequest request){
    if(request.getGroupId() == null){
      throw new InvalidInputException("groupId가 null 일 수 없습니다.");
    }
  }

}
