package com.raddiwala.web.controller;

import com.raddiwala.web.model.Admin;
import com.raddiwala.web.model.LoginForm;
import com.raddiwala.web.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/admin-api")
public class AdminController {

    @Autowired
    AdminRepository adminRepository;

    @GetMapping("/admin")
    public List<Admin> getAdmin(){
        return adminRepository.findAll();
    }


    @PostMapping("/login")
    public String adminLogin(@RequestBody LoginForm loginForm){
        Admin admin = adminRepository.findAdminByUsername(loginForm.getUsername());
        if(admin.login(loginForm.getPassword())){
            return "admin login successfully";
        }
        return "admin password incorrect try again";
    }

}
