package ec.refill.domain.member.domain;

import ec.refill.domain.hashtag.domain.HashTag;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@IdClass(MemberHashTagId.class)
@Entity(name = "member_hashtag")
public class MemberHashTag  {

  @Id
  @ManyToOne
  @JoinColumn(name = "member_id")
  private Member member;

  @Id
  @ManyToOne
  @JoinColumn(name = "hashtag_id")
  private HashTag hashTag;

  public MemberHashTag(Member member, HashTag hashTag) {
    this.member = member;
    this.hashTag = hashTag;
  }

}
