package ec.refill.domain.member.exception;

import ec.refill.common.exception.BusinessException;
import ec.refill.common.exception.ErrorType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DuplicateNicknameException extends BusinessException {

  public DuplicateNicknameException() {
    super(ErrorType.DUPLICATED_NICKNAME);
    log.error("닉네임 중복!");
  }
}
