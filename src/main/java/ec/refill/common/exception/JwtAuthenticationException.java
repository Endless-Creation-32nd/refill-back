package ec.refill.common.exception;


public class JwtAuthenticationException extends BusinessException{

  public JwtAuthenticationException(ErrorType errorType,String message) {
    super(errorType, message);
  }
}
