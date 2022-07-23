package ec.refill.common.exception;


public class AuthenticationFailException extends BusinessException{

  public AuthenticationFailException(String message) {
    super(ErrorType.AUTHENTICATION_FAIL, message);
  }
}
