package com.major.devsolver_backend;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class DevsolverBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevsolverBackendApplication.class, args);
	}
}
