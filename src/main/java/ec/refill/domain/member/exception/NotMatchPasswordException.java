package ec.refill.domain.member.exception;

import ec.refill.common.exception.BusinessException;
import ec.refill.common.exception.ErrorType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotMatchPasswordException extends BusinessException {

  public NotMatchPasswordException() {
    super(ErrorType.NOT_MATCH_PASSWORD);
    log.error("password 불일치");
  }
}
