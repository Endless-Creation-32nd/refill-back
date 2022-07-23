package ec.refill.domain.writing.api;

import ec.refill.common.response.JsonResponse;
import ec.refill.domain.writing.application.WritingQueryService;
import ec.refill.domain.writing.domain.WritingCategory;
import ec.refill.domain.writing.dto.WritingDto;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/writing")
@Validated
@RequiredArgsConstructor
public class WritingApi {

  private final WritingQueryService writingQueryService;

  @GetMapping("")
  public ResponseEntity<?> getByCategory(
      @Positive @RequestParam(value = "count") int count,
      @PositiveOrZero @RequestParam(value = "page") int page,
      @NotBlank @RequestParam(value = "category") String category
  ) {
    List<WritingDto> result = writingQueryService.getWriting(count, page,
        WritingCategory.valueOf(category));
    return JsonResponse.okWithData(HttpStatus.OK, "조회 성공", result);
  }
}
