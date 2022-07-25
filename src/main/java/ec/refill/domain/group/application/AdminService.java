package ec.refill.domain.group.application;

import ec.refill.common.exception.AuthorizationFailException;
import ec.refill.common.exception.NotFoundResourceException;
import ec.refill.domain.group.dao.GroupRepository;
import ec.refill.domain.group.dao.ParticipationRepository;
import ec.refill.domain.group.domain.Group;
import ec.refill.domain.group.domain.Participation;
import ec.refill.domain.group.dto.GroupMemberDto;
import ec.refill.domain.member.dao.MemberRepository;
import ec.refill.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminService {

  private final MemberRepository memberRepository;
  private final GroupRepository groupRepository;
  private final ParticipationRepository participationRepository;

  @Transactional(readOnly = true)
  public GroupMemberDto getGroupMembers(Long groupId, Long memberId){
    Group group = groupRepository.findById(groupId)
        .orElseThrow(() -> new NotFoundResourceException("해당 그룹을 찾을 수 없습니다."));

    if(!group.getAdminId().equals(memberId)){
      throw new AuthorizationFailException("권한이 없습니다.");
    }

    return new GroupMemberDto(group);
  }

  @Transactional
  public void approve(Long groupId, Long adminId, Long requestMemberId){
    Member requestMember = memberRepository.findById(requestMemberId)
        .orElseThrow(() -> new NotFoundResourceException(requestMemberId + "해당 유저를 잦을 수 없습니다."));

    Group group = groupRepository.findById(groupId)
        .orElseThrow(() -> new NotFoundResourceException("해당 그룹을 찾을 수 없습니다."));

    if(!group.getAdminId().equals(adminId)){
      throw new AuthorizationFailException("권한이 없습니다.");
    }

    group.approve(requestMember);
    groupRepository.save(group);
  }

  @Transactional
  public void reject(Long groupId, Long adminId, Long requestMemberId){
    Member requestMember = memberRepository.findById(requestMemberId)
        .orElseThrow(() -> new NotFoundResourceException(requestMemberId + "해당 유저를 잦을 수 없습니다."));

    Group group = groupRepository.findById(groupId)
        .orElseThrow(() -> new NotFoundResourceException("해당 그룹을 찾을 수 없습니다."));

    if(!group.getAdminId().equals(adminId)){
      throw new AuthorizationFailException("권한이 없습니다.");
    }

    Participation pendingMember = group.getPendingMember(requestMember);
    participationRepository.delete(pendingMember);
  }

  @Transactional
  public void expel(Long groupId, Long adminId, Long currentMemberId){
    Member currentMember = memberRepository.findById(currentMemberId)
        .orElseThrow(() -> new NotFoundResourceException(currentMemberId + "해당 유저를 잦을 수 없습니다."));

    Group group = groupRepository.findById(groupId)
        .orElseThrow(() -> new NotFoundResourceException("해당 그룹을 찾을 수 없습니다."));

    if(!group.getAdminId().equals(adminId)){
      throw new AuthorizationFailException("권한이 없습니다.");
    }

    Participation pendingMember = group.getCurrentMember(currentMember);
    participationRepository.delete(pendingMember);
  }
}
