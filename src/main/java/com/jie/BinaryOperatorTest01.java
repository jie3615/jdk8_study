package com.jie;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.BinaryOperator;

/**
 * @author: wyj
 * @date: 2019/10/19
 * @description:BiFunction得具化
 */
public class BinaryOperatorTest01 {


    public int calculate(int x, int y, BinaryOperator<Integer> binaryOperator) {
        return binaryOperator.apply(x, y);
    }

    @Test
    public void test01() {
        System.out.println(calculate(1, 3, (x, y) -> x + y));
    }

    // BinaryOperator 的静态方法使用minBy

    public String getIWantString(String x, String y, Comparator<String> comparator) {
        return BinaryOperator.minBy(comparator).apply(x, y);
    }


    @Test
    public void test02() {
        // 选取长度短的字符串
        System.out.println(getIWantString("jiege", "ajie", (x, y) -> x.length()-y.length()));

        System.out.println(getIWantString("jiege", "ajie", Comparator.comparingInt(String::length)));
    }


}