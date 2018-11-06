package com.owen;/**
 * @Auther: Administrator
 * @Date: 2018/11/2 19:44
 * @Description:
 */

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 *@ClassName JwtTest
 *@Description TODO
 *@Author zhusm
 *@Date 2018/11/2 19:44    
 *@Version 1.0
 */
public class JwtTest {
    public static void main(String[] args) {
        String compact = Jwts.builder().signWith(SignatureAlgorithm.HS512, "jwtsecretdemo")
                .setIssuer("echisan")
                .setSubject("xiaozhu")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .compact();
        System.out.println(compact);
    }
}
