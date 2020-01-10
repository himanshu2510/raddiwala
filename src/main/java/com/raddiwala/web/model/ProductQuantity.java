package com.raddiwala.web.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

@Entity
public class ProductQuantity  extends ProductAuditable{
    @Getter
    @Setter
    private Product product;

    @Getter
    @Setter
    private Integer quantity;
}
