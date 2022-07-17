package ec.refill.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FailResponseDto {

  private final int httpStatus;
  private final String errorType;

  public FailResponseDto(HttpStatus httpStatus, String errorType) {
    this.httpStatus = httpStatus.value();
    this.errorType = errorType;
  }
}
