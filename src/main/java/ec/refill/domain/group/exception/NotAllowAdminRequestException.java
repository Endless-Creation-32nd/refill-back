package ec.refill.domain.group.exception;

import ec.refill.common.exception.BusinessException;
import ec.refill.common.exception.ErrorType;

public class NotAllowAdminRequestException extends BusinessException {

  public NotAllowAdminRequestException(
      String customMessage) {
    super(ErrorType.NOT_ALLOW_ADMIN_REQUEST, customMessage);
  }
}
