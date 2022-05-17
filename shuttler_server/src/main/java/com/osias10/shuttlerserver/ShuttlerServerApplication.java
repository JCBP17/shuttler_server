package com.osias10.shuttlerserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@ComponentScan(basePackages={"com.osias10.shuttlerserver.controller"})
@SpringBootApplication
public class ShuttlerServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShuttlerServerApplication.class, args);
	}

}
