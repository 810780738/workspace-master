package com.owen.service.impl;

import com.owen.TokenUtils;
import com.owen.redisDao.RedisUtil;
import com.owen.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Administrator
 * @Date: 2018/10/15 18:39
 * @Description:
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String getToken(String url) throws Exception {
        String token = TokenUtils.getInstance().makeToken();
        redisUtil.setKey(url,token,false);
        return token;
    }
}
