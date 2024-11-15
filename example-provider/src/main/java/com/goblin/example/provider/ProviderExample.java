package com.goblin.example.provider;

import com.goblin.example.common.service.UserService;
import com.goblin.rpc.RpcApplication;
import com.goblin.rpc.registry.LocalRegistry;
import com.goblin.rpc.server.HttpServer;
import com.goblin.rpc.server.VertxHttpServer;

public class ProviderExample {
    public static void main(String[] args) {
        RpcApplication.init();

        LocalRegistry.register(UserService.class.getName() ,UserServiceImpl.class);

        // 启动web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
