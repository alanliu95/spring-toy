package com.alan.java8;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TimeDateTest {
    @Mock
    List<String> stringList;

    @Test
    public void mockTest(){
        when(stringList.get(anyInt())).thenReturn("A").thenReturn("B");
        System.out.println(stringList.get(0));
        System.out.println(stringList.get(0));

        when(stringList.get(anyInt())).thenReturn("C");
        System.out.println(stringList.get(0));

        verify(stringList,times(3)).get(anyInt());
    }
    @Test
    public void zonedDateTimeTest(){
        DateTimeFormatter formatter1=DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").withZone(ZoneId.of("GMT"));
        ZonedDateTime dateTime1= ZonedDateTime.parse("2020-09-01T08:00:00+08:00[Asia/Shanghai]");
        DateTimeFormatter formatter2=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("Europe/London"));
//        ZonedDateTime dateTime2=ZonedDateTime.parse("2020-09-01 08:00:00",formatter2);
        System.out.println(dateTime1);
        System.out.println(dateTime1.format(formatter2));
    }

    /**
     * dtf实例如果会解析字符串中时区信息，则生成的ZonedDateTime实例使用该时区
     * 通过 withZone()设置的时区parse时为第二选择，格式时会使用
     */
    @Test
    public void zoneTest(){
        DateTimeFormatter formatter=DateTimeFormatter.ISO_ZONED_DATE_TIME.withZone(ZoneId.of("Europe/Paris"));
        ZonedDateTime time=ZonedDateTime.parse("2020-12-13T12:00:00+00:00[Asia/Tokyo]",formatter);
        System.out.println(time.toInstant());
        System.out.println(time);
        System.out.println(time.format(formatter));

        DateTimeFormatter formatter1=DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.of("Europe/Paris"));
        ZonedDateTime time1=ZonedDateTime.parse("2020-12-13T12:00:00",formatter1);
        System.out.println(time1.toInstant());
        System.out.println(time1);
        System.out.println(time1.format(formatter1));
    }
}
