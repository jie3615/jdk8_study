package com.jie.java8.collect;

import org.junit.Test;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author: wyj
 * @date: 2019/10/28
 * @description:自定义收集器 ，结果封装到set中
 */
public class MyCollector<T> implements Collector<T,Set<T>,Set<T>> {
    @Override
    public Supplier<Set<T>> supplier() { // 用于提供一个空的容器，被accumulator调用的空的结果容器
        System.out.println("invoke supplier...");
        return HashSet::new;
    }

    // 接受两个参数，不返回值，
    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("invoke accumulator...");
//      return Set<T>::add;
//      return Set::add;
        return (set, item) -> set.add(item);
    }

    @Override
    public BinaryOperator<Set<T>> combiner() {
        System.out.println("invoke combiner...");
        return (set1,set2)->{
             set1.addAll(set2);
             return set1;
        };
    }

    @Override
    public Function<Set<T>, Set<T>> finisher() {

        System.out.println("invoke finisher...");
//        return t -> t;
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("invoke characteristics...");
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH,Characteristics.UNORDERED));
    }

    /**
     * <R, A> R collect(Collector<? super T, A, R> collector);
     * T:流中的元素
     * A:中间结果
     * R:结果
     */

    @Test
    public void test01() {
        List<String> list  = Arrays.asList("hello", "wyj", "nihao wyj","jie","jie");
        Set<String> resSet = list.stream().collect(new MyCollector<>());
        System.out.println(resSet);
    }
}