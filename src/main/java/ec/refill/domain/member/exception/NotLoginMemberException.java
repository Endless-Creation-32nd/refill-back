package ec.refill.domain.member.exception;

import ec.refill.common.exception.BusinessException;
import ec.refill.common.exception.ErrorType;

public class NotLoginMemberException extends BusinessException {

  public NotLoginMemberException() {
    super(ErrorType.NOT_LOGIN_MEMBER, " 해당 member를 찾을 수 없습니다.");
  }
}
