package ec.refill.domain.member.exception;

import ec.refill.common.exception.BusinessException;
import ec.refill.common.exception.ErrorType;

public class DuplicateNicknameException extends BusinessException {

  public DuplicateNicknameException() {
    super(ErrorType.DUPLICATED_NICKNAME);
  }
}
