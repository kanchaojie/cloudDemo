package com.kcj.test;

import com.kcj.user.pojo.User;

/**
 * 利用反射调用方法
 */
public class test3 {
    public static void main(String[] args) {
        User user = new User();
        user.print();
        try {
            Class<?> clazz  = Class.forName("com.kcj.user.pojo.User");
            User user1 = (User) clazz.newInstance();
            user1.print();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
