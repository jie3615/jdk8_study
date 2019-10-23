package com.jie.java8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: wyj
 * @date: 2019/10/23
 * @description:
 */
public class StreamTest03 {

    @Test
    public void test01() {
        // 小写转大写，map 映射，Function 输入一个参数，输出一个结果
        List<String> list = Arrays.asList("hello", "wyj", "hello wyj");
        List<String> listCover = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        listCover.forEach(System.out::println);

    }
    // 多个List合并成一个流进行处理
    @Test
    public void test02() {
        List<String> list = Arrays.asList("hello", "wyj", "hello wyj");
        List<String> list1 = Arrays.asList("hhhh");
        System.out.println(Stream.of(list, list1).flatMap(theList -> theList.stream()).collect(Collectors.toList()));

    }

    // 多个List合并成一个流进行处理
    @Test
    public void test03() {
        Stream<List<Integer>> stream = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4));
        stream.flatMap(theList->theList.stream()).map(item->item*item).forEach(System.out::println);
    }

    // generate
    @Test
    public void test04() {
        Stream<String> generate = Stream.generate(UUID.randomUUID()::toString);
//        System.out.println(generate.collect(Collectors.toList()));
        generate.findFirst().ifPresent(System.out::println);
    }

    // 迭代器生成奇数，iterate生成无限流，需要用limit终止
    @Test
    public void test05() {
        Stream.iterate(1,item->item+2).limit(9).forEach(System.out::println);
    }
}