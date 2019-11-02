package com.jie.java8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author: wyj
 * @date: 2019/10/30
 * @description:
 */
public class StreamTest06 {

    // 异常压制
    @Test
    public void test01() {
        List<String> listName = Arrays.asList("ajie", "jiege", "wyj", "jiege");

        try (Stream<String> stream = listName.stream();){
            stream .onClose(()->{
                System.out.println("666");
                throw new NullPointerException("first exception");
            }).onClose(()->{
                System.out.println("888");
                throw new NullPointerException("seconed exception");
            }).forEach(System.out::println);
        }
        /**
         * java.lang.NullPointerException: first exception

         at com.jie.java8.stream.StreamTest06.lambda$test01$0(StreamTest06.java:23)
         at java.util.stream.Streams$1.run(Streams.java:850)
         at java.util.stream.AbstractPipeline.close(AbstractPipeline.java:323)
         at com.jie.java8.stream.StreamTest06.test01(StreamTest06.java:28)
         at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
         at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
         at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
         at java.lang.reflect.Method.invoke(Method.java:498)
         at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:47)
         at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
         at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:44)
         at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
         at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:271)
         at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:70)
         at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:50)
         at org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)
         at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)
         at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)
         at org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)
         at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)
         at org.junit.runners.ParentRunner.run(ParentRunner.java:309)
         at org.junit.runner.JUnitCore.run(JUnitCore.java:160)
         at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
         at com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:47)
         at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:242)
         at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)
         Suppressed: java.lang.NullPointerException: seconed exception       // 压制
         at com.jie.java8.stream.StreamTest06.lambda$test01$1(StreamTest06.java:26)
         at java.util.stream.Streams$1.run(Streams.java:854)
         ... 24 more

         */


    }

    //  只有一个异常对象，自己不会压制自己；
    @Test
    public void test02() {
        List<String> listName = Arrays.asList("ajie", "jiege", "wyj", "jiege");
        NullPointerException nullPointerException = new NullPointerException("my exception");
        try (Stream<String> stream = listName.stream();) {
            stream.onClose(() -> {
                System.out.println("666");
                throw nullPointerException;
            }).onClose(() -> {
                System.out.println("888");
                throw nullPointerException;
            }).forEach(System.out::println);
        }
        /**
         *
         java.lang.NullPointerException: my exception

         at com.jie.java8.stream.StreamTest06.test02(StreamTest06.java:72)
         at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
         at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
         at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
         at java.lang.reflect.Method.invoke(Method.java:498)
         at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:47)
         at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
         at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:44)
         at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
         at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:271)
         at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:70)
         at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:50)
         at org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)
         at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)
         at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)
         at org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)
         at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)
         at org.junit.runners.ParentRunner.run(ParentRunner.java:309)
         at org.junit.runner.JUnitCore.run(JUnitCore.java:160)
         at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
         at com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:47)
         at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:242)
         at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)
         */
    }

}