package ec.refill.common.exception;

import ec.refill.common.response.JsonResponse;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  ResponseEntity<?> handleBusinessException(BusinessException e) {
    return JsonResponse.fail(e.getErrorType());
  }

  @ExceptionHandler(value = {
      MethodArgumentNotValidException.class,
      ConstraintViolationException.class}
  )
  ResponseEntity<?> handleInvalidArgument(Exception e) {
    log.error("400 - Error");
    return JsonResponse.fail(ErrorType.INVALID_INPUT);
  }
}
