package com.owen.rpcservices;/**
 * @Auther: Administrator
 * @Date: 2018/11/5 20:03
 * @Description:
 */

import com.owen.model.JwtUser;
import com.xioazhu.rpccommon.model.ResultBean;
import com.xioazhu.rpccommon.model.User;
import com.xioazhu.rpccommon.services.RpcUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *@ClassName UserDetailsServiceImpl
 *@Description TODO
 *@Author zhusm
 *@Date 2018/11/5 20:03    
 *@Version 1.0
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    RpcUserService rpcUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ResultBean<List<User>> byName = rpcUserService.findByName(username);
        return new JwtUser();
    }
}
