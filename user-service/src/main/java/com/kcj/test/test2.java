package com.kcj.test;

import com.kcj.user.service.UserService;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 利用反射查看方法的返回类型参数和名字
 */
public class test2 {
    public static void main(String[] args) {
        Class<UserService> clazz = UserService.class;
        Method[] methods = clazz.getMethods();

        for (Method m:methods) {
            System.out.println(m+"\t");
            System.out.println( m.getModifiers()+"\t");//修饰符;
            System.out.println( m.getGenericParameterTypes()+"\t");//入参;
            System.out.println( m.getGenericReturnType()+"\t");//出参;
        }
        //得到所有的构造函数
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        //输出所有的构造函数
        for (Constructor c:constructors) {
            System.out.println(c+"\t");
            System.out.println(c.getModifiers()+"\t");//修饰符
            System.out.println(c.getGenericParameterTypes()+"\t");//入参
        }
    }
}
