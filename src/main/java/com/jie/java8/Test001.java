package com.jie.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author: wyj
 * @date: 2019/10/16
 * @description:
 */
public class Test001 {
    public static void main(String[] args){

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("##########");
        for (Integer integer : list) {
            System.out.println(integer);
        }
        System.out.println("##########");
        // jdk8 的写法；
        /**
         * e 不用声明类型，是编译器进行了类型推断，如果函数式接口的抽象方法没有参数则不能省略括号，否则可以；
         * 如下可以写成：
          list.forEach( (Integer e)->{
             System.out.println(e);
           });

         list.forEach( (e)->{
         System.out.println(e);
         });
         *
         */
        list.forEach( e->{
            System.out.println(e);
        });

        System.out.println("##########");
        /**
         * Consumer:函数式接口：一个接口有且只有一个抽象方法
         */
        list.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });

        System.out.println("##########");
        // 使用方法引用创建lambda表达式
        list.forEach(System.out::println);

    }
}