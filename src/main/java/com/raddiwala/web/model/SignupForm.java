package com.raddiwala.web.model;

import lombok.Getter;
import lombok.Setter;

public class SignupForm {
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Long phoneNumber;

    @Getter
    @Setter
    private String area;

    @Getter
    @Setter
    private String city;

    @Getter
    @Setter
    private Long pincode;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;
}
