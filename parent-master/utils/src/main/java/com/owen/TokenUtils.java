package com.owen;

import com.owen.redisDao.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @Auther: Administrator
 * @Date: 2018/10/13 17:45
 * @Description:token工具类
 */
public class TokenUtils {

    private static Logger log = LoggerFactory.getLogger(TokenUtils.class);

    /**
     * 简单单例(保证获取md5)
     */
    private TokenUtils() {}
    private static final TokenUtils instance = new TokenUtils();
    public static TokenUtils getInstance(){
        return instance;
    }

    public String makeToken(){
        String token = (System.currentTimeMillis()+new Random().nextInt(999999999))+"";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] md5bytes = md5.digest(token.getBytes());
            BASE64Encoder base64Encoder = new BASE64Encoder();
            return base64Encoder.encode(md5bytes);
        } catch (NoSuchAlgorithmException e) {
            log.error("获取MD5出错");
            e.printStackTrace();
        }
        return null;
    }
}
