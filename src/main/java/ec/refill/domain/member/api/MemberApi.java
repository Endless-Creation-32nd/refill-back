package ec.refill.domain.member.api;

import ec.refill.common.response.JsonResponse;
import ec.refill.domain.member.application.RegisterMemberService;
import ec.refill.domain.member.dto.RegisterMemberRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberApi {

  private final RegisterMemberService registerMemberService;

  @PostMapping("")
  public ResponseEntity<?> register(@RequestBody @Valid RegisterMemberRequest request){
    registerMemberService.register(request);
    return JsonResponse.ok(HttpStatus.CREATED, "회원가입 성공");
  }

}
