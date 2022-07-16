package ec.refill.domain.member.exception;

import ec.refill.common.exception.BusinessException;
import ec.refill.common.exception.ErrorType;

public class DuplicateEmailException extends BusinessException {

  public DuplicateEmailException() {
    super(ErrorType.DUPLICATED_EMAIL);
  }
}
