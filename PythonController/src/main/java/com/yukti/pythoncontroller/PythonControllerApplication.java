package com.yukti.pythoncontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Bishwadeep Roy
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class PythonControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PythonControllerApplication.class, args);
	}

}
