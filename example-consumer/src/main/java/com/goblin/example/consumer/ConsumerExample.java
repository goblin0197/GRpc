package com.goblin.example.consumer;

import com.goblin.example.common.model.User;
import com.goblin.example.common.service.UserService;
import com.goblin.rpc.config.RpcConfig;
import com.goblin.rpc.proxy.ServiceProxyFactory;
import com.goblin.rpc.utils.ConfigUtils;

public class ConsumerExample{
    public static void main(String[] args) {
//        RpcConfig rpcConfig = ConfigUtils.loadConfig(RpcConfig.class,"rpc");
//        System.out.println(rpcConfig);
        // 获取代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("name11");
        // 调用
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
//        short number = userService.getNumber();
//        System.out.println(number);
    }
}
