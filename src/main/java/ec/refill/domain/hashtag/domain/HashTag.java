package ec.refill.domain.hashtag.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "hashtag")
@Getter
@NoArgsConstructor
public class HashTag {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "hashtag_id")
  private Long id;

  @Column(name = "hashtag", unique = true, nullable = false, columnDefinition = "varchar(20)")
  private String hashTag;

  public HashTag(String hashTag) {
    this.hashTag = hashTag;
  }

}
