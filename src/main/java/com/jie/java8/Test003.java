package com.jie.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * @author: wyj
 * @date: 2019/10/17
 * @description:
 */
public class Test003 {
    /**
     * 类型推断:根据参数类型和返回结果的类型推断
     * @param args
     */
    public static void main(String[] args){

        MyInterface01 myInterface01 = ()->{
//            System.out.println("interface1");
        };
        MyInterface02 myInterface02 = ()->{
//            System.out.println("interface2");
        };

        System.out.println(myInterface01.getClass().getInterfaces()[0]);
        System.out.println(myInterface02.getClass().getInterfaces()[0]);
    }

    // 线程的定义
    @Test
    public void test01() {
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"线程执行");
        }).start();
    }

    /**
     * java8小写转大写
     */
    @Test
    public void test02() {
       List<String> list = Arrays.asList("hello","world","hello wyj");
       list.forEach(item-> System.out.println(item.toUpperCase()));

        System.out.println("#########");
       // 放入新的集合
        List<String> list1 = new ArrayList<>();
        list.forEach(item->{
            String s = item.toUpperCase();
            list1.add(s);
        });
        list1.forEach(System.out::println);

        System.out.println("#########");
        //使用stream
        list.stream();//串行流
        list.parallelStream();// 并行流
        // 使用lambda
        list.stream().map(item->item.toUpperCase()).forEach(System.out::println);
        System.out.println("#########");
        //使用方法引用，使用类或者对象都可以进行方法引用； toUpperCase()的参数是调用者
        // toUpperCase是一个实例方法，在真正调用的时候是具体的一个字符串；对应Function中的第一个参数
        /**
         * @FunctionalInterface
           public interface Function<T, R>  T：输入，R：输出
         */
        Function<String,String> function = String::toUpperCase;
        list.stream().map(function).forEach(System.out::println);



    }


}

    @FunctionalInterface
     interface MyInterface01{
        void test01();
     }

    @FunctionalInterface
    interface MyInterface02{
        void test02();
    }
