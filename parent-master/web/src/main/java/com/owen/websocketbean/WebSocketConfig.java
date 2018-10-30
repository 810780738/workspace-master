package com.owen.websocketbean;/**
 * @Auther: Administrator
 * @Date: 2018/10/30 09:38
 * @Description:
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yeauty.standard.ServerEndpointExporter;

/**
 *@ClassName WebSocketConfig
 *@Description TODO
 *@Author zhusm
 *@Date 2018/10/30 9:38    
 *@Version 1.0
 */
@Slf4j
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        log.info("ServerEndpointExporter online");
        return new ServerEndpointExporter();
    }
}
