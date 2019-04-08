package com.qf.stu_service;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//扫描主包
@SpringBootApplication(scanBasePackages = "com.qf")
//扫描映射
@MapperScan("com.qf.dao")
//扫描接口实现
@DubboComponentScan("com.qf.service.impl")
public class StuServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StuServiceApplication.class, args);
	}

}
