package org.chisa.auth;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient

public class MainAuth {
    public static void main(String[] args) {
        SpringApplication.run(MainAuth.class);
    }
}
