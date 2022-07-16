package ec.refill.domain.member.dto;

import ec.refill.domain.hashtag.application.HashTagValid;
import ec.refill.domain.member.domain.Member;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterMemberRequest {

  @NotBlank
  @Pattern(regexp = "[가-힣]{2,8}")
  private final String nickname;

  @NotBlank
  @Email
  private final String email;

  @NotBlank
  @Pattern(regexp = "[a-zA-Z0-9]{8,}")
  private final String password;
  
  @HashTagValid
  private final List<String> tagList;

  public Member toEntity(String encoredPassword) {
    return Member.builder()
        .email(this.email)
        .nickname(this.nickname)
        .password(encoredPassword)
        .build();
  }

}
