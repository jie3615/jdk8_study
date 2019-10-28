package com.jie.java8.campare;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: wyj
 * @date: 2019/10/28
 * @description:
 */
public class CampareTest01 {
    @Test
    public void test01() {
        Stream<String> stream = Stream.of("hello", "wyj", "nihao wyj","ajie");
        // toCollection用法
        List<String> list = stream.collect(Collectors.toCollection(ArrayList::new));
        Collections.sort(list, (item1, item2) -> item1.length() - item2.length());
        System.out.println(list);

    }

    @Test
    public void test02() {
        Stream<String> stream = Stream.of("hello", "wyj", "nihao wyj","ajie");
        // toCollection用法
        List<String> list = stream.collect(Collectors.toCollection(ArrayList::new));
        Collections.sort(list, Comparator.comparingInt(String::length).reversed()); // 方法引用方式， 使用特定的Int类型接口，减少装箱拆箱，提高效率
        System.out.println(list);

    }

    @Test
    public void test03() {
        List<String> list  = Arrays.asList("hello", "wyj", "nihao wyj","jie");

        Collections.sort(list, Comparator.comparingInt((String item)->item.length()).reversed());// 无法进行类型推断的情况，需要类型指定
        System.out.println(list);

    }

    // 两级排序
    @Test
    public void test04() {
        List<String> list  = Arrays.asList("hello", "wyj", "nihao wyj","jie");

        Collections.sort(list, Comparator.comparingInt(String::length).thenComparing(String.CASE_INSENSITIVE_ORDER));
        System.out.println(list);

    }
}