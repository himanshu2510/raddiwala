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
        if(this.getPassword().equals(password)){
            return true;
        }
        return false;
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
}
