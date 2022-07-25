package ec.refill.domain.group.application;

import ec.refill.common.exception.NotFoundResourceException;
import ec.refill.domain.group.dao.GroupRepository;
import ec.refill.domain.group.dao.ParticipationRepository;
import ec.refill.domain.group.domain.Group;
import ec.refill.domain.group.domain.Participation;
import ec.refill.domain.group.domain.ParticipationStatus;
import ec.refill.domain.group.dto.GroupDto;
import ec.refill.domain.member.dao.MemberRepository;
import ec.refill.domain.member.domain.Member;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GroupQueryService {

  private final GroupRepository groupRepository;
  private final ParticipationRepository participationRepository;
  private final MemberRepository memberRepository;

  public GroupDto findOne(Long groupId) {
    Group group = groupRepository.findById(groupId)
        .orElseThrow(() -> new NotFoundResourceException("해당 그룹을 찾을 수 없습니다."));

    return GroupDto.toDto(group, group.getParticipationList()); //그룹 상세 조회는 전체 유저 리턴
  }

  public List<GroupDto> findRecommendation() {
            List<Group> findGroups = groupRepository.findTop4ByOrderByIdDesc();

    return findGroups.stream()
        .map(group -> GroupDto.toDto(group, participateMember(group.getParticipationList())))
        .collect(Collectors.toList());
  }

  public GroupDto findMyGroup(Long memberId) {
    Member member = memberRepository.findById(memberId)
        .orElseThrow(() -> new NotFoundResourceException(memberId + "해당 유저를 잦을 수 없습니다."));

    Optional<Participation> currentParticipation = participationRepository.findByMemberAndParticipationStatus(
        member, ParticipationStatus.PARTICIPATE);

    if (currentParticipation.isEmpty()) {
      return null;
    }
    Group myGroup = currentParticipation.get().getGroup();
    return GroupDto.toDto(myGroup, participateMember(myGroup.getParticipationList()));
  }

  private List<Participation> participateMember(List<Participation> list) {
    return list.stream().filter(participation ->
        participation.getParticipationStatus().equals(ParticipationStatus.PARTICIPATE)
    ).collect(Collectors.toList());
  }
}
