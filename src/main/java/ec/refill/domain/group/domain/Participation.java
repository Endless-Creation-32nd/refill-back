package ec.refill.domain.group.domain;

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

  public Participation(Group group, Member member, ParticipationStatus status) {
    this.group = group;
    this.member = member;
    this.participationStatus = status;
  }

  public boolean approve(){
    return true;
  }
}
