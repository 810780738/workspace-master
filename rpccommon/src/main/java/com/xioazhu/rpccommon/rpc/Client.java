package com.xioazhu.rpccommon.rpc;

import com.xioazhu.rpccommon.BeanUtils.SpringBootBeanUtil;

/**
 *@ClassName Client
 *@Description 通过非接口形式调用RPC服务
 *@Author zhusm
 *@Date 2018/10/29 19:13    
 *@Version 1.0
 */
public class Client {


    public static <T> T rpcInvoke(String beanName,String methodName,Object... paramters){
        Object bean = SpringBootBeanUtil.getBean(beanName);
        return null;
    }
}
