package com.jie.java8;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

/**
 * @author: wyj
 * @date: 2019/11/2
 * @description:
 */
public class ConsumerTest {
    public void test01(int num,Consumer<Integer> consumer) {
        consumer.accept(num);
    }

    @Test
    public void test02() {
        Consumer<Integer> consumer = integer -> System.out.println(integer);
        IntConsumer intConsumer = integer -> System.out.println(integer);

        System.out.println(consumer instanceof Consumer);
        System.out.println(intConsumer instanceof Consumer);
        test01(100,consumer); // 传递的是对象
        test01(200,consumer::accept);// 方法引用，即lambda表达式

//        test01(100,intConsumer); 会报错，传递的是对象
        test01(200,intConsumer::accept);// 传递的是行为，也是我们的lambda表达式，所以可以成功；
    }
}