package ec.refill.domain.member.exception;

import ec.refill.common.exception.BusinessException;
import ec.refill.common.exception.ErrorType;

public class NotLoginMemberException extends BusinessException {

  public NotLoginMemberException() {
    super(ErrorType.NOT_LOGIN_MEMBER);
  }
}
