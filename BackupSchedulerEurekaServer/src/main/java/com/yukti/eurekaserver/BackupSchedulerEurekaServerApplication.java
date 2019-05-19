package com.yukti.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Bishwadeep Roy
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class BackupSchedulerEurekaServerApplication {

	public static void main(String[] args) {

		//boostrap stater
		SpringApplication.run(BackupSchedulerEurekaServerApplication.class, args);
	}

}
