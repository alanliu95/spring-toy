package com.alan.java8.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class CalendarAndDateTest {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("asia/shanghai"));
        Date time=simpleDateFormat.parse("2020-09-01 00:00:00");
        long seconds=time.getTime();
        System.out.println(seconds);

        Calendar calendar=Calendar.getInstance();
        System.out.println("原始："+calendar);
        calendar.setTimeInMillis(seconds);
        System.out.println("设置时间后："+calendar);
        calendar.setTimeZone(TimeZone.getTimeZone("Europe/london"));
        System.out.println("设置时间,时区后："+calendar);
        calendar.add(Calendar.DATE, 1);
        System.out.println(calendar.getTime());
        check3();
    }
    public static void check(){
        long bj=1563663600000L;
        long gmt=1598918400000L;  //1563634800000L;
        System.out.println(new Date(bj));
        System.out.println(new Date(gmt));
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(gmt);
        LocalDateTime dateTime=LocalDateTime.ofInstant(calendar.toInstant(), ZoneId.systemDefault());
        System.out.println(dateTime);
        LocalDateTime dateTime1=LocalDateTime.ofInstant(calendar.toInstant(), ZoneId.of("Europe/London"));
        System.out.println(dateTime1);
        System.out.println(calendar.toInstant().getEpochSecond());
    }
    public static void check2(){
        DateTimeFormatter formatter1=DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").withZone(ZoneId.of("GMT"));
        ZonedDateTime dateTime1= ZonedDateTime.parse("2020-09-01T08:00:00Z",formatter1);
        DateTimeFormatter formatter2=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("Europe/London"));
        LocalDateTime dateTime2=LocalDateTime.parse("2020-09-01 08:00:00",formatter2);
        System.out.println(dateTime1.format(formatter2));
        System.out.println(dateTime2);
    }
    public static void check3(){
        long bj=1579407300000L;
        System.out.println(new Date(bj));
    }
}
