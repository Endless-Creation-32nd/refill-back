package ec.refill.domain.group.dto;

import ec.refill.domain.group.domain.Group;
import ec.refill.domain.group.domain.Participation;
import ec.refill.domain.group.domain.ParticipationStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GroupDto {

  private Long groupId;
  private String name;
  private String description;
  private boolean penalty;
  private int maxMember;
  private Long adminId;
  private int perWeek;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private List<String> tagList;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private int currentMemberCount;

  public static GroupDto toDto(Group group){
    List<Participation> collect = group.getParticipationList().stream()
        .filter(participation -> participation.getParticipationStatus()
            .equals(ParticipationStatus.PARTICIPATE)).toList();

    return GroupDto.builder()
        .groupId(group.getId())
        .name(group.getName())
        .description(group.getDescription())
        .penalty(group.isPenalty())
        .maxMember(group.getMaxMember())
        .perWeek(group.getPerWeek())
        .adminId(group.getAdminId())
        .startTime(group.getStartTime())
        .endTime(group.getEndTime())
        .createdAt(group.getCreatedAt())
        .updatedAt(group.getUpdatedAt())
        .tagList(group.getGroupHashTag().stream().map(groupHashTag -> groupHashTag.getHashTag().getHashTag())
            .collect(Collectors.toList()))
        .currentMemberCount(collect.size())
        .build();
  }
}
