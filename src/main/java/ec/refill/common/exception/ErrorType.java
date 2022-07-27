package ec.refill.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorType {
  /*
  *  error Type : client 가 식별하는 값
  */
  //400
  INVALID_INPUT_BODY("E001", HttpStatus.BAD_REQUEST),
  INVALID_INPUT_HEADER("E002", HttpStatus.BAD_REQUEST),

  //401
  EXPIRED_JWT("E010", HttpStatus.UNAUTHORIZED),
  INVALID_JWT("E011",HttpStatus.UNAUTHORIZED),
  NOT_LOGIN_MEMBER("E012",HttpStatus.UNAUTHORIZED),

  //403
  AUTHORIZATION_FAIL("E013", HttpStatus.FORBIDDEN),

  //404
  NOT_FOUND_RESOURCE("E014", HttpStatus.NOT_FOUND),

  //409 : business error
  DUPLICATED_NICKNAME("E020", HttpStatus.CONFLICT),
  DUPLICATED_EMAIL("E021", HttpStatus.CONFLICT),
  NOT_MATCH_PASSWORD("E022", HttpStatus.CONFLICT),
  NOT_MATCH_AUTH("E023",HttpStatus.CONFLICT),
  ALREADY_PARTICIPATE_GROUP("E024", HttpStatus.CONFLICT),
  GROUP_MEMBER_LIMITED("E025", HttpStatus.CONFLICT),
  NOT_PARTICIPATE_GROUP("E026", HttpStatus.CONFLICT),
  //500
  INTERNAL_SERVER_ERROR("E999", HttpStatus.INTERNAL_SERVER_ERROR);

  private final String errorType;
  private final HttpStatus statusCode;

  ErrorType(String errorMessage, HttpStatus statusCode) {
    this.errorType = errorMessage;
    this.statusCode = statusCode;
  }
}
