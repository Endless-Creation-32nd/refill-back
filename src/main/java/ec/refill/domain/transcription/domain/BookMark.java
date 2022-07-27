package ec.refill.domain.transcription.domain;

import ec.refill.common.BaseTimeEntity;
import ec.refill.domain.member.domain.Member;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "book_mark")
@NoArgsConstructor
@Getter
@IdClass(BookMarkId.class)
public class BookMark extends BaseTimeEntity {

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "transcrption_id")
  private Transcription transcription;

  public BookMark(Member member, Transcription transcription) {
    this.member = member;
    this.transcription = transcription;
  }
}
