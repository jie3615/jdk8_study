package com.jie.java8.defaultMethod;

import org.junit.Test;

/**
 * @author: wyj
 * @date: 2019/10/21
 * @description:
 */
public class DefaultMethodTest implements DefaultMethodTest01, DefaultMethodTest02 {
    // 实现的接口有同名的方法，要进行重写显式的告诉编译器
    @Override
    public void myMehtod() {
//        System.out.println("DefaultMethodTest......");
        DefaultMethodTest02.super.myMehtod();
    }

    @Test
    public void test01() {
        myMehtod();
    }
}