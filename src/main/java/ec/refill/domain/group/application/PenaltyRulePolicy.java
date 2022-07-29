package ec.refill.domain.group.application;

import ec.refill.domain.group.vo.PeriodVo;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public interface PenaltyRulePolicy {

  PeriodVo penaltyTime(LocalDateTime current);
}
