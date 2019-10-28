package com.jie.java8.collect;

import com.jie.bean.Student;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: wyj
 * @date: 2019/10/26
 * @description:collect详解
 */
public class CollectTest02 {
    @Test
    public void test01() {
        Student student1 = new Student("aa", 23);
        Student student2 = new Student("ca", 24);
        Student student3 = new Student("da", 22);
        Student student4 = new Student("aa", 28);
        List<Student> list = Arrays.asList(student1, student2, student3, student4);
        List<Student> studentList = list.stream().collect(Collectors.toList());
        studentList.forEach(System.out::println);
        System.out.println("----------------------");
        System.out.println("list->count:" + list.stream().collect(Collectors.counting()));
        System.out.println("list->count:"+list.stream().count());
    }

    //分组后选年龄最小的学生
    @Test
    public void test02() {
        Student student1 = new Student("aa", 23);
        Student student2 = new Student("ca", 24);
        Student student3 = new Student("da", 22);
        Student student4 = new Student("aa", 28);
        List<Student> list = Arrays.asList(student1, student2, student3, student4);
        List<Student> studentList = list.stream().collect(Collectors.toList());
        Map<String, Student> map = studentList.stream().collect(Collectors.groupingBy(Student::getName, Collectors.collectingAndThen(Collectors.minBy(Comparator.comparing(Student::getAge)), Optional::get)));
        System.out.println(map);
    }
}