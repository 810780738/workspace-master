package com.xiaozhu.rpm_server.service;

import com.xiaozhu.rpm_server.conf.DataSourceConfig;
import com.xiaozhu.rpm_server.dao.JwtUserRepository;
import com.xiaozhu.rpm_server.dao.UserRepository;
import com.xioazhu.rpccommon.Utils.TimeUtils;
import com.xioazhu.rpccommon.model.ResultBean;
import com.xioazhu.rpccommon.model.User;
import com.xioazhu.rpccommon.model.Users;
import com.xioazhu.rpccommon.services.RpcUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    private JwtUserRepository jwtUserRepository;

    private UserRepository userRepository;

    private DataSourceConfig dataSourceConfig;

    @Autowired
    public UserService(UserRepository userRepository, DataSourceConfig dataSourceConfig) {
        this.userRepository = userRepository;
        this.dataSourceConfig = dataSourceConfig;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultBean<User> saveUser(Long id, String name, Integer age, String address) {
        log.info("服务端获取到客户端数据 id:{},name:{},age:{},address:{}",id,name,age,address);
        Number number = dataSourceConfig.getIdGenerator().generateId();
        Date dateTime = TimeUtils.getDateTime(null, new Date());
        User user = new User(number.longValue(),name, age, address,dateTime,dateTime);
        user = userRepository.save(user);
        ResultBean<User> result = ResultBean.success(user);
        log.info("服务端返回数据：{}",result);
        return result;
    }

    @Override
    public ResultBean<User> findById(Long id) {
        return null;
    }

    @Override
    public ResultBean<List<User>> findByName(String name) {
        Users byUsername = null;
        ResultBean<List<User>> objectResultBean = new ResultBean<>();
        try {
            byUsername = jwtUserRepository.findByUsername(name);
        } catch (Exception e) {
            e.printStackTrace();
            objectResultBean.setSuccess(false);
            objectResultBean.setCode(5004);
            objectResultBean.setMessage("查询出错");
        }
        List<User> users = new ArrayList<>();
        users.add(
                 User.builder()
                .name(byUsername.getUsername())
                .address(byUsername.getRole())
                .addTime(byUsername.getAddtime())
                .modifyTime(byUsername.getModifytime())
                .build()
        );
        objectResultBean.setData(users);
        return objectResultBean;
    }

}
