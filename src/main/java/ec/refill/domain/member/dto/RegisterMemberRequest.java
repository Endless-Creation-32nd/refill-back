package ec.refill.domain.member.dto;

import ec.refill.domain.hashtag.application.HashTagValid;
import ec.refill.domain.member.domain.Member;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterMemberRequest {

  @NotBlank
  @Pattern(regexp = "[가-힣]{2,8}")
  private String nickname;

  @NotBlank
  @Email
  private String email;

  @NotBlank
  @Pattern(regexp = "[a-zA-Z0-9]{8,}")
  private String password;

  @HashTagValid
  private List<String> tagList;

  public Member toEntity(String encoredPassword) {
    return Member.builder()
        .email(this.email)
        .nickname(this.nickname)
        .password(encoredPassword)
        .build();
  }

}
