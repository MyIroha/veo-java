package org.chisa.getway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MainGetWay {
    public static void main(String[] args) {
        SpringApplication.run(MainGetWay.class);
    }
}
