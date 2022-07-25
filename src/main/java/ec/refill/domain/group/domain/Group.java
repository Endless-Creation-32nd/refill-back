package ec.refill.domain.group.domain;

import ec.refill.common.BaseTimeEntity;
import ec.refill.common.exception.BusinessException;
import ec.refill.common.exception.ErrorType;
import ec.refill.common.exception.NotFoundResourceException;
import ec.refill.domain.member.domain.Member;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "transcription_group")
@NoArgsConstructor
@Getter
public class Group extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "group_id")
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private boolean penalty;

  @Column(nullable = false)
  private int maxMember;

  @Column(nullable = false)
  private int perWeek;

  private Long adminId;

  private LocalDateTime startTime;

  private LocalDateTime endTime;

  @OneToMany(mappedBy = "group")
  private Set<GroupHashTag> groupHashTag = new HashSet<>();

  @OneToMany(mappedBy = "group")
  private List<Participation> participationList = new ArrayList<>();

  @Builder
  public Group(String name, String description, boolean penalty, int maxMember, int perWeek,
      LocalDateTime startTime, LocalDateTime endTime, Long adminId) {
    this.name = name;
    this.description = description;
    this.penalty = penalty;
    this.maxMember = maxMember;
    this.perWeek = perWeek;
    this.adminId = adminId;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public void approve(Member member) {
    validateApprove();
    Participation findMember = getPendingMember(member);
    findMember.participate();
  }

  public Participation getPendingMember(Member member){
   return participationList.stream().filter(participation ->
            participation.getMember().equals(member) && participation.getParticipationStatus()
                .equals(ParticipationStatus.PENDING))
        .findAny()
        .orElseThrow(() -> new NotFoundResourceException("가입된 유저가 아닙니다."));
  }

  public Participation getCurrentMember(Member member){
    return participationList.stream().filter(participation ->
            participation.getMember().equals(member) && participation.getParticipationStatus()
                .equals(ParticipationStatus.PARTICIPATE))
        .findAny()
        .orElseThrow(() -> new NotFoundResourceException("가입된 유저가 아닙니다."));
  }

  private void validateApprove() {
    if (participationList.size() >= maxMember) {
      throw new BusinessException(ErrorType.INVALID_INPUT, "제한 인원 초과");
    }
  }
}
