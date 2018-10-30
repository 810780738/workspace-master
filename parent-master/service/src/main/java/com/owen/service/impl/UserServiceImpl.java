package com.owen.service.impl;

import com.owen.DataSourceConfig;
import com.owen.TokenUtils;
import com.owen.dao.repository.UserRepository;
import com.owen.model.User;
import com.owen.redisDao.RedisUtil;
import com.owen.redisDao.repository.UserRedis;
import com.owen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRedis userRepositoryRedis;

    @Autowired
    private DataSourceConfig dataSourceConfig;

    @Autowired
    private RedisUtil redisUtil;


    public User getUser(String name){
        userRepositoryRedis.setKey(name,"小猪");
//        return userRepository.findByName(name);
        User user = new User();
        user.setName((String) userRepositoryRedis.getValue(name));
        return user;
    }

    @Transactional
    public String saveUser(String name,Integer age, String address,String token) throws InterruptedException {
        //幂等性校验
        if (!redisUtil.delete(token)) return "非法请求";

        Number number = dataSourceConfig.getIdGenerator().generateId();
        User user = new User(number.longValue(),name, age, address);
//        User save = userRepository.save(user);

        String userToken = TokenUtils.getInstance().makeToken();
//        userRepositoryRedis.setKey(userToken,save.toString());
        return token;
    }

}
