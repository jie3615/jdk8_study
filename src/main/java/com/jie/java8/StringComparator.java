package com.jie.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author: wyj
 * @date: 2019/10/18
 * @description:
 */
public class StringComparator {
    public static void main(String[] args){

        // java8之前的方式
        List<String> list = Arrays.asList("hello","world","hello wyj");
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(list);
    }
    /**
     * Comparator jdk1.2就有了，在1.8后变成了函数式接口
     */
    @Test
    public void test01() {
        // java8之前的方式
        List<String> list = Arrays.asList("hello","world","hello wyj");
        Collections.sort(list, Comparator.reverseOrder());
        System.out.println(list);
    }

    @Test
    public void test02() {
        // java8之前的方式
        List<String> list = Arrays.asList("hello","world","hello wyj");
        Collections.sort(list, (o1, o2) -> o1.compareTo(o2));
        System.out.println(list);
    }
    /**
     * lambda 表达式左边是参数，右边是方法体
     * 参数的类型可以省略，编译器可以推断出来；
     *
     * lambda表达式的完整语法:
     *  lambda语法
     lambda表达式的基本格式为:( type x1,type x2 )->{ 表达式…};
     而在整个java之中，对于lambda表达式一共定义了有三类语法：
     *
     *
     * express ：表达式风格方法体后没有分号
     * statement：语句方式的最后加分号；
     */

}

