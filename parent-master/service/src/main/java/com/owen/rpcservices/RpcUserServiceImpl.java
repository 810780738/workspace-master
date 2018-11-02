package com.owen.rpcservices;

import com.owen.DataSourceConfig;
import com.owen.redisDao.RedisUtil;
import com.xioazhu.rpccommon.model.ResultBean;
import com.xioazhu.rpccommon.model.User;
import com.xioazhu.rpccommon.rpc.Client;
import com.xioazhu.rpccommon.services.RpcUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author zhusm
 * @Description
 * @Date 16:38 2018/10/31
 **/
@Slf4j
@Service
public class RpcUserServiceImpl {

    @Autowired
    RpcUserService rpcUserService;

    @Autowired
    private DataSourceConfig dataSourceConfig;

    @Autowired
    private RedisUtil redisUtil;

    public ResultBean saveUser(String name,int age,String address,String token) throws InterruptedException {
//        Client.rpcInvoke("UserService",null,null);
        ResultBean<User> resultBean = new ResultBean<>();
//        resultBean.setMessage("非法请求");
//        if (!redisUtil.delete(token)) return resultBean;
        long id = dataSourceConfig.getIdGenerator().generateId().longValue();
        resultBean = rpcUserService.saveUser(id, name, age, address);
        log.info("获取到服务端数据：{}",resultBean);
        return resultBean;
    }
}
