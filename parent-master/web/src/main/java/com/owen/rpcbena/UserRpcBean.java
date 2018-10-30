package com.owen.rpcbena;

import com.xioazhu.rpccommon.services.RpcUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

/**
 * @Auther: Administrator
 * @Date: 2018/10/17 18:34
 * @Description:
 */
@Configuration
public class UserRpcBean {


    @Value("${rpc.server}")
    private String server;

    @Value("${rpc.port}")
    private String port;

    @Bean
    public HessianProxyFactoryBean helloClient() {
        HessianProxyFactoryBean factory = new HessianProxyFactoryBean();
        factory.setServiceUrl("http://"+server+":"+port+"/UserService");
        factory.setServiceInterface(RpcUserService.class);
        return factory;
    }
}
