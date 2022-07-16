package ec.refill.common.exception;

import ec.refill.common.response.JsonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  ResponseEntity<?> handleBusinessException(BusinessException e){
   return JsonResponse.fail(e.getErrorType());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  ResponseEntity<?> handleInvalidArgument(MethodArgumentNotValidException e){
    return JsonResponse.fail(ErrorType.INVALID_INPUT);
  }
}
