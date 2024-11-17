package com.goblin.rpc.serializer;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.goblin.rpc.model.RpcRequest;
import com.goblin.rpc.model.RpcResponse;

import java.io.IOException;

/**
 * Json序列化器
 */
public class JsonSerializer implements Serializer {
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 序列化
     *
     * @param object
     * @param <T>
     * @return
     * @throws IOException
     */
    @Override
    public <T> byte[] serialize(T object) throws IOException {
        return OBJECT_MAPPER.writeValueAsBytes(object);
    }

    /**
     * 逆序列化
     *
     * @param bytes
     * @param classType
     * @param <T>
     * @return
     * @throws IOException
     */
    @Override
    public <T> T deserialize(byte[] bytes, Class<T> classType) throws IOException {
        T obj = OBJECT_MAPPER.readValue(bytes , classType);
        //instanceof 是 Java 中的一个关键字，用于判断一个对象是否是某个类的实例或其子类的实例。它在类型检查和确保类型安全时非常有用。
        // object instanceof ClassName 如果 object 是 ClassName 类的实例（或者是其子类的实例），返回 true；否则返回 false
        if(obj instanceof RpcRequest){
            return handlRequest((RpcRequest) obj , classType);
        }
        if(obj instanceof RpcResponse){
            return handleResponse((RpcResponse) obj , classType);
        }
        return obj;
    }

    /**
     * 由于 Object 的原始对象会被擦除，导致反序列化时会被作为 LinkedHashMap 无法转换成原始对象，因此这里做了特殊处理
     *
     * @param rpcRequest
     * @param type
     * @param <T>
     * @return
     * @throws IOException
     */
    private <T> T handlRequest(RpcRequest rpcRequest, Class<T> type) throws IOException {
        Class<?>[] paramTypes = rpcRequest.getParameterTypes(); // 获取参数类型
        Object[] args = rpcRequest.getArgs(); // 获取参数

        // 处理每个参数的类型
        for (int i = 0; i < paramTypes.length; i++) {
            Class<?> clazz = paramTypes[i];
            // 如果类型不同，重新处理一下类型
            if(!clazz.isAssignableFrom(args[i].getClass())) {
                byte[] argBytes = OBJECT_MAPPER.writeValueAsBytes(args[i]);
                args[i] = OBJECT_MAPPER.readValue(argBytes , clazz);
            }
        }
        return type.cast(rpcRequest); // 返回原始对象
    }

    /**
     * 由于 Object 的原始对象会被擦除，导致反序列化时会被作为 LinkedHashMap 无法转换成原始对象，因此这里做了特殊处理
     * @param rpcResponse
     * @param type
     * @return
     * @param <T>
     * @throws IOException
     */
    private <T> T handleResponse(RpcResponse rpcResponse , Class<T> type) throws IOException {
        // 处理响应数据
        byte[] dataBytes = OBJECT_MAPPER.writeValueAsBytes(rpcResponse.getData());
        rpcResponse.setData(OBJECT_MAPPER.readValue(dataBytes , rpcResponse.getDataType()));
        return type.cast(rpcResponse);
    }
}
