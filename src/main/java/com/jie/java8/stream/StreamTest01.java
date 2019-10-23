package com.jie.java8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author: wyj
 * @date: 2019/10/21
 * @description:
 */
public class StreamTest01 {

    // 得到Stream对象的方式
    @Test
    public void test01() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> stream1 = list.stream();

        Stream<Integer> stream2 = Stream.of(1, 3, 5);

        int[] nums = new int[]{2, 4, 6};
        Stream stream3 = Stream.of(nums);
        IntStream stream4 = Arrays.stream(nums);
    }

    @Test
    public void test02() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        IntStream intStream = IntStream.of(1, 2, 3, 4, 5);
        intStream.forEach(System.out::println);

        System.out.println("################");
        IntStream.range(2,6).forEach(System.out::println);
        System.out.println("################");
        IntStream.rangeClosed(2,6).forEach(System.out::println);
    }
    @Test
    public void test03() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(list.stream().map(item -> item * 2).reduce(0, (x, y) -> x + y));
        System.out.println(list.stream().map(item -> item * 2).reduce(0, Integer::sum));// reduce(初始值，操作)
    }

}