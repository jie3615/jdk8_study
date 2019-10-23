package com.jie.java8.stream;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: wyj
 * @date: 2019/10/22
 * @description:
 */
public class StreamTest02 {

    @Test
    public void test01() {

//      List<String> list = Arrays.asList("hello", "wyj", "hello wyj");
        Stream<String> stream = Stream.of("hello", "wyj", "hello wyj");
        // lambda表达式
//      String[] strings = stream.toArray(length -> new String[length]);
        // 方法引用
        String [] strings = stream.toArray(String[]::new);
        Arrays.asList(strings).forEach(System.out::println);
    }

    @Test
    public void test02() {
        Stream<String> stream = Stream.of("hello", "wyj", "hello wyj");
        stream.collect(Collectors.toList()).forEach(item-> System.out.println(item));
        System.out.println("###########");
        // collect 参数展开的形式
        //
        /**
         * 第一个参数返回结果对象；
         * 第二个参数：累加器，每一个元素都添加到list；
         * 第三个参数，把上一步得到的每一个list都添加到list1中；
         */
        List<String> listRes = stream.collect(() -> new ArrayList<>(), (list, item) -> list.add(item), (list1, list2) -> list1.addAll(list2));
        listRes.forEach(System.out::println);

        /**
         * <R> R collect(Supplier<R> supplier,BiConsumer<R, ? super T> accumulator,BiConsumer<R, R> combiner);
         *  supplier：要返回的结果。。。
         */
    }

    @Test
    public void test03() {
        Stream<String> stream = Stream.of("hello", "wyj", "hello wyj");
        /**
         * 第一个参数返回结果对象；
         * 第二个参数：累加器，每一个元素都添加到list；
         * 第三个参数，把上一步得到的每一个list都添加到list1中；
         */
        // 使用LinkList 方法引用模式
        List<String> listRes = stream.collect(LinkedList::new, LinkedList::add, LinkedList::addAll); // 构造方法引用，方法引用
        listRes.forEach(System.out::println);

        /**
         * <R> R collect(Supplier<R> supplier,BiConsumer<R, ? super T> accumulator,BiConsumer<R, R> combiner);
         *  supplier：要返回的结果。。。
         */
    }

    @Test
    public void test04() {
        Stream<String> stream = Stream.of("hello", "wyj", "hello wyj","wyj");
        // toCollection用法
        List<String> list = stream.collect(Collectors.toCollection(ArrayList::new));
        list.forEach(System.out::println);
        System.out.println("#############");



    }

    @Test
    public void test05() {
        Stream<String> stream = Stream.of("hello", "wyj", "hello wyj","wyj");


        // 去重
        Set<String> set = stream.collect(Collectors.toSet());
        System.out.println(set);
        System.out.println("#############");


    }

    @Test
    public void test06() {
        Stream<String> stream = Stream.of("hello", "wyj", "hello wyj","wyj");
        // 去重并排序
        TreeSet<String> treeSet = stream.collect(Collectors.toCollection(TreeSet::new));
        System.out.println(treeSet);
        System.out.println("#############");


    }

    @Test
    public void test07() {
        Stream<String> stream = Stream.of("hello", "wyj", "hello wyj","wyj");

        // 拼接
        String s = stream.collect(Collectors.joining(",")).toString();
        System.out.println(s);

    }


}