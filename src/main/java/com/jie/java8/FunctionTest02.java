package com.jie.java8;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author: wyj
 * @date: 2019/10/18
 * @description:
 */
public class FunctionTest02 {

    public int calculate01(int x, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        Integer result = function1.compose(function2).apply(x);
        return result;
    }

    public int calculate02(int x, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        Integer result = function1.andThen(function2).apply(x);
        return result;
    }

    @Test
    public void test01() {
        FunctionTest02 functionTest02 = new FunctionTest02();

        System.out.println(calculate01(2, value -> value * 3, value -> value * value));
        // 先执行function2的方法2*2= 4,结果作为参数交给function1的方法4*3= 12；

        System.out.println(calculate02(2, value -> value * 3, value -> value * value));
        // 先执行function1的方法2*3= 6,结果作为参数交给function2的方法6*6= 36；
    }

    /**
     * Function<T, R> ：只能接受一个参数
     * BiFunction<T, U, R>  ：可以接收两个参数T，，一个返回
     */

    // 使用BiFunction处理两个参数加减乘除
    public int calculate03(int x, int y, BiFunction<Integer, Integer, Integer> function) {
        return function.apply(x, y);
    }

    @Test
    public void test02() {
        FunctionTest02 functionTest02 = new FunctionTest02();
        System.out.println(functionTest02.calculate03(1, 3, (a, b) -> a + b));
        System.out.println(functionTest02.calculate03(1, 3, (a, b) -> a - b));
        System.out.println(functionTest02.calculate03(1, 3, (a, b) -> a * b));
        System.out.println(functionTest02.calculate03(1, 3, (a, b) -> a / b));
    }


    // BiFunciton > andThen 用法
    public int calculate04(int x,int y, Function<Integer, Integer> function, BiFunction<Integer, Integer,Integer> biFunction) {
        Integer result = biFunction.andThen(function).apply(x, y);
        return result;
    }
    @Test
    public void test03() {
        FunctionTest02 functionTest02 = new FunctionTest02();
        System.out.println(functionTest02.calculate04(1, 3, a->a*a,(a, b) -> a + b));
        // 先执行biFunction ,1+3=4 ，再执行4*4=16；
        /**
         * BiFunction为什么不存在compose方法，因为执行的结果会交给biFunction本身，biFunction是要提供两个参数 ，而上一步的结果只能返回一个结果；
         */
    }

}