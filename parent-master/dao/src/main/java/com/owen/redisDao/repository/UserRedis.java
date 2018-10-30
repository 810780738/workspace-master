package com.owen.redisDao.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: redis管理用户
 * @Date: 2018/8/7 20:10
 * @Description:
 */
@Repository
public class UserRedis {

    @Autowired
    private StringRedisTemplate template;

    public void setKey(String key,String value){
        ValueOperations ops = template.opsForValue();
        ops.set(key,value,30,TimeUnit.MINUTES);//设定一分钟过期
    }

    public Object getValue(String key){
        return this.template.opsForValue().get(key);
    }
}
