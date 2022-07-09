package ec.refill.common.response;

import ec.refill.common.exception.ErrorType;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
class FailResponseDto {

  private final HttpStatus httpStatus;
  private final ErrorType errorType;

  public FailResponseDto(HttpStatus httpStatus, ErrorType errorType) {
    this.httpStatus = httpStatus;
    this.errorType = errorType;
  }
}
