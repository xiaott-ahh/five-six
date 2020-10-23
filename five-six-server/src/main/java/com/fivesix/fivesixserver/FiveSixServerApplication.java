package com.fivesix.fivesixserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.fivesix.fivesixserver.mapper")
@SpringBootApplication
public class FiveSixServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FiveSixServerApplication.class, args);
	}

}
