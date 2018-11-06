package com.xiaozhu.rpm_server.service;/**
 * @Auther: Administrator
 * @Date: 2018/11/5 20:10
 * @Description:
 */

import com.xiaozhu.rpm_server.dao.JwtUserRepository;
import com.xioazhu.rpccommon.model.ResultBean;
import com.xioazhu.rpccommon.model.Users;
import com.xioazhu.rpccommon.services.RpcJwtUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *@ClassName JwtUserService
 *@Description TODO
 *@Author zhusm
 *@Date 2018/11/5 20:10    
 *@Version 1.0
 */
@Slf4j
@Service
public class JwtUserService implements RpcJwtUserService {

    private JwtUserRepository jwtUserRepository;
    
    @Override
    public ResultBean<List<Users>> findByName(String name) {
        Users byUsername = null;
        ResultBean<List<Users>> resultBean = new ResultBean<>();
        try {
            byUsername = jwtUserRepository.findByUsername(name);
        } catch (Exception e) {
            e.printStackTrace();
            resultBean.setSuccess(false);
            resultBean.setCode(5004);
            resultBean.setMessage("查询出错");
        }
        List<Users> users = new ArrayList<>();
        users.add(byUsername);
        resultBean.setData(users);
        return resultBean;
    }
}
