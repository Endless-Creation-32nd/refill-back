package ec.refill.domain.transcription.dto;

import ec.refill.domain.member.domain.Member;
import ec.refill.domain.transcription.domain.Comment;
import ec.refill.domain.transcription.domain.Transcription;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddCommentRequest {

  @NotBlank
  @Length(max = 100)
  private String content;

  public Comment toEntity(Member member, Transcription transcription){
    return Comment.builder()
        .content(this.content)
        .member(member)
        .transcription(transcription)
        .build();
  }
}
