package com.kcj.studyJDK8;

import java.util.*;
import java.util.stream.Collectors;

/**
 * jdk8新特性常用转换
 */
public class CommonUtils {
    /**
     * 对象转换String
     * @param object
     * @return
     */
    public static String getString(Object object){
        if(object == null){
            return "";
        }
        return String.valueOf(object);
    }
    // 使用 java 7 排序根据id排序
    private void sortUsingJava7(List<User> users){
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User s1, User s2) {
                return s1.getId().compareTo(s2.getId());
            }
        });
    }

    // 使用 java 8 排序
    private void sortUsingJava8(List<User> users){
        Collections.sort(users, (s1, s2) -> s1.getId().compareTo(s2.getId()));
    }

    public static void main(String[] args) {
        //常用转换
        //造数据
        List<User> userList = new ArrayList<User>();
        for (int i=0;i<5;i++) {
            long s = System.currentTimeMillis();
            String m = String.valueOf(i);
            User user = new User();
            user.setId(s+Long.valueOf(m));
            user.setUsername("kcj"+m);
            user.setCreated(new Date());
            user.setNote("记忆中的你"+m);
            user.setPassword(String.valueOf(s));
            user.setPhone("1886666222"+m);
            userList.add(user);
        }
        //排序================================开始=========================================
        //使用jdk7排序，根据用户id排序
        Collections.sort(userList, new Comparator<User>() {
            @Override
            public int compare(User s1, User s2) {
                return s1.getId().compareTo(s2.getId());
            }
        });

        //使用jdk8中Lambda表达式排序，根据用户id排序
        Collections.sort(userList, (s1, s2) -> s1.getId().compareTo(s2.getId()));
        //排序================================结束=========================================


        //对象转化对象=========================开始=========================================
        User user = userList.get(0);
        UserResWebDto resWebDto = BeanUtils.map(user,UserResWebDto.class);
        System.out.println(resWebDto.toString());
        //对象转化对象=========================结束=========================================


        //list转化list=========================开始=========================================
        List<UserResWebDto> resWebDtoList = BeanUtils.mapList(userList,User.class,UserResWebDto.class);
        //list转化list=========================结束=========================================


        //取出list中的id转化成另一个list=========开始=========================================
        List<Long> userIds = userList.stream().map(User::getId).collect(Collectors.toList());
        //取出list中的id转化成另一个list=========结束=========================================

        //取出list中的id为key，整个user为map转换对象=========开始==============================
        Map<Long,User> map = userList.stream().collect(Collectors.toMap(User::getId,node->node));
            //取出list中的id为key,username为value，若key有重复进行覆盖，以最后一个为准
        Map<Long,String> map1 = userList.stream().collect(Collectors.toMap(User::getId, User::getUsername, (entity1, entity2) -> entity2));
        //取出list中的id为key，整个user为map转换对象=========结束==============================


        //list转数组=======================================开始==============================
        List<String> dafaultList = new ArrayList<String>();
        dafaultList.add("个人");
        dafaultList.add("公司");
        String[] defaultListArr = new String[2];
        dafaultList.toArray(defaultListArr);
        //list转数组=======================================结束==============================
    }
}
