package com.jie.java8.collect;

import com.jie.bean.Student;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: wyj
 * @date: 2019/10/25
 * @description:
 */
public class GroupTest01 {

    @Test
    public void test01() {
        Student student1 = new Student("aa", 23);
        Student student2 = new Student("ca", 24);
        Student student3 = new Student("da", 22);
        Student student4 = new Student("aa", 28);
        List<Student> list = Arrays.asList(student1, student2, student3, student4);
        Map<String, List<Student>> nameMapping = list.stream().collect(Collectors.groupingBy(Student::getName));
        System.out.println(nameMapping);

    }

    @Test
    public void test02() {
        Student student1 = new Student("aa", 23);
        Student student2 = new Student("ca", 24);
        Student student3 = new Student("da", 22);
        Student student4 = new Student("aa", 28);
        List<Student> list = Arrays.asList(student1, student2, student3, student4);
        Map<String, Long> mapCount = list.stream().collect(Collectors.groupingBy(Student::getName, Collectors.counting()));
        System.out.println(mapCount);
        System.out.println("---------------");
        // 分组然后计算
        Map<String, Double> mapCount1 = list.stream().collect(Collectors.groupingBy(Student::getName, Collectors.averagingInt(Student::getAge)));

        System.out.println(mapCount1);
        /**
         * {aa=2, da=1, ca=1}
         _---------------
         {aa=25.5, da=22.0, ca=24.0}
         */
        System.out.println("---------------");
        // 分区
        System.out.println(list.stream().collect(Collectors.partitioningBy(e -> e.getAge() > 25)));
        /**
         * {false=[Student{name='aa', age=23}, Student{name='ca', age=24}, Student{name='da', age=22}], true=[Student{name='aa', age=28}]}
         */
    }
}