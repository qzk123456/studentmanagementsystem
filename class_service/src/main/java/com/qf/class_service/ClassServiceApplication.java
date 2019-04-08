package com.qf.class_service;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//扫描主包
@SpringBootApplication(scanBasePackages = "com.qf")
//扫描映射接口
@MapperScan("com.qf.dao")
//扫描接口实现
@DubboComponentScan("com.qf.service.impl")
public class ClassServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassServiceApplication.class, args);
	}

}
