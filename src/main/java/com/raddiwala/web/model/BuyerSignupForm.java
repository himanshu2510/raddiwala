package com.raddiwala.web.model;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class BuyerSignupForm extends SignupForm {

    @Getter
    @Setter
    @NotBlank
    private String shopName;
}
