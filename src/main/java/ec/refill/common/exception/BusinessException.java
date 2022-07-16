package ec.refill.common.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{

  private final ErrorType errorType;

  public BusinessException() {
    super(ErrorType.INTERNAL_SERVER_ERROR.getErrorMessage());
    this.errorType = ErrorType.INTERNAL_SERVER_ERROR;
  }

  public BusinessException(ErrorType errorType){
    this.errorType = errorType;
  }
}
