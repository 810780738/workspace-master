package com.xiaozhu.rpm_server.dao;

import com.xioazhu.rpccommon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String userName);

    @Query("select u from User u where u.name=:name")
    User findUser(@Param("name") String name);
}
