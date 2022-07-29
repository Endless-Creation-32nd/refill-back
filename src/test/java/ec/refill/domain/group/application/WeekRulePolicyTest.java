package ec.refill.domain.group.application;

import static org.junit.jupiter.api.Assertions.*;

import ec.refill.domain.group.vo.PeriodVo;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekRulePolicyTest {

  @Test
  @DisplayName("현재 시간의 요일 가져오기")
  void getDayOfWeek(){
//    LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    // 2022년 7월 29 일 5시 30분 => 금요일
    LocalDateTime now = LocalDateTime.of(2022, 7,29,5,30);

    DayOfWeek dayOfWeek = now.getDayOfWeek();
    int dayOfWeekNumber = dayOfWeek.getValue();

    // 월 ~ 일 1 ~ 7, 금요일은 5가 나와야 한다.
    assertEquals(dayOfWeekNumber, 5);
  }

  @Test
  @DisplayName("현재 시간의 지난주 월요일 00시 가져오기")
  void getDayOfLastWeekMonDay(){
    // 2022년 7월 3 일 5시 30분 => 일요일
    LocalDateTime now = LocalDateTime.of(2022, 7,3,5,30);

    DayOfWeek dayOfWeek = now.getDayOfWeek();
    //현재 요일(index, 1~5)
    int dayOfWeekNumber = dayOfWeek.getValue();
    // 원하는 날( 지난주 월요일) 00시 00분
    LocalDateTime lastWeekMonDay = now.minusDays(dayOfWeekNumber+6).withHour(0).withMinute(0);
    LocalDateTime expectedDay = LocalDateTime.of(2022,6,20,0,0);
    assertEquals(lastWeekMonDay, expectedDay);
  }

  @Test
  @DisplayName("현재 시간 기준 지난주 월 자정과 이번주 월 자정 이 떠야 한다.")
  public void weekRulePolicySuccess() throws Exception {
    WeekRulePolicy weekRulePolicy = new WeekRulePolicy();
    //22-7-6-5:30
    LocalDateTime now = LocalDateTime.of(2022,7,6,5,30);

    LocalDateTime expectedStartTime = LocalDateTime.of(2022,6,27,0,0);
    LocalDateTime expectedEndTime = LocalDateTime.of(2022,7,4,0,0);

    PeriodVo period = weekRulePolicy.penaltyTime(now);

    assertEquals(expectedStartTime, period.getStartTime());
    assertEquals(expectedEndTime, period.getEndTime());
  }
}