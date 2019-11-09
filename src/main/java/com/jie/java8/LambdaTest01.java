package com.jie.java8;

import org.junit.Test;

/**
 * @author: wyj
 * @date: 2019/11/5
 * @description:
 */
public class LambdaTest01 {

    @Test
    public void test01() {
        Runnable runnable1 = () -> System.out.println(this);
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                System.out.println(this);
            }
        };
        Thread t1 = new Thread(runnable1);
        t1.start();
        System.out.println("#########");

        Thread t2 = new Thread(runnable2);
        t2.start();
    }
}