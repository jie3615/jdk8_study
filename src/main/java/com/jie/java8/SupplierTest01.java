package com.jie.java8;

import com.jie.bean.Student;
import org.junit.Test;

import java.util.function.Supplier;

/**
 * @author: wyj
 * @date: 2019/10/19
 * @description:Supplier 设计和用法，不接收参数，同时返回一个结果；
 */
public class SupplierTest01 {

    @Test
    public void test01() {
        Supplier<String> supplier = () -> "test";
        System.out.println(supplier.get());
    }

    @Test
    public void test02() {
        Supplier<Student> supplier = () -> new Student();
        Supplier<Student> supplier1 =  Student::new; //通过方法引用
        System.out.println(supplier.get());
        System.out.println(supplier1.get());

        Supplier<Student> supplier3 = () -> new Student("hello", 12);
        System.out.println(supplier3.get());
    }
}