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
  NOT_MATCH_PASSWORD("NOT_MATCH_PASSWORD", HttpStatus.BAD_REQUEST),
  AUTHENTICATION_FAIL("AUTHENTICATION_FAIL", HttpStatus.UNAUTHORIZED),
  AUTHORIZATION_FAIL("AUTHORIZATION_FAIL", HttpStatus.FORBIDDEN),
  NOT_FOUND_RESOURCE("NOT_FOUND_RESOURCE", HttpStatus.NOT_FOUND),
  NOT_LOGIN_MEMBER("NOT_LOGIN_MEMBER",HttpStatus.BAD_REQUEST),
  ALREADY_PARTICIPATE_GROUP("ALREADY_PARTICIPATE_GROUP", HttpStatus.BAD_REQUEST),
  INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);

  private final String errorMessage;
  private final HttpStatus statusCode;

  ErrorType(String errorMessage, HttpStatus statusCode) {
    this.errorMessage = errorMessage;
    this.statusCode = statusCode;
  }
}
