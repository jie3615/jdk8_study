package com.jie.java8;

import org.junit.Test;

/**
 * @author: wyj
 * @date: 2019/10/16
 * @description:
 */
@FunctionalInterface
public interface Test002 {
    void test01();

    //    void test02();
    @Override
    String toString();

    /**
     * 一个接口定义了一个抽象方法test01()；
     * 如果抽象方法是复写的Object类中的方法toString();，那么抽象方法不会加一，依然满足是一个函数式接口；
     * 否则，如果是自己定义了一个抽象接口test02();那么就会导致不符合函数式接口的定义
     */
}

class Test0021{
    public void test02(Test002 test002) {
        System.out.println("调用test01之前");
        test002.test01();
        System.out.println("调用test01之后");
    }
    public static void main(String[] args){

        Test0021 test0021 = new Test0021();
        // 匿名内部类
        test0021.test02(new Test002() {
            @Override
            public void test01() {
                System.out.println("调用test01");
            }
        });
        System.out.println("##########");
        // lambda表达式
        test0021.test02(()->{
            System.out.println("调用test01");
        });

        Test002 test002 = ()->{
            System.out.println("Test002");

        };
        System.out.println(test002.getClass());
        System.out.println(test002.getClass().getSuperclass());
        System.out.println(test002.getClass().getInterfaces()[0]);
        /**
         * 在Java中函数作为参数要依附于对象，这个对象就是函数式接口；
         */
    }
}
