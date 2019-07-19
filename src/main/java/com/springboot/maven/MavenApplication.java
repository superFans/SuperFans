package com.springboot.maven;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 * Created by xuebusi.com on 2017/4/13.
 */
@SpringBootApplication
@MapperScan("com.springboot.maven.mapper")
public class MavenApplication {

	public static void main(String[] args) {
		SpringApplication.run(MavenApplication.class, args);
	}
}
