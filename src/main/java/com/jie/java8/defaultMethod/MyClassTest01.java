package com.jie.java8.defaultMethod;

import org.junit.Test;

/**
 * @author: wyj
 * @date: 2019/10/21
 * @description:
 */

/**
 * 实现类和接口中都定义了mythod方法，会调用哪一个；
 * Java认为实现类比接口的默认方法优先级高，所以会取接口1的实现类的方法；
 */
public class MyClassTest01 extends DefaultMethodTest01Impl implements DefaultMethodTest02 {

    @Test
    public void test02() {
        myMehtod();
    }

}