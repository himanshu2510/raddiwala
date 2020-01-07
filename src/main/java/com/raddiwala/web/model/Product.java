package com.raddiwala.web.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product extends Auditable {
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
