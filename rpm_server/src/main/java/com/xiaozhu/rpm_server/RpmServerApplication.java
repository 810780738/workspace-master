package com.xiaozhu.rpm_server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@ComponentScan(basePackages = {"com.xiaozhu.rpm_server.conf","com.xiaozhu.rpm_server.service"})
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.xiaozhu.rpm_server.dao"})//扫描继承了JpaRepository的子类
@EntityScan(basePackages = "com.xioazhu.rpccommon.model")//扫描Entity
public class RpmServerApplication {

	public static void main(String[] args) {
	    SpringApplication.run(RpmServerApplication.class, args);
	}

}
