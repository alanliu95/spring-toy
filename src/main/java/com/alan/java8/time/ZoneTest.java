package com.alan.java8.time;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class ZoneTest {
    static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) {

        LocalDateTime dateTime= LocalDateTime.now();;
        LocalDateTime dateTime1=LocalDateTime.now(ZoneId.of("Europe/London"));
        System.out.println(dateTime.format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println(dateTime1.format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println(dateTime.equals(dateTime1));

        Instant instant=Instant.now();
        long millis=System.currentTimeMillis();
        Date bj1 = new Date(millis);
        System.out.println(dateFormat.format(bj1));
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/London"));  // 设置伦敦时区
        System.out.println(dateFormat.format(bj1));
        LocalDateTime bj=LocalDateTime.ofInstant(instant,ZoneId.systemDefault());
        LocalDateTime london=LocalDateTime.ofInstant(instant,ZoneId.of("Europe/London"));
        System.out.println(String.format("bj:%s,london:%s",bj.format(DateTimeFormatter.ISO_DATE_TIME),london.format(DateTimeFormatter.ISO_DATE_TIME)));
    }
}
