package com.raddiwala.web.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "admin")
public class Admin extends Auditable {

    public boolean login(String password) {
        if (this.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public Admin(){

    }

    private Admin(Builder builder){
        setUsername(builder.username);
        setPassword(builder.password);
    }

    public static final class Builder{
        private @NotBlank String username;
        private @NotBlank String password;

        public Builder(){

        }

        public Builder username(@NotBlank String val){
            username = val;
            return this;
        }

        public Builder password(@NotBlank String val){
            password = val;
            return this;
        }

        public Admin build(){
            return new Admin(this);
        }
    }
}


//    @Getter
//    @Setter
//    @NotBlank
//    private String username;
//
//    @Getter
//    @Setter
//    @NotBlank
//    private String password;

