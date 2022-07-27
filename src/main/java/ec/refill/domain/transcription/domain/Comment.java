package ec.refill.domain.transcription.domain;

import ec.refill.common.BaseTimeEntity;
import ec.refill.domain.member.domain.Member;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends BaseTimeEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "comment_id")
  private Long id;

  private Long content;

  @ManyToOne
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne
  @JoinColumn(name = "transcription_id")
  private Transcription transcription;

  @Builder
  public Comment(Long content, Member member,
      Transcription transcription) {
    this.content = content;
    this.member = member;
    this.transcription = transcription;
  }
}
