package ec.refill.domain.member.exception;

import ec.refill.common.exception.BusinessException;
import ec.refill.common.exception.ErrorType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DuplicateEmailException extends BusinessException {

  public DuplicateEmailException() {
    super(ErrorType.DUPLICATED_EMAIL);
    log.error("email 중복!");
  }
}
