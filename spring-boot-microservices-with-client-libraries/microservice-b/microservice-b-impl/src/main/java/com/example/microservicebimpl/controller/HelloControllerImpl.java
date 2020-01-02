package com.example.microservicebimpl.controller;

import com.example.microservicebapi.controller.HelloController;
import com.example.microservicebapi.dto.HelloRequest;
import com.example.microservicebapi.dto.HelloResponse;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControllerImpl implements HelloController {
    @Override
    public HelloResponse hello(HelloRequest request) {
        var hello = new HelloResponse();
        hello.setGreeting("Hello " + request.getName());
        return hello;
    }
}
