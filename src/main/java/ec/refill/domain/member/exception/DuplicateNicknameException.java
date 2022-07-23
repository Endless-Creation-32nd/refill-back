package ec.refill.domain.member.exception;

import ec.refill.common.exception.BusinessException;
import ec.refill.common.exception.ErrorType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DuplicateNicknameException extends BusinessException {

  public DuplicateNicknameException() {
    super(ErrorType.DUPLICATED_NICKNAME, "nickname 이 중복됐습니다.");
  }
}
