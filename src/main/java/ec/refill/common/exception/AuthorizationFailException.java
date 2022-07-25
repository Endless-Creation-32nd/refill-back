package ec.refill.common.exception;

public class AuthorizationFailException extends BusinessException{

  public AuthorizationFailException( String customMessage) {
    super(ErrorType.AUTHORIZATION_FAIL, customMessage);
  }
}
