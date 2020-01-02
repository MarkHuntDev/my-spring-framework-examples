package com.example.microservicebapi.controller;

import com.example.microservicebapi.dto.HelloRequest;
import com.example.microservicebapi.dto.HelloResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Api("Hello API")
@RequestMapping("/hello")
public interface HelloController {
    @PostMapping
    HelloResponse hello(@RequestBody HelloRequest request);
}
