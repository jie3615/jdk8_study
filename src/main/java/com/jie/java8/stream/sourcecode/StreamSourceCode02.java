package com.jie.java8.stream.sourcecode;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author: wyj
 * @date: 2019/11/7
 * @description:
 */
public class StreamSourceCode02 {
    @Test
    public void test01() {
        List<String> listName = Arrays.asList("ajie", "jiege", "wyj", "jiege", "2", "3", "4", "5", "8");
        Stream<String> stream = listName.stream();
        System.out.println("###########");
        Stream<String> stream1 = stream.map(e -> e + "#");
        System.out.println("############");
        stream1.forEach(System.out::println);
    }
}