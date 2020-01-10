package com.raddiwala.web.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product extends ProductAuditable {
    @Getter
    @Setter
    private String productName;

    @Getter
    @Setter
    private Long price;

    @Getter
    @Setter
    private String measure;

    public Product(){

    }
    private Product(Builder builder){
        setProductName(builder.productName);
        setPrice(builder.price);
        setMeasure(builder.measure);
    }

    public static final class Builder{
        private String productName;
        private String measure;
        private Long price;

        public Builder(){

        }

        public Builder productName(String val){
            productName = val;
            return this;
        }

        public Builder measure(String val){
            measure = val;
            return this;
        }
        public Builder price(Long val){
            price = val;
            return this;
        }

        public Product build(){
            return new Product(this);
        }
    }
}
