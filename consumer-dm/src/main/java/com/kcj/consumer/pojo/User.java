package com.kcj.consumer.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long id;//id
    private String username;//用户名
    private String password;//密码
    private String phone;//手机号
    private Date created;//创建时间
    private String note;//备注
}
