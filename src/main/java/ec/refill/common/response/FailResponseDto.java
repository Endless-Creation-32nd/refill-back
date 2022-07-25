package ec.refill.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FailResponseDto {

  private final int httpStatus;
  private final String errorType;
  private final String message;

  public FailResponseDto(HttpStatus httpStatus, String errorType, String message) {
    this.httpStatus = httpStatus.value();
    this.errorType = errorType;
    this.message = message;
  }
}
