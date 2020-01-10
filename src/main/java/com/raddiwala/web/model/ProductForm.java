package com.raddiwala.web.model;

import lombok.Getter;
import lombok.Setter;

public class ProductForm {
    @Getter
    @Setter
    private String productName;
    @Getter
    @Setter
    private Long price;
    @Getter
    @Setter
    private String measure;
}
