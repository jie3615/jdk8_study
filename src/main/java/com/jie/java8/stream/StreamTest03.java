package com.jie.java8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
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

    //  找出流中大于2的元素，然后将每个元素乘以2，然后忽略掉流中的前两个元素，然后取出流中的前两个元素，最后求出流中元素的总和；
    @Test
    public void test06() {
        List<Integer> list = Stream.iterate(1, item -> item + 2).limit(6).collect(Collectors.toList());
        System.out.println(list.stream().filter(e -> e > 2).mapToInt(e -> e * 2).skip(2).limit(2).sum());

        List<Integer> list1 = Stream.iterate(1, item -> item + 2).limit(6).collect(Collectors.toList());
        list1.stream().filter(e -> e > 2).mapToInt(e -> e * 2).skip(2).limit(2).max().ifPresent(System.out::println); // max返回OptionalInt类型，可以使用Optional中的方法
    }

    // 一次性取出最大值，最小值和总和，使用IntSummaryStatistics
    @Test
    public void test07() {
        List<Integer> list = Stream.iterate(1, item -> item + 2).limit(6).collect(Collectors.toList());
       IntSummaryStatistics summaryStatistics  = list.stream().filter(e -> e > 2).mapToInt(e -> e * 2).skip(2).limit(2).summaryStatistics();
        System.out.println(summaryStatistics.getMin());
        System.out.println(summaryStatistics.getSum());
        System.out.println(summaryStatistics.getMax());

    }
    @Test
    public void test08() {
        List<Integer> list = Stream.iterate(1, item -> item + 2).limit(6).collect(Collectors.toList());
        Stream<Integer> stream = list.stream();
        stream.distinct();
        stream.filter(e -> e > 2);
        /**
         * 执行报错：流用过就不能继续使用
         * java.lang.IllegalStateException: stream has already been operated upon or closed
         at java.util.stream.AbstractPipeline.<init>(AbstractPipeline.java:203)
         at java.util.stream.ReferencePipeline.<init>(ReferencePipeline.java:94)
         at java.util.stream.ReferencePipeline$StatelessOp.<init>(ReferencePipeline.java:618)
         at java.util.stream.ReferencePipeline$2.<init>(ReferencePipeline.java:163)
         at java.util.stream.ReferencePipeline.filter(ReferencePipeline.java:162)
         at com.jie.java8.stream.StreamTest03.test08(StreamTest03.java:82)
         */

        //重新获取流
        list.stream().distinct();
        list.stream().filter(e -> e > 2);

    }

}