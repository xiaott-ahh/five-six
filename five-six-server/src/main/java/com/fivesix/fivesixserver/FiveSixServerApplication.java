package com.fivesix.fivesixserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FiveSixServerApplication {

	public static void main(String[] args) {
		System.out.println("hello,world");
		SpringApplication.run(FiveSixServerApplication.class, args);
	}

}
