package ec.refill.domain.group.application;

import ec.refill.common.exception.NotFoundResourceException;
import ec.refill.domain.group.dao.GroupRepository;
import ec.refill.domain.group.dao.ParticipationRepository;
import ec.refill.domain.group.domain.Group;
import ec.refill.domain.group.domain.Participation;
import ec.refill.domain.group.domain.ParticipationId;
import ec.refill.domain.group.domain.ParticipationStatus;
import ec.refill.domain.group.exception.AlreadyParticipateException;
import ec.refill.domain.member.dao.MemberRepository;
import ec.refill.domain.member.domain.Member;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ParticipateGroupService {

  private final ParticipationRepository participationRepository;
  private final MemberRepository memberRepository;
  private final GroupRepository groupRepository;

  public void participate(Long groupId, Long memberId) {
    Member member = memberRepository.findById(memberId)
        .orElseThrow(() -> new NotFoundResourceException(memberId + "해당 유저를 찾을 수 없습니다."));

    Group group = groupRepository.findById(groupId)
        .orElseThrow(() -> new NotFoundResourceException("해당 그룹을 찾을 수 없습니다."));

    /*
     *  이미 다른 방에 참여중이면 안됨.
     */
    participationRepository.findByMemberAndParticipationStatus(member,
            ParticipationStatus.PARTICIPATE)
        .ifPresent(entity -> {
          throw new AlreadyParticipateException("이미 방에 참여중입니다!");
        });

    participationRepository.findById(new ParticipationId(groupId, memberId))
        .ifPresent(entity -> {
          throw new AlreadyParticipateException("해당 방에 이미 참여했습니다.");
        });

    participationRepository.save(new Participation(group, member, ParticipationStatus.PENDING));
  }
}
