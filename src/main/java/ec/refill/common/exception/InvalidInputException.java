package ec.refill.common.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvalidInputException extends BusinessException{

  public InvalidInputException(String message) {
    super(ErrorType.INVALID_INPUT);
    log.error("invalid error! " + message);
  }

  public InvalidInputException() {
    super(ErrorType.INVALID_INPUT);
    log.error("invalid error!");
  }
}
