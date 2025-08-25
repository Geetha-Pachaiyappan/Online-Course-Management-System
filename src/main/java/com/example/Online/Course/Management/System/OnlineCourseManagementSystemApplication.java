package com.example.Online.Course.Management.System;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OnlineCourseManagementSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(OnlineCourseManagementSystemApplication.class, args);
	}

}
