package ec.refill.domain.group.domain;

import ec.refill.domain.hashtag.domain.HashTag;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@IdClass(GroupHashTagId.class)
@Entity(name = "group_hashtag")
public class GroupHashTag {

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "group_id")
  private Group group;

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "hashtag_id")
  private HashTag hashTag;

  public GroupHashTag(Group group, HashTag hashTag) {
    this.group = group;
    this.hashTag = hashTag;
  }
}
