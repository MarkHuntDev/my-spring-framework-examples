package com.example.microservicea;

import com.example.microservicebapi.dto.HelloRequest;
import com.example.microservicebclient.feign.HelloClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@Slf4j
@EnableFeignClients(basePackageClasses = HelloClient.class)
@SpringBootApplication
public class MicroserviceA {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceA.class, args);
    }

    @Bean
    CommandLineRunner hello(HelloClient client) {
        return args -> {
            var request = new HelloRequest();
            request.setName("StackOverflow");
            var response = client.hello(request);
            log.info(response.getGreeting());
        };
    }
}
