package com.xioazhu.rpccommon.services;

import com.xioazhu.rpccommon.model.ResultBean;
import com.xioazhu.rpccommon.model.User;

import javax.xml.ws.ServiceMode;
import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2018/10/16 10:14
 * @Description:
 */
public interface RpcUserService {

    ResultBean<User> saveUser(Long id, String name, Integer age, String address);

    ResultBean<User> findById(Long id);

    ResultBean<List<User>> findByName(String name);
}
