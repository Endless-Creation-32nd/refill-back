package ec.refill.common.response;

import java.util.ArrayList;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
class SuccessResponseDto {
  private final HttpStatus status;
  private final String message;
  private final Object data;

  public SuccessResponseDto(HttpStatus status, String message, Object data) {
    this.status = status;
    this.message = message;
    this.data = data;
  }

  public SuccessResponseDto(HttpStatus status, String message){
    this.status = status;
    this.message = message;
    this.data = new ArrayList<>();
  }
}
