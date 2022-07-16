package ec.refill.domain.member.api;

import ec.refill.common.response.JsonResponse;
import ec.refill.domain.member.application.DuplicateCheckService;
import ec.refill.domain.member.application.RegisterMemberService;
import ec.refill.domain.member.dto.RegisterMemberRequest;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/member")
public class MemberApi {

  private final RegisterMemberService registerMemberService;
  private final DuplicateCheckService duplicateCheckService;

  @PostMapping("")
  public ResponseEntity<?> register(@RequestBody @Valid RegisterMemberRequest request){
    registerMemberService.register(request);
    return JsonResponse.ok(HttpStatus.CREATED, "회원가입 성공");
  }

  @GetMapping("/check")
  public ResponseEntity<?> checkNicknameAndEmail(
      @Valid @NotBlank @RequestParam("nickname") String nickname,
      @Valid @NotBlank @Email @RequestParam("email") String email){
    duplicateCheckService.check(nickname, email);
    return  JsonResponse.ok(HttpStatus.OK, "검증 성공");
  }
}
