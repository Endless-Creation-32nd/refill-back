package ec.refill.domain.group.dto;

import ec.refill.domain.group.domain.Group;
import ec.refill.domain.hashtag.application.HashTagValid;
import ec.refill.domain.member.domain.Member;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateGroupRequest {

  @NotBlank
  @Length(min = 2, max = 10)
  private String name;

  @NotBlank
  @Length(min = 0, max = 48)
  private String description;

  @Positive
  @Min(2)
  @Max(10)
  private int maxMember;

  @FutureOrPresent
  private LocalDateTime startTime;

  @FutureOrPresent
  private LocalDateTime endTime;

  @Positive
  @Min(1)
  @Max(10)
  private int perWeek;

  private boolean penalty;

  @HashTagValid
  private List<String> tagList;

  public Group toEntity(Member member){
    return Group.builder()
        .name(this.name)
        .description(this.description)
        .penalty(this.penalty)
        .maxMember(this.maxMember)
        .perWeek(this.perWeek)
        .startTime(this.startTime)
        .endTime(this.endTime)
        .adminId(member.getId())
        .build();
  }
}
