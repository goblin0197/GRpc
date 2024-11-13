package com.goblin.example.provider;

import com.goblin.example.common.service.UserService;
import com.goblin.rpc.registry.LocalRegistry;
import com.goblin.rpc.server.HttpServer;
import com.goblin.rpc.server.VertxHttpServer;


public class EasyProviderExample {
    public static void main(String[] args) {
        // 启动后开始提供服务

        // 注册服务
        LocalRegistry.register(UserService.class.getName() , UserServiceImpl.class);

        // 启动web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}
