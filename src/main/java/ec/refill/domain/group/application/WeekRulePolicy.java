package ec.refill.domain.group.application;

import ec.refill.domain.group.vo.PeriodVo;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class WeekRulePolicy implements PenaltyRulePolicy {

  /*
  *  현재 날짜(시간)을 기준으로 지난 주 월~00시, 이번주 월00(=일24시)
  *   을 구해야 한다.
  */
  @Override
  public PeriodVo penaltyTime(LocalDateTime current) {
    // 현재 시간 구하기

    //현재 요일 구하기.
    DayOfWeek dayOfWeek = current.getDayOfWeek();
    int dayOfWeekNumber = dayOfWeek.getValue();

    LocalDateTime lastWeekMonDayMidnight = current.minusDays(dayOfWeekNumber+6).withHour(0).withMinute(0);
    LocalDateTime currentWeekMonDayMidnight = lastWeekMonDayMidnight.plusDays(7);
    return new PeriodVo(lastWeekMonDayMidnight, currentWeekMonDayMidnight);
  }
}
