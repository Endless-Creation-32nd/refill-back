package ec.refill.domain.group.application;

import ec.refill.common.exception.NotFoundResourceException;
import ec.refill.domain.group.dao.GroupRepository;
import ec.refill.domain.group.dao.ParticipationRepository;
import ec.refill.domain.group.domain.Group;
import ec.refill.domain.group.domain.Participation;
import ec.refill.domain.group.domain.ParticipationStatus;
import ec.refill.domain.group.dto.GroupDto;
import ec.refill.domain.group.dto.GroupTranscriptionDto;
import ec.refill.domain.member.dao.MemberRepository;
import ec.refill.domain.member.domain.Member;
import ec.refill.domain.transcription.dao.BookMarkRepository;
import ec.refill.domain.transcription.dao.CommentRepository;
import ec.refill.domain.transcription.dao.TranscriptionRepository;
import ec.refill.domain.transcription.domain.Transcription;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GroupQueryService {

  private final GroupRepository groupRepository;
  private final ParticipationRepository participationRepository;
  private final MemberRepository memberRepository;
  private final TranscriptionRepository transcriptionRepository;
  private final CommentRepository commentRepository;
  private final BookMarkRepository bookMarkRepository;

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

  public List<GroupTranscriptionDto> getGroupTranscription(Long groupId,int pageNumber, int count){
    Group group = groupRepository.findById(groupId)
        .orElseThrow(() -> new NotFoundResourceException("해당 그룹을 찾을 수 없습니다."));
    PageRequest pageRequest = PageRequest.of(pageNumber,count);

    List<Transcription> groupTranscriptionList = transcriptionRepository.findByGroupIdOrderByIdDesc(
        groupId, pageRequest);

    return groupTranscriptionList.stream()
        .map((transcription) -> {
          Long bookMarkCount = bookMarkRepository.countByTranscription(transcription);
          Long commentCount = commentRepository.countByTranscription(transcription);
          return GroupTranscriptionDto.toDto(transcription, bookMarkCount, commentCount);
        }).toList();
  }

  private List<Participation> participateMember(List<Participation> list) {
    return list.stream().filter(participation ->
        participation.getParticipationStatus().equals(ParticipationStatus.PARTICIPATE)
    ).collect(Collectors.toList());
  }
}
