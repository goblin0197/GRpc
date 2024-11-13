package com.goblin.rpc.server;

/**
 * Http服务接口
 */
public interface HttpServer {
    /**
     * 启动web服务器
     * @param port
     */
    void doStart(int port);
}
