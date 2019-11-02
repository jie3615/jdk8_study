package com.jie.java8;

import com.jie.bean.Company;
import com.jie.bean.Employee;
import org.junit.Test;

import java.util.*;

/**
 * @author: wyj
 * @date: 2019/10/19
 * @description:Optional 的用法和设计
 */
public class OptionalTest01 {

    @Test
    public void test01() {

        Optional<String> optional = Optional.of("hello wyj");
        if (optional.isPresent()) {
            System.out.println(optional.get());
        }
        System.out.println("################");
        // 不为空才执行lambda 方法
        optional.ifPresent(item -> System.out.println(item));
        System.out.println("################");
        System.out.println(optional.orElse("jiege"));
    }
    @Test
    public void test02() {

        Optional<String> optional = Optional.ofNullable("666");
        System.out.println(optional.orElse("jiege"));

        System.out.println(optional.orElseGet(()->"hhh"));


        // 如果为null 返回其他值
        Optional<String> optional1 = Optional.ofNullable(null);
        System.out.println(optional1.orElse("jiege"));

        System.out.println(optional1.orElseGet(()->"hhh"));

        /**
         666
         666
         jiege
         hhh
         */
    }

    @Test
    public void test003() {

        Employee employee = new Employee("aa", 21);
        Employee employee1 = new Employee("bb",22);
        List<Employee> employeeList = Arrays.asList(employee, employee1);
        Company company = new Company();
        company.setName("myCompany");
        company.setEmployeeList(employeeList);

        Optional<Company> optional = Optional.ofNullable(company);

        // 如果employeeList 不为空则直接返回，如果为空返回一个空集合，而不是null
        System.out.println(optional.map(company1 -> company1.getEmployeeList()).orElse(Collections.emptyList()));

    }

    @Test
    public void test004() {

        Employee employee = new Employee("aa", 21);
        Employee employee1 = new Employee("bb",22);
        List<Employee> employeeList = Arrays.asList(employee, employee1);
//        List<Employee> employeeList =null;
                System.out.println(Optional.ofNullable(employeeList).orElse(Collections.emptyList()));

    }
    @Test
    public void test005() {

        Employee employee = new Employee("aa", 21);
        employee =null;
        Optional.ofNullable(employee).ifPresent(employee1 -> {

        });
        Optional.ofNullable(employee);

    }
}