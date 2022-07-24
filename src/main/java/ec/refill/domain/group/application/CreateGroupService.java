package ec.refill.domain.group.application;

import ec.refill.common.exception.NotFoundResourceException;
import ec.refill.domain.group.dao.GroupHashTagRepository;
import ec.refill.domain.group.dao.GroupRepository;
import ec.refill.domain.group.dao.ParticipationRepository;
import ec.refill.domain.group.domain.Group;
import ec.refill.domain.group.domain.GroupHashTag;
import ec.refill.domain.group.domain.Participation;
import ec.refill.domain.group.domain.ParticipationId;
import ec.refill.domain.group.domain.ParticipationStatus;
import ec.refill.domain.group.dto.CreateGroupRequest;
import ec.refill.domain.group.exception.AlreadyParticipateException;
import ec.refill.domain.hashtag.dao.HashTagRepository;
import ec.refill.domain.hashtag.domain.HashTag;
import ec.refill.domain.member.dao.MemberRepository;
import ec.refill.domain.member.domain.Member;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateGroupService {

  private final MemberRepository memberRepository;
  private final GroupRepository groupRepository;
  private final HashTagRepository hashTagRepository;
  private final GroupHashTagRepository groupHashTagRepository;
  private final ParticipationRepository participationRepository;

  public void createGroup(CreateGroupRequest request, Long memberId) {
    Member member = memberRepository.findById(memberId)
        .orElseThrow(() -> new NotFoundResourceException(memberId + "해당 유저를 잦을 수 없습니다."));

    Group group = request.toEntity(member);

    groupRepository.save(group);

    participationRepository.findByMemberAndParticipationStatus(member,
        ParticipationStatus.PARTICIPATE).ifPresent((participation) -> {
      throw new AlreadyParticipateException("이미 방에 참여중입니다.");
    });

    participationRepository.save(new Participation(group, member, ParticipationStatus.PARTICIPATE));

    for (String tag : request.getTagList()) {
      HashTag hashTag = hashTagRepository.findByHashTag(tag)
          .orElseGet(() -> hashTagRepository.save(new HashTag(tag)));

      groupHashTagRepository.save(new GroupHashTag(group, hashTag));
    }
  }
}
