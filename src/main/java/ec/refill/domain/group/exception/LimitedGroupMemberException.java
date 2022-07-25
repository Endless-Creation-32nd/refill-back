package ec.refill.domain.group.exception;

import ec.refill.common.exception.BusinessException;
import ec.refill.common.exception.ErrorType;

public class LimitedGroupMemberException extends BusinessException {

  public LimitedGroupMemberException(String customMessage) {
    super(ErrorType.GROUP_MEMBER_LIMITED, customMessage);
  }
}
