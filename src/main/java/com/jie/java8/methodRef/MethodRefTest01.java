package com.jie.java8.methodRef;

import com.jie.bean.Student;
import com.jie.java8.Test002;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author: wyj
 * @date: 2019/10/21
 * @description:方法引用
 */
public class MethodRefTest01 {
    @Test
    public void test01() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        list.forEach(item -> System.out.println(item));
        // 方法引用，可以看作是一个函数指针
        list.forEach(System.out::println);
    }
    /**
     * 方法引用分类：
     *
     1、引用静态方法
     ContainingClass::staticMethodName
     例子: String::valueOf，对应的Lambda：(s) -> String.valueOf(s)
     比较容易理解，和静态方法调用相比，只是把.换为::

     2、引用特定对象的实例方法
     containingObject::instanceMethodName
     例子: x::toString，对应的Lambda：() -> this.toString()
     与引用静态方法相比，都换为实例的而已

     3、引用特定类型的任意对象的实例方法
     ContainingType::methodName
     例子: String::toString，对应的Lambda：(s) -> s.toString()
     太难以理解了。难以理解的东西，也难以维护。建议还是不要用该种方法引用。
     实例方法要通过对象来调用，方法引用对应Lambda，Lambda的第一个参数会成为调用实例方法的对象。

     4、引用构造函数
     ClassName::new
     例子: String::new，对应的Lambda：() -> new String()
     构造函数本质上是静态方法，只是方法名字比较特殊。
     */


    //第一种 静态方法
    @Test
    public void test02() {
        Student student1 = new Student("aa", 23);
        Student student2 = new Student("ca", 24);
        Student student3 = new Student("da", 22);
        Student student4 = new Student("aa", 28);
        List<Student> list = Arrays.asList(student1, student2, student3, student4);
        list.forEach(System.out::println);
        System.out.println("##############");
        // 使用集合本身的排序方法,传入lambda表达式
        list.sort((stu1,stu2)-> Student.compareStuByAge(stu1, stu2));
        list.forEach(System.out::println);

        // 使用方法引用,等价于上面的方式；编译器自动推断出来
        list.sort(Student::compareStuByAge);
        list.forEach(System.out::println);
        System.out.println("##############");
        list.sort(Student::compareStuByName);
        list.forEach(System.out::println);

    }

    //第二种，对象引用实例方法
    @Test
    public void test03() {
        Student student1 = new Student("aa", 23);
        Student student2 = new Student("ca", 24);
        Student student3 = new Student("da", 22);
        Student student4 = new Student("aa", 28);
        List<Student> list = Arrays.asList(student1, student2, student3, student4);
        list.forEach(System.out::println);
        System.out.println("##############");
        // 使用集合本身的排序方法,传入lambda表达式
        list.sort((stu1,stu2)-> student1.compareStuByAge1(stu1, stu2));
        list.forEach(System.out::println);

        // 使用方法引用,等价于上面的方式；编译器自动推断出来
        list.sort(student1::compareStuByAge1);
        list.forEach(System.out::println);
        System.out.println("##############");
        list.sort(student1::compareStuByName1);
        list.forEach(System.out::println);
}

    //第三种，类名::实例方法
    @Test
    public void test04() {
        Student student1 = new Student("aa", 23);
        Student student2 = new Student("ca", 24);
        Student student3 = new Student("da", 22);
        Student student4 = new Student("aa", 28);
        List<Student> list = Arrays.asList(student1, student2, student3, student4);
        list.sort(Student::comapareStuByAge2);
        list.forEach(System.out::println);

        List<String> citys = Arrays.asList("beijing", "tianjing", "dalian", "hefei");
        citys.sort(String::compareToIgnoreCase);
//        Collections.sort(citys,String::compareToIgnoreCase);
        citys.forEach(System.out::println);
    }

    //第四种，构造方法引用
    @Test
    public void test05() {
        Supplier<Student> supplier =  Student::new; //通过方法引用
        System.out.println(supplier.get());

        Supplier<String> supplier1 = String::new;
        System.out.println(supplier1.get());

        System.out.println("hello "+getString1(String::new));
        System.out.println(getString2("hello", String::new));

    }

    // 不接收参数，直接返回
    public String getString1(Supplier<String> stringSupplier) {
        return stringSupplier.get();
    }

    // 接收一个参数，返回一个参数
    public String getString2(String string, Function<String, String> function) {

        return function.apply(string);
    }
}