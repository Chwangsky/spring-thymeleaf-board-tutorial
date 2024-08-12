package com.board.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.board.demo.mapper")
public class DemoApplication {

	public static void main(String[] args) {
		System.out.println("ver1");
		SpringApplication.run(DemoApplication.class, args);
	}

}
