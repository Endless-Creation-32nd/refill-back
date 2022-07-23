package ec.refill.common.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{

  private final ErrorType errorType ;
  private final String customMessage;

  public BusinessException(ErrorType errorType, String customMessage){
    super(customMessage);
    this.errorType = errorType;
    this.customMessage = customMessage;
  }
}
