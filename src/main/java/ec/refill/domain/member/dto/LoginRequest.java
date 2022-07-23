package ec.refill.domain.member.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
  @NotBlank
  @Email
  private String email;

  @NotBlank
  @Pattern(regexp = "[a-zA-Z0-9]{8,}")
  private String password;
}
