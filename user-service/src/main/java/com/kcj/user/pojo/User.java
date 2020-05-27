package com.kcj.user.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Data
@Table(name = "tb_user")//表示表名
public class User {

    @Id//表示主键
    @KeySql(useGeneratedKeys = true)//表示主键自增
    private Long id;//id
    private String username;//用户名
    private String password;//密码
    private String phone;//手机号
    private Date created;//创建时间
    @Transient//表示当前字段不是持久化的，是瞬时在数据库中不存在这个字段
    private String note;//备注
}
