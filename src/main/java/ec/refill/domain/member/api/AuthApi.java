package ec.refill.domain.member.api;

import ec.refill.common.config.web.AuthUser;
import ec.refill.common.config.web.LoginUser;
import ec.refill.common.response.JsonResponse;
import ec.refill.domain.member.application.LoginService;
import ec.refill.domain.member.application.RefreshAuthService;
import ec.refill.domain.member.domain.Token;
import ec.refill.domain.member.dto.AuthCheckResponse;
import ec.refill.domain.member.dto.LoginRequest;
import ec.refill.domain.member.dto.RefreshAuthRequest;
import ec.refill.domain.member.exception.NotLoginMemberException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApi {

  private final LoginService loginService;
  private final RefreshAuthService refreshAuthService;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request,
      HttpServletResponse response) {
    Token token = loginService.login(request, response);
    return JsonResponse.okWithData(HttpStatus.OK, "로그인 성공", token);
  }

  @GetMapping(" ")
  public ResponseEntity<?> authCheck(@LoginUser AuthUser user) {

    return JsonResponse.okWithData(HttpStatus.OK, "인증 성공", new AuthCheckResponse(user.getId()));
  }

  @PostMapping("/refresh")
  public ResponseEntity<?> refreshAuth(@RequestBody @Valid RefreshAuthRequest refreshAuthRequest,
      @CookieValue(name = "refresh", required = false) String refreshToken) {
    if (refreshToken == null) {
      throw new NotLoginMemberException();
    }
    Token token = refreshAuthService.refresh(refreshAuthRequest, refreshToken);
    return JsonResponse.okWithData(HttpStatus.OK, "재인증 성공", token);
  }
}
