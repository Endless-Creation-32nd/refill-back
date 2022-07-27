package ec.refill.domain.transcription.api;

import ec.refill.common.config.web.AuthMember;
import ec.refill.common.config.web.LoginMember;
import ec.refill.common.response.JsonResponse;
import ec.refill.domain.transcription.application.TranscribeService;
import ec.refill.domain.transcription.dto.TranscribeRequest;
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
@RequestMapping("/api/transcription")
public class TranscriptionApi {

  private final TranscribeService transcribeService;

  @PostMapping(value = "")
  public ResponseEntity<?> transcribe(
      @Valid @RequestBody TranscribeRequest request,
      @LoginMember AuthMember member
      ) {
    transcribeService.transcribe(request, member.getId());
    return JsonResponse.ok(HttpStatus.OK, "필사 인증 업로드 성공");
  }
}
