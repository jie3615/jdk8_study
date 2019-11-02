package com.jie.java8.stream.sourcecode;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author: wyj
 * @date: 2019/11/1
 * @description:
 */
public class StreamSourceCode01 {
    @Test
    public void test01() {
        List<String> listName = Arrays.asList("ajie", "jiege", "wyj", "jiege");
        listName.stream();
    }
}