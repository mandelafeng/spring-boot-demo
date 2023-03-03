package com.cfhui;

import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * @author cfhui
 * @version V1
 * @description TODO
 * @date 2023/3/3 下午 2:50
 */
public class LocalDateTimeTest {

    @Test
    public void test1() {
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
    public void test2() {
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
    public void test3() {
        LocalTime localTime = LocalTime.now();
        System.out.println("localTime = " + localTime);
    }
    @Test
    public void test4() {
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

}
