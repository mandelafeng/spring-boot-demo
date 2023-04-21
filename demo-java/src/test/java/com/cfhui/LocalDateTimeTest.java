package com.cfhui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @author cfhui
 * @version V1
 * @description TODO
 * @date 2023/3/3 下午 2:50
 */
class LocalDateTimeTest {

    @Test
    void test1() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now = " + now);
        LocalDateTime dayPlus2 = now.plus(2, ChronoUnit.DAYS);
        System.out.println("dayPlus2 = " + dayPlus2);
        LocalDateTime hoursPlus3 = now.plus(Duration.ofHours(3));
        System.out.println("hoursPlus3 = " + hoursPlus3);
        LocalDateTime yearPlus1 = now.plus(Period.ofYears(1));
        System.out.println("yearPlus1 = " + yearPlus1);
        LocalDateTime plusHours1 = now.plusHours(1);
        System.out.println("plusHours1 = " + plusHours1);
        int dayOfMonth = now.getDayOfMonth();
        System.out.println("dayOfMonth = " + dayOfMonth);
        DayOfWeek dayOfWeek = now.getDayOfWeek();
        System.out.println("dayOfWeek = " + dayOfWeek);
    }

    @Test
    void test2() {
        LocalDate now1 = LocalDate.now();
        System.out.println("now1 = " + now1);
        boolean leapYear = now1.isLeapYear();
        System.out.println("leapYear =  " + leapYear);
        LocalDate localDate = now1.plusYears(1);
        System.out.println(localDate.isLeapYear());
        int i = now1.lengthOfMonth();
        System.out.println("i = " + i);


    }
    @Test
    void test3() {
        LocalTime localTime = LocalTime.now();
        System.out.println("localTime = " + localTime);
    }
    @Test
    void test4() {
        LocalDate date = LocalDate.of(1998, 3, 20);
        System.out.println("date = " + date.toString());
        LocalDate localDate = LocalDate.ofYearDay(1994, 222);
        System.out.println("localDate = " + localDate);
        LocalDate parse = LocalDate.parse("2022-03-15");
        System.out.println(parse);
        LocalDate max = LocalDate.MAX;
        System.out.println("max = " + max);
        LocalDate min = LocalDate.MIN;
        System.out.println("min = " + min);
    }

    @Test
    @DisplayName("日期调整器相关设置")
    void test6() {
      LocalDate now = LocalDate.now();
      System.out.println(now);
      // 查找每月的第一天或最后一天
      LocalDate firstDayOfMonth = now.with(TemporalAdjusters.firstDayOfMonth());
      System.out.println(firstDayOfMonth);

      LocalDate lastDayOfMonth = now.with(TemporalAdjusters.lastDayOfMonth());
      System.out.println(lastDayOfMonth);
      // 查找下个月的第一天
      LocalDate firstDayOfNextMonth = now.with(TemporalAdjusters.firstDayOfNextMonth());
      System.out.println(firstDayOfNextMonth);
      // 查找一年的第一天或最后一天
      LocalDate firstDayOfYear = now.with(TemporalAdjusters.firstDayOfYear());
      System.out.println(firstDayOfYear);

      LocalDate lastDayOfYear = now.with(TemporalAdjusters.lastDayOfYear());
      System.out.println(lastDayOfYear);
      // 寻找明年的第一天
      LocalDate firstDayOfNextYear = now.with(TemporalAdjusters.firstDayOfNextYear());
      System.out.println(firstDayOfNextYear);
      // 查找一个月内的第一天或最后一天，例如“当前月的第二个星期五”
      LocalDate dayOfWeekInMonth = now.with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.FRIDAY));
      System.out.println(dayOfWeekInMonth);
      // 查找一周中的下一天或前一天，例如“上周五”
      LocalDate previousDayOfWeek = now.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
      System.out.println(previousDayOfWeek);
      // 下周五
      LocalDate nextDayOfWeek = now.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
      System.out.println(nextDayOfWeek);
    }

  @Test
  @DisplayName("date LocalDateTime 相互转换")
  void test7() {
    // date2LocalDateTime
    Date date = new Date();
    ZoneId zoneId = ZoneId.systemDefault();
    LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), zoneId);
    System.out.println(localDateTime);

    // localDateTime2Date
    LocalDateTime now = LocalDateTime.now();
    Date from = Date.from(now.atZone(zoneId).toInstant());
    System.out.println(from);
  }

}
