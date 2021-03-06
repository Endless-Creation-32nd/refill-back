package ec.refill.domain.member.api;

import ec.refill.common.config.web.AuthMember;
import ec.refill.common.config.web.LoginMember;
import ec.refill.common.response.JsonResponse;
import ec.refill.domain.member.application.LoginService;
import ec.refill.domain.member.application.LogoutService;
import ec.refill.domain.member.application.RefreshAuthService;
import ec.refill.domain.member.dto.TokenDto;
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
  private final LogoutService logoutService;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request,
      HttpServletResponse response) {
    TokenDto token = loginService.login(request, response);
    return JsonResponse.okWithData(HttpStatus.OK, "로그인 성공", token);
  }

  @GetMapping(" ")
  public ResponseEntity<?> authCheck(@LoginMember AuthMember user) {

    return JsonResponse.okWithData(HttpStatus.OK, "인증 성공", new AuthCheckResponse(user.getId(), user.getNickname()));
  }

  @PostMapping("/refresh")
  public ResponseEntity<?> refreshAuth(@RequestBody @Valid RefreshAuthRequest refreshAuthRequest,
      @CookieValue(name = "refresh", required = false) String refreshToken) {
    if (refreshToken == null) {
      throw new NotLoginMemberException();
    }
    TokenDto token = refreshAuthService.refresh(refreshAuthRequest, refreshToken);
    return JsonResponse.okWithData(HttpStatus.OK, "재인증 성공", token);
  }

  @GetMapping("/logout")
  public ResponseEntity<?> logout(@LoginMember AuthMember user,  @CookieValue(name = "refresh", required = false) String refreshToken,
    HttpServletResponse response
  ) {
    if(refreshToken == null ) throw new NotLoginMemberException();
    logoutService.logout(user.getId(), refreshToken, response);

    return JsonResponse.ok(HttpStatus.OK,"로그아웃 성공");
  }
}
