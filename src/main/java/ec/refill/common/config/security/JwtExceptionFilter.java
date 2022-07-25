package ec.refill.common.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import ec.refill.common.exception.BusinessException;
import ec.refill.common.exception.ErrorType;
import ec.refill.common.response.FailResponseDto;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    try {
      filterChain.doFilter(request, response);
    } catch (BusinessException e) {
      log.error(
          "[Filter Error]" + " status : " + e.getErrorType().getStatusCode() + " Type : "
              + e.getErrorType().getErrorType() +  " Message : " + e.getCustomMessage());
      responseHandle(response, e.getErrorType(), e.getCustomMessage());
    } catch (Exception e) {
      log.error("[Filter Error]" + " status: 500" + " Message : " + e.getMessage());
      responseHandle(response, ErrorType.INTERNAL_SERVER_ERROR, "서버 에러");
    }
  }


  private void responseHandle(HttpServletResponse response, ErrorType errorType, String message)
      throws IOException {
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setCharacterEncoding("UTF-8");
    response.setStatus(errorType.getStatusCode().value());
    response.getWriter()
        .write(objectMapper.writeValueAsString(
            new FailResponseDto(errorType.getStatusCode(), errorType.getErrorType(),message)));
  }
}
