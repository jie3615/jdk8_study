package com.jie.java8;

import com.jie.bean.User;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * @author: wyj
 * @date: 2019/10/19
 * @description:
 */
public class UserFuntionTest01 {
    @Test
    public void test01() {
        User user1 = new User("yj", 22);
        User user2 = new User("jiege", 25);
        User user3 = new User("ajie", 28);
        List<User> userList = Arrays.asList(user1, user2, user3);

        List<User> users = getUserListByUserName("jiege", userList);
        users.forEach(System.out::println);

        System.out.println("###################");

        System.out.println(getUserListByAge(25, userList));

        System.out.println("###################");
        System.out.println(getUserListByAge2(23, userList,
                (age, users0) -> users0.stream().filter(user -> user.getAge() > age).collect(Collectors.toList())
        ));
    }

    /**
     * 根据用户名查找用户
     * @param userName
     * @param userList
     * @return
     */
    List<User> getUserListByUserName(String userName, List<User> userList) {
        return userList.stream().filter(user -> user.getUserName().equals(userName)).collect(Collectors.toList());
    }

    /**
     * 过滤年龄大于某个值，使用BiFunciotn实现
     * @param age
     * @param userList
     * @return
     */
    List<User> getUserListByAge(int age, List<User> userList) {
        //先定义函数式接口的实例lambda表达式；
        // 输入两个参数，返回一个
        BiFunction<Integer, List<User>, List<User>> biFunction = (ageOfuser, users) -> users.stream().filter(u -> u.getAge() > ageOfuser).collect(Collectors.toList());
        return biFunction.apply(age, userList);
    }

    /**
     * 把函数体通过参数传入
     * @param age
     * @param userList
     * @param biFunction
     * @return
     */
    List<User> getUserListByAge2(int age, List<User> userList,BiFunction<Integer, List<User>, List<User>> biFunction) {
        //先定义函数式接口的实例lambda表达式；
        // 输入两个参数，返回一个
        return biFunction.apply(age, userList);
    }
}