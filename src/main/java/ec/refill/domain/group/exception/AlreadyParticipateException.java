package ec.refill.domain.group.exception;

import ec.refill.common.exception.BusinessException;
import ec.refill.common.exception.ErrorType;

public class AlreadyParticipateException extends BusinessException {

  public AlreadyParticipateException(
      String customMessage) {
    super(ErrorType.ALREADY_PARTICIPATE_GROUP, customMessage);
  }
}
