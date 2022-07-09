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

  public static ResponseEntity<?> fail(HttpStatus status, ErrorType errorType){
    FailResponseDto responseDto = new FailResponseDto(status, errorType);
    return ResponseEntity.status(status)
        .body(responseDto);
  }

}
