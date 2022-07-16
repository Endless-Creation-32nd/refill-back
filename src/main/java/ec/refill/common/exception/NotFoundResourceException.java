package ec.refill.common.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotFoundResourceException extends BusinessException{

  public NotFoundResourceException(String message) {
    super(ErrorType.NOTFOUND_RESOURCE);
    log.error("not found resource = "+ message);
  }
}
