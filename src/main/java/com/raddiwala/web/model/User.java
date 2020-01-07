package com.raddiwala.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends Auditable {
    @Getter
    @Setter
    @NotBlank
    private String name;

    @OneToMany
    @Getter
    @Setter
    List<Order> orders;

    @Getter
    @Setter
    @NotBlank
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
    @NotBlank
    private Long pincode;

    public User(){

    }

    private User(Builder builder){
        setName(builder.name);
        setPhoneNumber(builder.phoneNumber);
        setArea(builder.area);
        setCity(builder.city);
        setPincode(builder.pincode);
        setUsername(builder.username);
        setPassword(builder.password);
    }

    public boolean login(String password) {
        if(this.getPassword().equals(password)){
            //that means password gets matched
            return true;
        }
        return false;
    }

    public static final class Builder{
        private @NotBlank String name;
        private @NotBlank Long phoneNumber;
        private @NotBlank String area;
        private @NotBlank String city;
        private @NotBlank Long pincode;
        private @NotBlank String username;
        private @NotBlank String password;

        public Builder(){

        }

        public Builder name(String val){
            name = val;
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

        public User build(){
            User user = new User(this);
            return user;
        }
    }
}
