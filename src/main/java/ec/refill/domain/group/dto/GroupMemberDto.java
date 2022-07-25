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

  private List<ParticipationMemberDto> participateRequestList;
  private List<ParticipationMemberDto> groupParticipationList;

  public GroupMemberDto(Group group) {
    this.participateRequestList = group.getParticipationList()
        .stream().filter(participation -> participation.getParticipationStatus()
            .equals(ParticipationStatus.PENDING))
        .map(participation -> new ParticipationMemberDto(participation.getMember()))
        .collect(Collectors.toList());
    this.groupParticipationList = group.getParticipationList()
        .stream().filter(participation -> participation.getParticipationStatus()
            .equals(ParticipationStatus.PARTICIPATE))
        .map(participation -> new ParticipationMemberDto(participation.getMember()))
        .collect(Collectors.toList());
  }
}
