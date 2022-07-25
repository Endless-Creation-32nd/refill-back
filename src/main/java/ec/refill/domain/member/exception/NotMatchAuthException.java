package ec.refill.domain.member.exception;

import ec.refill.common.exception.BusinessException;
import ec.refill.common.exception.ErrorType;

public class NotMatchAuthException extends BusinessException {

  public NotMatchAuthException(
      String customMessage) {
    super(ErrorType.NOT_MATCH_AUTH, customMessage);
  }
}
