package com.jie.java8;

import org.junit.Test;

import java.util.function.Function;

/**
 * @author: wyj
 * @date: 2019/10/18
 * @description:
 */
public class FunctionTest01 {

    // 数字计算处理
    public int calculate(int x, Function<Integer, Integer> function) {
        Integer result = function.apply(x);// x是Fun的输入
        return result;
    }

    @Test
    public void testCalculate() {
        System.out.println(calculate(1, value -> 2 * value));

        System.out.println(calculate(3, value -> 3 + value));

    }
    //  分割#############

    // 数字转字符串
    public String coverToString(int x, Function<Integer, String> function) {
        return function.apply(x);
    }

    @Test
    public void testCoverToString() {
        Function function = value -> value + "hahaha"; // 独立定义
        System.out.println(coverToString(1, value -> value + "hello wyj ！"));
        System.out.println(coverToString(2, function));
    }

}