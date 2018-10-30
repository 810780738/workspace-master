package com.owen;

import com.dangdang.ddframe.rdb.sharding.id.generator.IdGenerator;
import com.dangdang.ddframe.rdb.sharding.id.generator.self.CommonSelfIdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: Administrator
 * @Date: 2018/10/15 16:06
 * @Description:
 */
@Configuration
public class DataSourceConfig {

    @Bean
    public IdGenerator getIdGenerator(){
        return new CommonSelfIdGenerator();
    }
}
