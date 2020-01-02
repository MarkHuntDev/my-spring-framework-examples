package com.example.microservicebapi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel("request model")
public class HelloRequest {
    @NotNull
    @ApiModelProperty("name property")
    private String name;
}
