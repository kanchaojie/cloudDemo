package com.kcj.study.test;

import com.kcj.user.pojo.User;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

/**
 * 利用反射查看修饰符、类型、名字
 */
public class test {
    public static void main(String[] args) {
        Class<User> clazz = User.class;
        //获取这个类中的所有的属性
        Field[] fields = clazz.getDeclaredFields();
        for (Field f:fields) {
            //输出修饰符
            System.out.println(Modifier.toString(f.getModifiers())+"\t");
            final Type genericType = f.getGenericType();


            //输出属性的类型
            System.out.println(f.getGenericType().toString()+"\t");

            //输出属性的名字
            System.out.println(f.getName()+"\t");
        }
    }
}
