package com.blueprint;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.blueprint.module.*.serviceDal.mapper")
public class BlueprintApplication {




	public static void main(String[] args) {
		SpringApplication.run(BlueprintApplication.class, args);
	}
}
