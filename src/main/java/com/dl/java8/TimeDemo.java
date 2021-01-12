package com.dl.java8;

import org.junit.Test;

import java.time.*;

public class TimeDemo {
    @Test
    public void test1(){
        LocalDate date=LocalDate.of(2014,3,18);
        int year=date.getYear(); //2014
        Month month=date.getMonth(); //MARCH
        int day = date.getDayOfMonth(); //18
        DayOfWeek dow = date.getDayOfWeek(); //星期几
        int len = date.lengthOfMonth();//31
        boolean leapYear = date.isLeapYear();//是否是闰年

        LocalDate today=LocalDate.now();

        //一天中的时间
        LocalTime time=LocalTime.of(12,35,12);
        int hour = time.getHour();//12
        int minute = time.getMinute();//35
        int second = time.getSecond();//12

        LocalDate localDate=LocalDate.parse("2019-02-15");
        LocalTime localTime=LocalTime.parse("13:22:25");
        LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(13, 45, 20);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);
        LocalDate date1 = dt1.toLocalDate();
        LocalTime time1 = dt1.toLocalTime();
    }

    @Test
    public void test2(){

    }
}
