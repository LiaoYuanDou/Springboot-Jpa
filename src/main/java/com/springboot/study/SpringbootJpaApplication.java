package com.springboot.study;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.alibaba.fastjson.parser.ParserConfig;

@SpringBootApplication
@PropertySource("classpath:application.properties")
@ServletComponentScan(basePackages = "com.springboot.study.*")
@ComponentScan(basePackages = { "com.springboot.study.*" })
@EnableJpaRepositories(basePackages = { "com.springboot.study.repository" })
@EntityScan(basePackages = { "com.springboot.study.pojo" })
public class SpringbootJpaApplication {

	private static Logger logger = LoggerFactory.getLogger(SpringbootJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
		// 解决fastjson异常：autoType is not support
		ParserConfig.getGlobalInstance().addAccept("com.springboot.study.");
		logger.info("》》》》 服务启动完成 》》》》 SpringbootJpaApplication is Running ");
	}

}
