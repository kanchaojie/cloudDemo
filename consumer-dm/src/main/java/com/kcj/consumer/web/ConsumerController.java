package com.kcj.consumer.web;

import com.kcj.consumer.client.UserClient;
import com.kcj.consumer.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("consumer")
@DefaultProperties(defaultFallback = "getUserFallback"/*,commandProperties = {@HystrixProperty()可设置整个类的超时时长}*/)
public class ConsumerController {
   /* @Autowired
    private RestTemplate restTemplate;*/

   /* @Autowired
    private DiscoveryClient discoveryClient;*/

    /*@Autowired
    private RibbonLoadBalancerClient ribbonLoadBalancerClient;*/
    @Autowired
    private UserClient userClient;

    @GetMapping("{id}")
    public User getUser(@PathVariable("id") Long id) {
       return  userClient.queryById(id);
    }
/*
    @GetMapping("{id}")
    public User getUser(@PathVariable("id") Long id) {
        //根据注册的服务id获取服务的实例
        *//*List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
        ServiceInstance serviceInstance = instances.get(0);*//*
        *//*ServiceInstance serviceInstance = ribbonLoadBalancerClient.choose("user-service");//默认轮询取
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/user/" + id;*//*
        String url = "http://user-service/user/"+ id;
        User user = restTemplate.getForObject(url, User.class);//默认轮询取
        return user;
    }*/

    /*@GetMapping("{id}")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000"),*//*设置超时时长5秒*//*
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "20"),*//*默认20，熔断处于关闭状态，20次判断请求的超时率，超过百分之50开启熔断，接下来休眠五秒内处于半开启状态继续测试请求超时率*//*
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000"),*//*默认5秒，表示休眠5秒*//*
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50")*//*默认50%*//*
    })*//*(fallbackMethod = "getUserFallback")类名中写了默认的defaultFallback,这里开启即可，不需要再指定了*//**//*开启失败处理使用hystrix的指令,指定失败时执行的方法为getUserFallback*//*
    public String getUser(@PathVariable("id") Long id) {
        String url = "http://user-service/user/"+ id;
        String user = restTemplate.getForObject(url, String.class);//默认轮询取
        return user;
    }*/
    /*注意：Hystrix失败时返回的方法返回值必须与getUser返回值一致，参数列表也要一样,对应独有的fallback的时候*/
    /*public String getUserFallback(Long id) {
        return "不好意思，调用失败，服务器太拥挤了";
    }*/
    /*注意：设置本类的通用fallback*/
    public String getUserFallback() {
        return "不好意思，调用失败，服务器太拥挤了";
    }
}
