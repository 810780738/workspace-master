package com.xiaozhu.rpm_server.conf;

import com.xioazhu.rpccommon.services.RpcUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.stereotype.Component;


/**
 * @Auther: Administrator
 * @Date: 2018/10/16 10:53
 * @Description:
 */

//当类路径存在这个类时才会加载这个配置类，否则跳过,这个很有用比如不同jar包间类依赖，依赖的类不存在直接跳过，不会报错
//@ConditionalOnClass(RpcUserService.class)
@Slf4j
@Component
public class UserRpc {

    @Bean("/UserService")
    public HessianServiceExporter userService(){
        RpcUserService bean = SpringBootBeanUtil.getBean(RpcUserService.class);
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(bean);
        exporter.setServiceInterface(RpcUserService.class);
        log.info("UserService online");
        return exporter;
    }

}

