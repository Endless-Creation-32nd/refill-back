package ec.refill.domain.transcription.api;

import ec.refill.common.config.web.AuthMember;
import ec.refill.common.config.web.LoginMember;
import ec.refill.common.response.JsonResponse;
import ec.refill.domain.transcription.application.AddBookMarkService;
import ec.refill.domain.transcription.application.AddCommentService;
import ec.refill.domain.transcription.application.TranscribeService;
import ec.refill.domain.transcription.application.TranscriptionQueryService;
import ec.refill.domain.transcription.dto.AddCommentRequest;
import ec.refill.domain.transcription.dto.TranscribeRequest;
import ec.refill.domain.transcription.dto.TranscriptionDetailDto;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transcription")
public class TranscriptionApi {

  private final TranscribeService transcribeService;
  private final AddCommentService addCommentService;
  private final AddBookMarkService addBookMarkService;
  private final TranscriptionQueryService transcriptionQueryService;
  @PostMapping(value = "")
  public ResponseEntity<?> transcribe(
      @Valid @RequestBody TranscribeRequest request,
      @LoginMember AuthMember member
      ) {
    transcribeService.transcribe(request, member.getId());
    return JsonResponse.ok(HttpStatus.OK, "필사 인증 업로드 성공");
  }

  @PostMapping("/{transcriptionId}/comment")
  public ResponseEntity<?> addComment(@Valid @RequestBody AddCommentRequest request,
      @PathVariable("transcriptionId") Long transcriptionId,
      @LoginMember AuthMember member){
    addCommentService.addComment(request, member.getId(), transcriptionId);
    return JsonResponse.ok(HttpStatus.OK, "댓글 등록 성공");
  }

  @PostMapping("/{transcriptionId}/bookmark")
  public ResponseEntity<?> addBookMark(
      @PathVariable("transcriptionId") Long transcriptionId,
      @LoginMember AuthMember member){
    addBookMarkService.addBookMark(member.getId(), transcriptionId);
    return JsonResponse.ok(HttpStatus.OK, "북마크 추가 성공");
  }

  @GetMapping("/{transcriptionId}")
  public ResponseEntity<?> getOne(@PathVariable("transcriptionId") Long transcriptionId,
      @LoginMember AuthMember member
      ){
    TranscriptionDetailDto result = transcriptionQueryService.getDetail(transcriptionId, member.getId());
    return JsonResponse.okWithData(HttpStatus.OK,"필사 글 조회 성공", result);
  }
}
