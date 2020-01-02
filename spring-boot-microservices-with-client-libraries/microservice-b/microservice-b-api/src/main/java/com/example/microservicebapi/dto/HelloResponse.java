package com.example.microservicebapi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("response model")
public class HelloResponse {
    @ApiModelProperty("greeting property")
    private String greeting;
}
