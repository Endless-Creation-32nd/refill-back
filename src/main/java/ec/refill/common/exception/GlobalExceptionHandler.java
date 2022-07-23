package ec.refill.common.exception;

import ec.refill.common.response.JsonResponse;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  ResponseEntity<?> handleBusinessException(BusinessException e) {
    log.error("[Business Error] " + "status : " + e.getErrorType().getStatusCode() + " Type : "
        + e.getErrorType().getErrorMessage() + " message : " + e.getCustomMessage());
    return JsonResponse.fail(e.getErrorType());
  }

  @ExceptionHandler(value = {
      MethodArgumentNotValidException.class,
      ConstraintViolationException.class}
  )
  ResponseEntity<?> handleInvalidArgument(Exception e) {
    log.error("[Handling Error] "+ "status : 400 " +  "Message : "+ e.getMessage());
    return JsonResponse.fail(ErrorType.INVALID_INPUT);
  }

  @ExceptionHandler(Exception.class)
  ResponseEntity<?> handleUnknownError(Exception e) {
    log.error("[Unknown Error] "+ "status : 500 " +  "Message : "+ e.getMessage());
    return JsonResponse.fail(ErrorType.INVALID_INPUT);
  }
}
