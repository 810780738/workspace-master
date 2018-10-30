package com.owen.web;

import com.owen.TokenUtils;
import com.owen.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Administrator
 * @Date: 2018/10/15 17:37
 * @Description:
 */
@RestController
@Api(value = "/token",description = "token相关")
public class TokenController {


    @Autowired
    private TokenService tokenService;

    @ApiOperation(value = "获取token",notes = "")
    @RequestMapping(value = "/getToken",method = RequestMethod.PUT)
    public void getToken(String url){
        try {
            tokenService.getToken(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
