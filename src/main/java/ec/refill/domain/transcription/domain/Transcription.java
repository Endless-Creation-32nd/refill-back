package ec.refill.domain.transcription.domain;

import ec.refill.common.BaseTimeEntity;
import ec.refill.domain.member.domain.Member;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Transcription extends BaseTimeEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "transcripion_id")
  private Long id;

  @Column(name = "group_id")
  private Long groupId;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String author;

  private String original;

  @Column(nullable = false)
  private String image;

  @OneToMany(mappedBy = "transcription", cascade = CascadeType.ALL)
  private List<Word> wordList = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @OneToMany(mappedBy = "transcription")
  private List<Comment> comment = new ArrayList<>();


  @Builder
  public Transcription(Long groupId, String title, String author, String original,
      String image, Member member) {
    this.groupId = groupId;
    this.title = title;
    this.author = author;
    this.original = original;
    this.image = image;
    this.member = member;
  }

  public void setWordList(List<Word> list){
    this.wordList = list;
  }

}
