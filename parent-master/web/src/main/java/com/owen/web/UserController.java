package com.owen.web;

import com.owen.model.User;
import com.owen.rpcservices.RpcUserServiceImpl;
import com.owen.service.UserService;
import com.xioazhu.rpccommon.model.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@Api(value = "/user",description = "用户操作相关")
public class UserController {

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Autowired
    private RpcUserServiceImpl repcuserService;//RPC调用


    @ApiOperation(value = "根据用户名获取用户",notes = "")
    @RequestMapping(value = "/user/{name}",method = RequestMethod.GET)
    public String getUserInfo(@PathVariable(name = "name")String name){
        log.info("根据用户名查询用户name:"+name);
        User user= userService.getUser(name);
        log.info("根据用户名查询用户查到用户"+user.toString());
        return user.toString();
    }

    @ApiOperation(value = "添加用户",notes = "")
    @RequestMapping(value = "/save/{name}/{age}/{address}",method = RequestMethod.POST)
    public ResultBean saveUserInfo(@PathVariable(name = "name")String name,@PathVariable(name = "age")Integer age,@PathVariable(name = "address")String address,String token) throws Exception {
//        String userToken= userService.saveUser(name,age,address,token);
        ResultBean resultBean = repcuserService.saveUser(name, age, address,token);
        return resultBean;
    }

}