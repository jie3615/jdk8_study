package com.jie.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author: wyj
 * @date: 2019/10/19
 * @description:函数式接口Predicate用于做判断
 */
public class PredicateTest01 {

    @Test
    public void test01() {
        // 给定条件，判断是否符合条件
        Predicate<String> predicate = str -> str.length() > 5;
        System.out.println(predicate.test("wyj"));
        // 字符串长度是否大于5，返回false;
    }

    @Test
    public void test02() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        //根据不同条件查询不同的数字集合；
        /*PredicateTest01 predicateTest01  = new PredicateTest01();
        Predicate<Integer> predicate = item -> item % 2 == 0;*/
        //打印偶数
        conditonFilter(list, item -> item % 2 == 0);
        System.out.println("#################");
        //打印奇数
        conditonFilter(list, item -> item % 2 == 1);

    }

    /**
     * 条件过滤行为当作参数
     *
     * @param list
     * @param predicate
     */
    public void conditonFilter(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer num : list) {
            if (predicate.test(num)) {
                System.out.println(num);
            }
        }

        list = (List<Integer>) list.stream().filter(predicate).collect(Collectors.toList());
    }


    @Test
    public void test03() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        // 大于6并且式偶数
        conditionFilterAnd(list, item -> item % 2 == 0, item -> item > 6);
    }

    /**
     * Predicate 接口的默认方法，与或非
     */
    public void conditionFilterAnd(List<Integer> list, Predicate<Integer> predicate1, Predicate<Integer> predicate2) {
        for (Integer integer : list) {
            if (predicate1.and(predicate2).test(integer)) {
                System.out.println(integer);
            }

        }
    }

    /**
     * Predicate 的静态方法isEqual用法
     */

    Predicate<String> isEqual(Object object) {

        return Predicate.isEqual(object);
    }

    @Test
    public void test003() {

        System.out.println(isEqual("test").test("test"));
        System.out.println(isEqual(new Date()).test("hhhh"));
    }

}