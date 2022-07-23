package ec.refill.domain.writing.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "writing")
@NoArgsConstructor
@Getter
public class Writing {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(name = "image_url", nullable = false)
  private String imageUrl;

  @Column(nullable = false)
  private String author;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String description;

  @Column(name = "link_url")
  private String linkUrl;

  @Enumerated(value = EnumType.STRING)
  private WritingCategory category;

  @Builder
  public Writing(String title, String imageUrl, String author, String description,
      String linkUrl, WritingCategory category) {
    this.title = title;
    this.imageUrl = imageUrl;
    this.author = author;
    this.description = description;
    this.linkUrl = linkUrl;
    this.category = category;
  }
}
