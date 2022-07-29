package ec.refill.domain.group.dto;

import ec.refill.domain.group.domain.Group;
import ec.refill.domain.group.domain.Participation;
import ec.refill.domain.group.domain.ParticipationStatus;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class AdminParticipationResponse {

  private List<ParticipationMemberDto> pendingMembers;
  private List<ParticipationActivityDto> participateMembers;


  public AdminParticipationResponse(
      List<ParticipationMemberDto> pendingMembers,
      List<ParticipationActivityDto> participateMembers) {
    this.pendingMembers = pendingMembers;
    this.participateMembers = participateMembers;
  }
}
