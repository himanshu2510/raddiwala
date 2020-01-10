package com.raddiwala.web.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SignupForm {
    @Getter
    @Setter
    @NotBlank
    private String name;

    @Getter
    @Setter
    @NotNull
    private Long phoneNumber;

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
    @NotNull
    private Long pincode;

    @Getter
    @Setter
    @NotBlank
    private String username;

    @Getter
    @Setter
    @NotBlank
    private String password;
}
