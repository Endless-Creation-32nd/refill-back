package ec.refill.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import ec.refill.common.exception.AuthenticationFailException;
import ec.refill.common.exception.ErrorType;
import ec.refill.common.exception.InvalidInputException;
import ec.refill.common.response.FailResponseDto;
import ec.refill.common.response.JsonResponse;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    try{
      filterChain.doFilter(request,response);
    }catch (InvalidInputException e){
      responseHandle(response, e.getErrorType());
    }catch (AuthenticationFailException e){
      responseHandle(response, e.getErrorType());
    }catch (Exception e) {
      responseHandle(response, ErrorType.INTERNAL_SERVER_ERROR);
    }
  }


  private void responseHandle(HttpServletResponse response, ErrorType errorType)
      throws IOException {
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setCharacterEncoding("UTF-8");
    response.setStatus(errorType.getStatusCode().value());
    response.getWriter()
        .write(objectMapper.writeValueAsString(new FailResponseDto(errorType.getStatusCode(), errorType.getErrorMessage())));
  }
}
