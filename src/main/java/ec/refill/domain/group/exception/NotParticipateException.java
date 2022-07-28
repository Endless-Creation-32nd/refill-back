package ec.refill.domain.group.exception;

import ec.refill.common.exception.BusinessException;
import ec.refill.common.exception.ErrorType;

public class NotParticipateException extends BusinessException {
  public NotParticipateException(
      String customMessage) {
    super(ErrorType.NOT_PARTICIPATE_GROUP, customMessage);
  }
}
