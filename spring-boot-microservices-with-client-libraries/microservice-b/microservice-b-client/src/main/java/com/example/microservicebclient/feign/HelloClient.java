package com.example.microservicebclient.feign;

import com.example.microservicebapi.controller.HelloController;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "hello", url = "http://localhost:8181")
public interface HelloClient extends HelloController {
}
