package com.jie.java8.collect;

import org.junit.Test;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author: wyj
 * @date: 2019/10/28
 * @description:输入Set<String>, 返回HashMap<String , String>,eg:Set{hello,wyj}->HashMap{hello-hello,wyj-wyj}
 */
public class MyCollector2<T> implements Collector<T, Set<T>, Map<T, T>> {
    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println("invoke supplier...");
//        return HashSet::new;
        return ()->{
            System.out.println("**********");
            return new HashSet<>();
        };
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("invoke accumulator...");
        return (set, item) ->{ set.add(item);
            System.out.println("accumulator    "+set+Thread.currentThread().getName());
        };
    }

    @Override
    public BinaryOperator<Set<T>> combiner() {
        System.out.println("invoke combiner...");

        return (set1,set2)->{
            System.out.println("set1");
            System.out.println("set2");
            set1.addAll(set2);
            return set1;
        };
    }

    @Override
    public Function<Set<T>, Map<T, T>> finisher() {
        System.out.println("invoke finisher...");

        return set->{
            Map<T, T> map = new HashMap<>();
            set.forEach(item->{
                map.put(item, item);
            });
            return map;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("invoke characteristics...");
//        return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED));
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED,Characteristics.CONCURRENT));

        /**
         * Characteristics.IDENTITY_FINISH, ： 中间结果容器和返回类型一样，此案例不一样
         */

    }

    // 先去重，后生成Map，中间容器和结果容器不一样，区别于MyCollector
    @Test
    public void test01() {
        List<String> list  = Arrays.asList("hello", "wyj", "nihao wyj","jie","jie","a","b","a");
        Map<String, String> map = list.stream().collect(new MyCollector2<>());
        System.out.println(map);

        /**
         * 执行结果：
         invoke supplier...
         invoke accumulator...
         invoke combiner...
         invoke characteristics...
         accumulator    main
         accumulator    main
         accumulator    main
         accumulator    main
         accumulator    main
         accumulator    main
         accumulator    main
         accumulator    main
         invoke characteristics...
         invoke finisher...
         {a=a, b=b, jie=jie, wyj=wyj, hello=hello, nihao wyj=nihao wyj}


         */
    }
    // 并行流
    @Test
    public void test02() {
        for (int i = 0; i < 100; i++) {
            List<String> list = Arrays.asList("hello", "wyj", "nihao wyj", "jie", "jie", "a", "b", "a");
            Map<String, String> map = list.parallelStream().collect(new MyCollector2<>());
            System.out.println(map);
            System.out.println("#########");
        }

        /**
         * 执行结果：打印set的时候会出现并发修改异常；parallelStream是并发流，Characteristics.CONCURRENT这个参数加上之后，会有多个线程同时操作set，同时存在一个线程遍历，另一个
         * 线程执行修改操作。在并发时候不能在累加操作过程中添加其他操作，会引起并发安全问题；
         * Characteristics.CONCURRENT 去掉之后，会每一个线程都有一个中间结果容器，最后执行combiner合并操作；否则不会执行combiner
         *
         ccumulator    [jie, wyj, hello, nihao wyj]ForkJoinPool.commonPool-worker-2
         accumulator    [a, jie, wyj, hello, nihao wyj]main

         java.util.ConcurrentModificationException
         : java.util.ConcurrentModificationException
         at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
         at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
         at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
         at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
         at java.util.concurrent.ForkJoinTask.getThrowableException(ForkJoinTask.java:593)
         at java.util.concurrent.ForkJoinTask.reportException(ForkJoinTask.java:677)
         at java.util.concurrent.ForkJoinTask.invoke(ForkJoinTask.java:735)
         at java.util.stream.ForEachOps$ForEachOp.evaluateParallel(ForEachOps.java:160)
         at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateParallel(ForEachOps.java:174)
         at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:233)
         at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:418)
         at java.util.stream.ReferencePipeline$Head.forEach(ReferencePipeline.java:583)
         at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:496)
         */
    }

    // sequential() 串行流
    // parallel() 并行流
    @Test
    public void test03() {
        List<String> list = Arrays.asList("hello", "wyj", "nihao wyj", "jie", "jie", "a", "b", "a");
        list.stream().sequential().parallel().collect(Collectors.toSet()).forEach(System.out::println);
    }


    /**
     * 如果把这个Characteristics.CONCURRENT参数去掉，会多次打印创建中间结果容器
     */
    @Test
    public void test04() {
            List<String> list = Arrays.asList("hello", "wyj", "nihao wyj", "jie", "jie", "a", "b", "a");
            Map<String, String> map = list.parallelStream().collect(new MyCollector2<>());
            System.out.println(map);
            System.out.println("#########");
        /**
         * 执行结果
         * invoke characteristics...
         **********
         **********
         accumulator    [a]main
        。。。。
         */
    }

}