package com.raddiwala.web.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class Address {
    @Getter
    @Setter
    @NotBlank
    private String area;

    @Getter
    @Setter
    @NotBlank
    private String city;

    @Getter
    @Setter
    @NotBlank
    private Long pincode;
}
