package com.kivi.framework.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping( "/test" )
    public String test() {
        List<String> services = discoveryClient.getServices();
        // 获取实例化的注册节点
        List<ServiceInstance> list = discoveryClient.getInstances("CONSUL-CLIENT");

        // 获取实例化的服务
        StringBuffer sb = new StringBuffer();
        if (list != null && list.size() > 0) {
            sb.append(list.get(0).getUri() + ",");
        }
        return "hello world  " + sb.toString();
    }
}
