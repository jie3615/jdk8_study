package com.jie.java8.stream.sourcecode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: wyj
 * @date: 2019/11/1
 * @description:
 */
public class StreamSourceCode01 {

    // 串行流执行顺序确定
    @Test
    public void test01() {
        List<String> listName = Arrays.asList("ajie", "jiege", "wyj", "jiege","2","3","4","5","8");
        List<String> list = new ArrayList<>();
        list.add("dfds");
        listName.stream().map(e->e+"#").forEach(System.out::println);
        /**
         * forEach() 两个实现，一个是针对流源的操作Head，只会处理源调用的串行流
         *  一个是针对流中间操作，RefrencePipeline
         */

    }
    // 并行流执行顺序不确定
    @Test
    public void test02() {
        List<String> listName = Arrays.asList("ajie", "jiege", "wyj", "jiege","2","3","4","5","8");
        listName.parallelStream().forEach(System.out::println);
    }
}