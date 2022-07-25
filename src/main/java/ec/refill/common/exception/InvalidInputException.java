package ec.refill.common.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvalidInputException extends BusinessException{

  public InvalidInputException(String message) {
    super(ErrorType.INVALID_INPUT_BODY, message);
  }

  public InvalidInputException(ErrorType errorType, String customMessage) {
    super(errorType, customMessage);
  }
}
