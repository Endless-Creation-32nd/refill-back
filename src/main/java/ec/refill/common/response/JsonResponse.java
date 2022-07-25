package ec.refill.common.response;


import ec.refill.common.exception.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class JsonResponse {

  public static ResponseEntity<?> ok(HttpStatus status, String message){
    SuccessResponseDto responseDto = new SuccessResponseDto(status, message);
    return ResponseEntity.status(status)
        .body(responseDto);
  }

  public static ResponseEntity<?> okWithData(HttpStatus status, String message, Object data){
    SuccessResponseDto responseDto = new SuccessResponseDto(status, message,data);
    return ResponseEntity.status(status)
        .body(responseDto);
  }

  public static ResponseEntity<?> fail(ErrorType errorType, String message){
    FailResponseDto responseDto = new FailResponseDto(errorType.getStatusCode(), errorType.getErrorType(), message);
    return ResponseEntity.status(errorType.getStatusCode())
        .body(responseDto);
  }

}
