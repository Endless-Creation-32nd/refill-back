package ec.refill.domain.transcription.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class Word {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "word_id")
  private Long id;

  @Column(nullable = false)
  private String word;

  @Column(nullable = false)
  private String definition;

  /*
  *  품사
  */
  @Column(nullable = false)
  private String pos;
  
  /*
  *  전문분야 
  */
  @Column(nullable = false)
  private String cat;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "trascription_id")
  private Transcription transcription;

  @Builder
  public Word(String word, String definition, String pos, String cat,
      Transcription transcription) {
    this.word = word;
    this.definition = definition;
    this.pos = pos;
    this.cat = cat;
    this.transcription = transcription;
  }
}
