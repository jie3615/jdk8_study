package com.jie.bean;

/**
 * @author: wyj
 * @date: 2019/10/19
 * @description:
 */
public class Student {
    private String name = "wangyongjie";
    private int age = 22;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    // 规范的比较行为，使用当前对象
    public int comapareStuByAge2(Student student) {
        return this.getAge() - student.getAge();
    }

    public int compareStuByName2(Student student) {
        return this.getName().compareToIgnoreCase(student.getName());
    }



    // 根据年龄排序 ,静态方法
    public static int compareStuByAge(Student student1, Student student2) {
        return student1.getAge() - student2.getAge();
    }

    // 根据名字排序，静态方法
    public static int compareStuByName(Student student1, Student student2) {
        return student1.getName().compareToIgnoreCase(student2.getName());
    }

    // 根据年龄排序 ,实例方法
    public  int compareStuByAge1(Student student1, Student student2) {
        return student1.getAge() - student2.getAge();
    }

    // 根据名字排序，实例方法
    public  int compareStuByName1(Student student1, Student student2) {
        return student1.getName().compareToIgnoreCase(student2.getName());
    }
}