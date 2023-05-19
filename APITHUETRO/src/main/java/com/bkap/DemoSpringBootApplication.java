package com.bkap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.bkap",exclude = {SecurityAutoConfiguration.class})
public class DemoSpringBootApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoSpringBootApplication.class, args);
    }

}
