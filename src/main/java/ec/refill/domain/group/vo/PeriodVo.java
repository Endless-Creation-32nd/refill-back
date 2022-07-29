package ec.refill.domain.group.vo;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PeriodVo {
  private LocalDateTime startTime;
  private LocalDateTime endTime;
}
