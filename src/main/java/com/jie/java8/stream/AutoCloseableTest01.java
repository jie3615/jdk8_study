package com.jie.java8.stream;

/**
 * @author: wyj
 * @date: 2019/10/29
 * @description:资源的自动关闭
 */
public class AutoCloseableTest01 implements AutoCloseable {
    @Override
    public void close() throws Exception {
        System.out.println("invoke close...");

    }

    public void doSomething() {
        System.out.println("do something...");
    }

    public static void main(String[] args){

        try(AutoCloseableTest01 autoCloseableTest01 = new AutoCloseableTest01()) {
            autoCloseableTest01.doSomething();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}