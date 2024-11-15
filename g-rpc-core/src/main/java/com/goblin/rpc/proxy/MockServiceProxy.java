package com.goblin.rpc.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * mock服务代理
 */
@Slf4j
public class MockServiceProxy implements InvocationHandler {
    /**
     * 调用代理
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 获取返回的类型
        Class<?> mechodReturnType = method.getReturnType();
        log.info("mock invoke {}", method.getName());
        // 根据返回值类型生成默认的返回对象
        return getDefaultObject(mechodReturnType);
    }

    /**
     * 生成指定类型的默认值对象
     * @param type
     * @return
     */
    private Object getDefaultObject(Class<?> type){
        if(type.isPrimitive()){
            if(type == boolean.class)
                return false;
            else if(type == short.class)
                return (short)0;
            else if(type == int.class)
                return 0;
            else if(type == long.class)
                return 0L;
            else if(type == float.class)
                return 0.0f;
            else if(type == double.class)
                return 0.0d;
            else if(type == byte.class)
                return (byte)0;
            else if(type == char.class)
                return 'a';
            else if(type == String.class)
                return "abc";
            else
                return null;
        }
        return null;
    }
}
