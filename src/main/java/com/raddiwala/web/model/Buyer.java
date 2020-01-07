package com.raddiwala.web.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "buyers")
public class Buyer extends Auditable{
    @Getter
    @Setter
    @NotBlank
    private String name;

    @Getter
    @Setter
    @NotBlank
    private String shopName;

    @OneToMany
    @Getter
    @Setter
    private List<Order> orders;

    @Getter
    @Setter
    @NotBlank
    private Long phoneNumber;


    public Buyer(){

    }

    private Buyer(Builder builder){
        setName(builder.name);
        setShopName(builder.shopName);
        setPhoneNumber(builder.phoneNumber);
    }

    public boolean login(String password) {
        if(this.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    public static final class Builder{
        private @NotBlank  String name;
        private @NotBlank String shopName;
        private @NotBlank Long phoneNumber;
        private @NotBlank String username;
        private @NotBlank String password;
        private @NotBlank String area;
        private @NotBlank String city;
        private @NotBlank Long pincode;


        public Builder(){

        }

        public Builder name(String val){
            name = val;
            return this;
        }

        public Builder shopName(String val){
            shopName = val;
            return this;
        }

        public Builder phoneNumber(Long val){
            phoneNumber = val;
            return this;
        }

        public Builder area(String val){
            area = val;
            return this;
        }

        public Builder city(String val){
            city = val;
            return this;
        }

        public Builder pincode(Long val){
            pincode = val;
            return this;
        }
        public Builder username(String val){
            username = val;
            return this;
        }

        public Builder password(String val){
            password = val;
            return this;
        }

        public Buyer build(){
            Buyer buyer = new Buyer(this);
            return buyer;
        }

    }

}
