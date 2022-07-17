package ec.refill.common.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{

  private final ErrorType errorType ;

  public BusinessException(ErrorType errorType){
    super(errorType.getErrorMessage());
    this.errorType = errorType;
  }
}
