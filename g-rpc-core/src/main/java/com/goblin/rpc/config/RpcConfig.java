package com.goblin.rpc.config;

import lombok.Data;

@Data
public class RpcConfig {
    private String name = "name";

    private String version = "v1.0";

    private String serverHost = "localhost";

    private Integer serverPort = 8080;

    private boolean mock = false;
}
