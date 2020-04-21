package com.kcj.user.mapper;

import com.kcj.user.pojo.User;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@Component("userMapper")
public interface UserMapper extends Mapper<User> {
}
