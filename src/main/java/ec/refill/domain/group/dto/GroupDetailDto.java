package ec.refill.domain.group.dto;

import ec.refill.domain.group.domain.Group;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GroupDetailDto {

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
  private List<ParticipationMemberDto> participationMembers;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;


  public static GroupDetailDto toDto(Group group){
    return GroupDetailDto.builder()
        .groupId(group.getId())
        .name(group.getName())
        .description(group.getDescription())
        .penalty(group.isPenalty())
        .maxMember(group.getMaxMember())
        .adminId(group.getAdminId())
        .perWeek(group.getPerWeek())
        .startTime(group.getStartTime())
        .endTime(group.getEndTime())
        .createdAt(group.getCreatedAt())
        .updatedAt(group.getUpdatedAt())
        .participationMembers(group.getParticipationList().stream()
            .map(member -> new ParticipationMemberDto(member.getMember()))
            .collect(Collectors.toList())
        )
        .tagList(group.getGroupHashTag().stream().map(groupHashTag -> groupHashTag.getHashTag().getHashTag())
            .collect(Collectors.toList())
        )
        .build();
  }
}
