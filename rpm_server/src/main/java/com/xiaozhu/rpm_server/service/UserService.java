package com.xiaozhu.rpm_server.service;

import com.xiaozhu.rpm_server.conf.DataSourceConfig;
import com.xiaozhu.rpm_server.dao.UserRepository;
import com.xioazhu.rpccommon.Utils.TimeUtils;
import com.xioazhu.rpccommon.model.ResultBean;
import com.xioazhu.rpccommon.model.User;
import com.xioazhu.rpccommon.services.RpcUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2018/10/17 19:10
 * @Description:
 */
@Slf4j
@Service
public class UserService implements RpcUserService {



    private UserRepository userRepository;

    private DataSourceConfig dataSourceConfig;

    @Autowired
    public UserService(UserRepository userRepository, DataSourceConfig dataSourceConfig) {
        this.userRepository = userRepository;
        this.dataSourceConfig = dataSourceConfig;
    }

    @Override
    public ResultBean<User> saveUser(Long id, String name, Integer age, String address) {
        log.info("服务端获取到客户端数据 id:{},name:{},age:{},address:{}",id,name,age,address);
        ResultBean<User> result = new ResultBean<>();
        Number number = dataSourceConfig.getIdGenerator().generateId();
        Date dateTime = TimeUtils.getDateTime(null, new Date());
        User user = new User(number.longValue(),name, age, address,dateTime,dateTime);
        user = userRepository.save(user);
        result.setCode(200);
        result.setMessage("添加成功");
        result.setData(user);
        log.info("服务端返回数据：{}",result);
        return result;
    }

    @Override
    public ResultBean<User> findById(Long id) {
        return null;
    }

    @Override
    public ResultBean<List<User>> findByName(String name) {
        return null;
    }

}