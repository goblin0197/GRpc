package com.goblin.rpc.config;

import com.goblin.rpc.serializer.Serializer;
import com.goblin.rpc.serializer.SerializerKeys;
import lombok.Data;

@Data
public class RpcConfig {
    private String name = "name";

    private String version = "v1.0";

    private String serverHost = "localhost";

    private Integer serverPort = 8080;

    private boolean mock = false;

    private String serializer = SerializerKeys.JDK;
}
