package ec.refill.domain.transcription.exception;

import ec.refill.common.exception.BusinessException;
import ec.refill.common.exception.ErrorType;

public class AlreadyBookMarkException extends BusinessException {

  public AlreadyBookMarkException(
      String customMessage) {
    super(ErrorType.ALREADY_BOOK_MARK, customMessage);
  }
}
