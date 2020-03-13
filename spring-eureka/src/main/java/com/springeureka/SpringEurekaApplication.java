package com.springeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

@SpringBootApplication
@EnableEurekaServer
@EnableZuulServer
public class SpringEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEurekaApplication.class, args);
	}

}
