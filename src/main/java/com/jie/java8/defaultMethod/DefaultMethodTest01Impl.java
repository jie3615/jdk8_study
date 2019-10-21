package com.jie.java8.defaultMethod;

import org.junit.Test;

/**
 * @author: wyj
 * @date: 2019/10/21
 * @description:
 */
public class DefaultMethodTest01Impl implements DefaultMethodTest01 {
    @Override
    public void myMehtod() {
        System.out.println("DefaultMethodTest01Impl......");
    }

    @Test
    public void test01() {
        myMehtod();
    }
}