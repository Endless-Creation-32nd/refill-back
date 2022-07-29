package ec.refill.domain.group.exception;

import ec.refill.common.exception.BusinessException;
import ec.refill.common.exception.ErrorType;

public class NotSatisfiedPenaltyPolicyException extends BusinessException {

  public NotSatisfiedPenaltyPolicyException(
      String customMessage) {
    super(ErrorType.NOT_SATISFIED_PENALTY_POLICY, customMessage);
  }
}
