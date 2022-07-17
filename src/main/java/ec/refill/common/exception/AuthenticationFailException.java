package ec.refill.common.exception;


public class AuthenticationFailException extends BusinessException{

  public AuthenticationFailException() {
    super(ErrorType.AUTHENTICATION_FAIL);
  }
}
