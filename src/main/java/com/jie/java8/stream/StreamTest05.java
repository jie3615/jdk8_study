package com.jie.java8.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author: wyj
 * @date: 2019/10/25
 * @description:
 */
public class StreamTest05 {
    // 3000000个数据排序, 串行流
    @Test
    public void test01() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 3000000; i++) {
            list.add(UUID.randomUUID().toString());
        }
        long start = System.nanoTime();
        long count = list.stream().sorted().count();
        long end = System.nanoTime();
        System.out.println(TimeUnit.NANOSECONDS.toMillis(end-start));
        /**
         * 3420
         */
    }

    // 并行流，多个线程进行排序，fork-join
    @Test
    public void test02() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 3000000; i++) {
            list.add(UUID.randomUUID().toString());
        }
        long start = System.nanoTime();
        long count = list.parallelStream().sorted().count();
        long end = System.nanoTime();
        System.out.println(TimeUnit.NANOSECONDS.toMillis(end-start));
        /**
         * 耗时1548
         */
    }

    /**
     * 流的转换
     */
    @Test
    public void test03() {
        List<String> list = Arrays.asList("wyj","hello","hello wyj","nihaoa");
        list.stream().filter(e->e.length() == 5).findFirst().ifPresent(System.out::println);

        // 打出长度 mapToInt 是转换
        list.stream().mapToInt(e->e.length()).filter(item->item ==5).findFirst().ifPresent(System.out::println);
        System.out.println("###########");
        list.stream().mapToInt(e->{

            System.out.println(e);
            return e.length();
        }).filter(item->item ==5).findFirst().ifPresent(System.out::println);
        /**
         * 结果分析：
         * ###########
             wyj
             hello
             5
         结果是并没有打印出所有的字符串，而是在找到了所需要的元素就会终止循环；
         我们传入的是对流的操作；对每一个元素执行组装好的操作，并非每一个元素分别每一个操作；
         这个例子中当找到了hello符号条件的，就执行短路运算，终止操作
         */
    }

    // 去重
    @Test
    public void test04() {
        List<String> list = Arrays.asList("wyj","hello nihao","hello wyj","wyj nihao");


        list.stream().map(item -> item.split(" ")).distinct().collect(Collectors.toList()).forEach(it->{
            for (int i = 0; i < it.length; i++) {
                System.out.println(it[i]);
            }
            System.out.println("----------");
        });
        /**
         * 结果分析
         * ----------
         hello
         nihao
         ----------
         hello
         wyj
         ----------
         wyj
         nihao
         ----------
         *  返回的数组对象,然后对数组去重，内容还是有重复
         */


        list.stream().map(item->item.split(" ")).flatMap(Arrays::stream).distinct().collect(Collectors.toList()).forEach(System.out::println);
        /**
         * 执行结果：
         *   wyj
             hello
             nihao
         使用flatMap将Stream(String []) 转化为Stream（String）

         */
    }

    // 实现交叉打招呼，使用listSay每一个元素跟listName中每一个元素组合
    @Test
    public void test05() {
        List<String> listSay = Arrays.asList("nihao", "hello", "hi");
        List<String> listName = Arrays.asList("ajie", "jiege", "wyj", "jiege");
        listSay.stream().map(say -> listName.stream().map(name -> say + " " + name)).collect(Collectors.toList()).forEach(System.out::println);
        /**
         * java.util.stream.ReferencePipeline$3@6d9c638
         java.util.stream.ReferencePipeline$3@7dc5e7b4
         java.util.stream.ReferencePipeline$3@1ee0005
         */

        System.out.println("###########");

        listSay.stream().flatMap(say -> listName.stream().map(name -> say + " " + name)).collect(Collectors.toList()).forEach(System.out::println);
        /**
         * nihao ajie
         nihao jiege
         nihao wyj
         nihao jiege
         hello ajie
         hello jiege
         hello wyj
         hello jiege
         hi ajie
         hi jiege
         hi wyj
         hi jiege

         */

    }
}