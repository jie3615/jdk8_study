package com.jie.bean;

import java.util.List;

/**
 * @author: wyj
 * @date: 2019/10/19
 * @description:
 */
public class Company {

    private String name;
    private List<Employee> employeeList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}