package com.owen;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 *@ClassName JwtTokenUtils
 *@Description Jwt工具类
 *@Author zhusm
 *@Date 2018/11/2 19:33    
 *@Version 1.0
 */
public class JwtTokenUtils {

    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer";

    private static final String SECRET = "jwtsecretdemo";
    private static final String ISS = "echisan";

    //过期时间1小时
    private static final long EXPIRATION = 3600L;

    //选择记住我失效时间为7天
    private static final long EXPIRATION_REMEMBER = 3600 * 24 * 7;

    /**
     * @Author zhusm
     * @Description 创建Token
     * @Date 19:47 2018/11/2
     * @Param [username, isRememberMe]
     * @return java.lang.String
     **/
    public static String createToken(String username,boolean isRememberMe){
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        return Jwts.builder().signWith(SignatureAlgorithm.HS512,SECRET)
                .setIssuer(ISS)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }

    /**
     * @Author zhusm
     * @Description 从token中获取用户名
     * @Date 19:50 2018/11/2
     * @Param [token]
     * @return java.lang.String
     **/
    public static String getUserName(String token){
        return getTokenBody(token).getSubject();
    }

    private static Claims getTokenBody(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();

    }

    /**
     * @Author zhusm
     * @Description 判断token是否已过期
     * @Date 19:52 2018/11/2
     * @Param [token]
     * @return boolean
     **/
    public static boolean isExpiration(String token){
        return getTokenBody(token).getExpiration().before(new Date());
    }
}
