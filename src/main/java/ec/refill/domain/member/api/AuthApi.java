package ec.refill.domain.member.api;

import ec.refill.common.response.JsonResponse;
import ec.refill.domain.member.application.LoginService;
import ec.refill.domain.member.domain.Token;
import ec.refill.domain.member.dto.LoginRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApi {

  private final LoginService loginService;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request, HttpServletResponse response){
    Token token = loginService.login(request, response);
    return JsonResponse.okWithData(HttpStatus.OK,"로그인 성공", token);
  }
}
