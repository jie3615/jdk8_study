package com.jie.java8.time;

import org.junit.Test;

import javax.xml.ws.RequestWrapper;
import java.time.*;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author: wyj
 * @date: 2019/11/8
 * @description:
 */
public class Test01 {

    @Test
    public void test01() {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.toString());
        System.out.println(localDate.getYear());
        System.out.println("******************");
        LocalDate localDate1 = LocalDate.of(1992, 1, 28);
        System.out.println(localDate1);
        System.out.println("****************");
        LocalDate localDate2 = LocalDate.of(2011, 6, 1);
        MonthDay monthDay = MonthDay.of(localDate2.getMonth(), localDate2.getDayOfMonth());
        System.out.println(monthDay);
        MonthDay monthDay1 = MonthDay.from(LocalDate.of(2011, 6, 1));
        System.out.println(monthDay1);
        System.out.println(monthDay.equals(monthDay1)); // 同一天
        LocalDate localDate3 = localDate.plusDays(10).plusMonths(1);
        System.out.println(localDate3);
    }

    @Test
    public void test02() {

        LocalDate localDate = LocalDate.now();
        LocalDate localDate1 = localDate.plusWeeks(2);
        System.out.println(localDate1);
    }

    @Test
    public void test03() {
        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock);
    }

    @Test
    public void test4() {
        Set<String> stringSet = ZoneId.getAvailableZoneIds();
        System.out.println(stringSet);

        Set<String> treeSet = new TreeSet<String>(){
            {
                addAll(stringSet);
            }
        };
        System.out.println(treeSet);
    }

    @Test
    public void test05() {
        YearMonth yearMonth = YearMonth.of(2019, 11);
        System.out.println(yearMonth);
        System.out.println(yearMonth.getYear());

        LocalDate localDate = LocalDate.now();
        LocalDate localDate1 = LocalDate.of(2011, 3, 4);
        Period period = Period.between(localDate1, localDate);
        System.out.println(period.getDays());
        System.out.println(period.getYears());
    }
}