package ec.refill.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorType {
  /*
  *  error Message : client 가 식별하는 값
  */
  INVALID_INPUT("INVALID_INPUT", HttpStatus.BAD_REQUEST),
  DUPLICATED_NICKNAME("DUPLICATED_NICKNAME", HttpStatus.BAD_REQUEST),
  DUPLICATED_EMAIL("DUPLICATED_EMAIL", HttpStatus.BAD_REQUEST),
  AUTHENTICATION_FAIL("AUTHENTICATION_FAIL", HttpStatus.UNAUTHORIZED),
  INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);

  private final String errorMessage;
  private final HttpStatus statusCode;

  ErrorType(String errorMessage, HttpStatus statusCode) {
    this.errorMessage = errorMessage;
    this.statusCode = statusCode;
  }
}
