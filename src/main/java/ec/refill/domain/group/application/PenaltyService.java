package ec.refill.domain.group.application;

import ec.refill.common.exception.AuthorizationFailException;
import ec.refill.common.exception.NotFoundResourceException;
import ec.refill.domain.group.dao.GroupRepository;
import ec.refill.domain.group.dao.ParticipationRepository;
import ec.refill.domain.group.domain.Group;
import ec.refill.domain.group.domain.Participation;
import ec.refill.domain.group.exception.LimitPenaltyException;
import ec.refill.domain.group.exception.NotAllowAdminRequestException;
import ec.refill.domain.group.exception.NotPenaltyGroupException;
import ec.refill.domain.group.exception.NotSatisfiedPenaltyPolicyException;
import ec.refill.domain.group.vo.PeriodVo;
import ec.refill.domain.member.dao.MemberRepository;
import ec.refill.domain.member.domain.Member;
import ec.refill.domain.transcription.dao.TranscriptionRepository;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PenaltyService {

  private final MemberRepository memberRepository;
  private final GroupRepository groupRepository;
  private final TranscriptionRepository transcriptionRepository;
  private final PenaltyRulePolicy penaltyRulePolicy;

  @Transactional
  public void addPenalty(Long targetMemberId, Long adminId, Long groupId) {
    Member admin = memberRepository.findById(adminId)
        .orElseThrow(() -> new NotFoundResourceException("해당 유저를 찾을 수 없습니다."));
    Group group = groupRepository.findById(groupId)
        .orElseThrow(() -> new NotFoundResourceException("해당 그룹을 찾을 수 없습니다."));

    if (!group.isPenalty()) {
      throw new NotPenaltyGroupException("패널티그룹이 아닙니다.");
    }

    if (!group.getAdminId().equals(adminId)) {
      throw new AuthorizationFailException("권한이 없습니다.");
    }

    if(groupId.equals(adminId)){
      throw new NotAllowAdminRequestException("본인 스스로를 패널티 부여할 수 없습니다.");
    }

    Member participationMember = memberRepository.findById(targetMemberId)
        .orElseThrow(() -> new NotFoundResourceException("해당 유저를 찾을 수 없습니다."));
    Participation currentMember = group.getCurrentMember(participationMember);

    PeriodVo period = penaltyRulePolicy.penaltyTime(LocalDateTime.now(ZoneId.of("Asia/Seoul")));
    Long lastWeekActivity = transcriptionRepository.countByMemberAndGroupIdAndCreatedAtBetween(participationMember,groupId,
        period.getStartTime(),
        period.getEndTime());

    if(lastWeekActivity >= group.getPerWeek()){
      throw new NotSatisfiedPenaltyPolicyException("해당 유저에게 패널티를 줄 수 없습니다.");
    }

    currentMember.addPenalty();
  }

  @Transactional
  public void cancelPenalty(Long targetMemberId, Long adminId, Long groupId) {
    Member admin = memberRepository.findById(adminId)
        .orElseThrow(() -> new NotFoundResourceException("해당 유저를 찾을 수 없습니다."));
    Group group = groupRepository.findById(groupId)
        .orElseThrow(() -> new NotFoundResourceException("해당 그룹을 찾을 수 없습니다."));

    if (!group.isPenalty()) {
      throw new NotPenaltyGroupException("패널티그룹이 아닙니다.");
    }

    if (!group.getAdminId().equals(adminId)) {
      throw new AuthorizationFailException("권한이 없습니다.");
    }

    if (groupId.equals(adminId)) {
      throw new NotAllowAdminRequestException("본인 스스로를 패널티 취소할 수 없습니다.");
    }

    Member participationMember = memberRepository.findById(targetMemberId)
        .orElseThrow(() -> new NotFoundResourceException("해당 유저를 찾을 수 없습니다."));
    Participation currentMember = group.getCurrentMember(participationMember);

    currentMember.cancelPenalty();
  }
}
