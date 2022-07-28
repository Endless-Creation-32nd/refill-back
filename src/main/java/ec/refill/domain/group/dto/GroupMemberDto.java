package ec.refill.domain.group.dto;

import ec.refill.domain.group.domain.Group;
import ec.refill.domain.group.domain.ParticipationStatus;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GroupMemberDto {

  private List<ParticipationMemberDto> pendingMembers;
  private List<ParticipationMemberDto> participateMembers;

  public GroupMemberDto(Group group) {
    this.pendingMembers = group.getParticipationList()
        .stream().filter(participation -> participation.getParticipationStatus()
            .equals(ParticipationStatus.PENDING))
        .map(ParticipationMemberDto::toDtoByParticipation)
        .collect(Collectors.toList());
    this.participateMembers = group.getParticipationList()
        .stream().filter(participation -> participation.getParticipationStatus()
            .equals(ParticipationStatus.PARTICIPATE))
        .map(ParticipationMemberDto::toDtoByParticipation)
        .collect(Collectors.toList());
  }
}
