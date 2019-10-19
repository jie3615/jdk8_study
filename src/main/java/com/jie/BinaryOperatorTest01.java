package com.jie;

import org.junit.Test;

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


}