package com.goblin.example.consumer;

import com.goblin.example.common.model.User;
import com.goblin.example.common.service.UserService;
import com.goblin.rpc.proxy.ServiceProxyFactory;

public class EasyConsumerExample {
    public static void main(String[] args) {
        // 获取静态代理对象
//        UserService userService = new UserServiceProxy();
        // 获取动态代理对象
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("name1");
        // 调用提供者的实现
        User newUser = userService.getUser(user);
        if(newUser != null){
            System.out.println(newUser.getName());
        }else {
            System.out.println("获取用户失败");
        }
    }
}
