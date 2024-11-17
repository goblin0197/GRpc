package com.goblin.rpc.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;

/**
 *配置工具类
 */
public class ConfigUtils {
    /**
     * 加载配置对象
     * @param tClass
     * @param prefix
     * @return
     * @param <T>
     */
    public static <T> T loadConfig(Class<T> tClass ,String prefix){
        return loadConfig(tClass,prefix,"");
    }

    /**
     * 加载配置对象 支持区分环境
     * @param tClass
     * @param prefix
     * @param environment
     * @return
     * @param <T>
     */
    public static <T> T loadConfig(Class<T> tClass ,String prefix,String environment){
        StringBuilder configFilerBuilder = new StringBuilder("application");
        if(StrUtil.isNotBlank(environment)){
            configFilerBuilder.append("-").append(environment);
        }
        // TODO 暂时按顺序读取，读到就返回
        String[] postFix = {".yml", ".yaml", ".properties"};
        for(String postfix : postFix){
            try{
                StringBuilder tmp = new StringBuilder(configFilerBuilder.toString());
                tmp.append(postfix);
                Props props = new Props(tmp.toString());
                props.autoLoad(true);
                System.out.println("配置文件路径："+tmp);
                return props.toBean(tClass,prefix);
            }catch (Exception e){

            }
        }
        return null;
    }
}
