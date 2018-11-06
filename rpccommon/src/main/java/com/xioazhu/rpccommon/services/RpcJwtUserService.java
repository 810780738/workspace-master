package com.xioazhu.rpccommon.services;/**
 * @Auther: Administrator
 * @Date: 2018/11/5 20:09
 * @Description:
 */

import com.xioazhu.rpccommon.model.ResultBean;
import com.xioazhu.rpccommon.model.Users;

import java.util.List;

/**
 *@ClassName RpcJwtUserService
 *@Description TODO
 *@Author zhusm
 *@Date 2018/11/5 20:09    
 *@Version 1.0
 */
public interface RpcJwtUserService {
    ResultBean<List<Users>> findByName(String name);
}
