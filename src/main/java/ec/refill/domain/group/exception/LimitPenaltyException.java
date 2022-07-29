package ec.refill.domain.group.exception;

import ec.refill.common.exception.BusinessException;
import ec.refill.common.exception.ErrorType;

public class LimitPenaltyException extends BusinessException {

  public LimitPenaltyException(
      String customMessage) {
    super(ErrorType.LIMIT_PENALTY, customMessage);
  }
}
