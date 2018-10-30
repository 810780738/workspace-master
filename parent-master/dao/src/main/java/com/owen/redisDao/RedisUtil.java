package com.owen.redisDao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: Administrator
 * @Date: 2018/10/15 17:42
 * @Description:
 */
@Slf4j
@Repository
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;//保存复杂数据时适用（对象等）


    @Autowired
    private StringRedisTemplate template;//存储简单的数据使用

    public void setKey(String key,String value,Boolean isTime) throws Exception {
        ValueOperations ops = template.opsForValue();
        ops.set(key,value,30,TimeUnit.MINUTES);//设定一分钟过期
        log.info("Redis保存数据成功key：{},value:{}",key,getValue(key));
    }

    public Object getValue(String key){
        return this.template.opsForValue().get(key);
    }

    public Boolean delete(String key){
        return this.template.delete(key);
    }

    public void setObjectKey(String key,Object value){
        redisTemplate.opsForHash().put(key,"",value);
    }
}
