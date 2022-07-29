package ec.refill.domain.group.domain;

import ec.refill.domain.group.exception.LimitPenaltyException;
import ec.refill.domain.member.domain.Member;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "participation")
@IdClass(ParticipationId.class)
@Getter
@NoArgsConstructor
public class Participation {

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "group_id")
  private Group group;

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @Enumerated(EnumType.STRING)
  private ParticipationStatus participationStatus;

  private Integer penalty;

  public Participation(Group group, Member member, ParticipationStatus status) {
    this.group = group;
    this.member = member;
    this.participationStatus = status;
    this.penalty = 0;
  }

  public void participate(){
    this.participationStatus = ParticipationStatus.PARTICIPATE;
  }

  public void addPenalty(){
    if(this.penalty >=3){
      throw new LimitPenaltyException("이미 최대 패널티입니다.");
    }
    this.penalty++;
  }

  public void cancelPenalty(){
    if(this.penalty == 0){
      throw new LimitPenaltyException("패널티를 취소할 수 없습니다.");
    }
    this.penalty--;
  }
}
