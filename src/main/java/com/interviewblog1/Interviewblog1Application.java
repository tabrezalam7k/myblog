package com.interviewblog1;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Interviewblog1Application {

	public static void main(String[] args) {
		SpringApplication.run(Interviewblog1Application.class, args);
	}
	@Bean
	public ModelMapper mapper(){
		ModelMapper mapper = new ModelMapper();
		return mapper;
	}

}
