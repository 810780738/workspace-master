package com.xiaozhu.rpm_server.dao;

import com.xioazhu.rpccommon.model.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *@ClassName JwtUserRepository
 *@Description TODO
 *@Author zhusm
 *@Date 2018/11/5 19:48    
 *@Version 1.0
 */
@Repository
public interface JwtUserRepository extends CrudRepository<Users, Long> {


    /**
     * @Author zhusm
     * @Description 根据姓名获取用户
     * @Date 20:00 2018/11/5
     * @Param [username]
     * @return com.xioazhu.rpccommon.model.Users
     **/
    Users findByUsername(String username);

}
