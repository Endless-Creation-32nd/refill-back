package ec.refill.domain.image.api;

import ec.refill.common.exception.BusinessException;
import ec.refill.common.exception.ErrorType;
import ec.refill.common.response.JsonResponse;
import ec.refill.domain.image.application.ImageUploadService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/image")
public class ImageApi {

  private final ImageUploadService imageUploadService;

  @PostMapping("")
  public ResponseEntity<?> upload(  MultipartFile file) throws IOException {
    if(file.isEmpty()){
      throw new BusinessException(ErrorType.INVALID_INPUT_BODY, "file이 없습니다.");
    }

    return JsonResponse.okWithData(HttpStatus.CREATED, "이미지 업로드 성공",
        imageUploadService.upload(file));
  }
}
