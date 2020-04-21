package com.kcj.consumer.client;

import com.kcj.consumer.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")/*根据服务名称去eureka拉去响应的服务列表，底层利用ribbon选取对应的服务*/
public interface UserClient {
    @GetMapping("user/{id}")
    User queryById(@PathVariable("id") Long id);
}
