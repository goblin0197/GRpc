package com.goblin.rpc.server;

import io.vertx.core.Vertx;

/**
 * Vertx HTTP 服务器
 */
public class VertxHttpServer implements HttpServer {
    /**
     * 启动服务器
     * @param port
     */
    @Override
    public void doStart(int port) {
        // 创建Vert.x实例
        Vertx vertx = Vertx.vertx();

        // 创建HTTP 服务器
        io.vertx.core.http.HttpServer server = vertx.createHttpServer();

        // 监听端口并处理请求
        server.requestHandler(new HttpServerHandler());

        // 启动 http 服务器 并开始监听指定端口
        server.listen(port , result -> {
            if(result.succeeded()){
                System.out.println("服务器启动成功，开始监听端口：" + port);
            }else{
                System.out.println("服务器启动失败，\n" + result.cause());
            }
        });
    }
}
