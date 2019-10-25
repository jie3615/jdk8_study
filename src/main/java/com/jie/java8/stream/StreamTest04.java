package com.jie.java8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author: wyj
 * @date: 2019/10/25
 * @description:
 */
public class StreamTest04 {

    // 首字母大写
    @Test
    public void test01() {
        List<String> list = Arrays.asList("hello", "wyj", "hello wyj");
        list.stream().map(item -> item.substring(0, 1).toUpperCase() + item.substring(1)).forEach(System.out::println);
    }
    @Test
    public void test02() {
        List<String> list = Arrays.asList("hello", "wyj", "hello wyj");
        list.stream().map(item ->{
            String s = item.substring(0, 1).toUpperCase() + item.substring(1);
            System.out.println("test");
            System.out.println(s);
            return s;
        });
        /**
         * 结果分析：
         * 无打印结果
         * map是中间操作，是惰性的，在没有遇到终止操作时候不会执行；
         * 最后加上.forEach(System.out::println);终止操作中间的打印才会输出；
         */
    }

    // 流的注意事项
    @Test
    public void test03() {
        IntStream.iterate(0,item->(item+1)%2).distinct().limit(6).forEach(System.out::println); // 不会终止，去重操作是不知道流什么终止的
        IntStream.iterate(0,item->(item+1)%2).limit(6).distinct().forEach(System.out::println);// 先限定流的长度，再去重
    }
}