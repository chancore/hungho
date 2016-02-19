package com.hungho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class HunghoApplication {

	public static void main(String[] args) throws Exception {
        SpringApplication.run(HunghoApplication.class, args);
    }
}
