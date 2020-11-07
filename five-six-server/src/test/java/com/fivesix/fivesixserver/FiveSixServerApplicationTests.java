package com.fivesix.fivesixserver;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class FiveSixServerApplicationTests {

	@Test
	void contextLoads() {
	}

	@Before
	public void init(){
		System.out.println("开始测试");
	}

	@After
	public void finish(){
		System.out.println("测试完成");
	}
}
