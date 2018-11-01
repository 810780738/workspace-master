package com.xiaozhu.rpm_server_mongo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RpmServerMongoApplicationTests {

	@Test
	public void contextLoads() {
		int i = Runtime.getRuntime().availableProcessors();
		System.out.println(i);
	}

}
