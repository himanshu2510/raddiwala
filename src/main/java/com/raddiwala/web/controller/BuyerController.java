package com.raddiwala.web.controller;

import com.raddiwala.web.model.Buyer;
import com.raddiwala.web.model.LoginForm;
import com.raddiwala.web.model.SignupForm;
import com.raddiwala.web.model.Buyer;
import com.raddiwala.web.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/buyer-api")
public class BuyerController {
    @Autowired
    BuyerRepository buyerRepository;

    @GetMapping("/buyers")
    public List<Buyer> getAllbuyers() {
        return buyerRepository.findAll();
    }

    @GetMapping("/buyer/{id}")
    public Buyer getbuyerById(@PathVariable(name = "id") Long id) throws Exception {
        return buyerRepository.findById(id).orElseThrow(() -> new Exception("No buyer is found with this id"));
    }

    @PostMapping("signup")
    public String buyerSignup(@RequestBody SignupForm signupForm){
        //TODO: first check if buyername already exists or not if yes then return that buyername already registered if not then create a new buyer
        Buyer buyer = new Buyer.Builder()
                .name(signupForm.getName())
                .phoneNumber(signupForm.getPhoneNumber())
                .username(signupForm.getUsername())
                .password(signupForm.getPassword())
                .area(signupForm.getArea())
                .city(signupForm.getCity())
                .pincode(signupForm.getPincode())
                .build();
        buyerRepository.save(buyer);
        return "buyer created successfully";

    }

    @PostMapping("/login")
    public String buyerLogin(@RequestBody LoginForm loginForm){
        //TODO: first check if buyername exists or not if not then return that buyer doesnot exist and if yes check the password
        Buyer buyer = buyerRepository.findBuyerByUsername(loginForm.getUsername());
        if(buyer.login(loginForm.getPassword())){
            return "password matched login successful";
        }
        return "incorrect password try again";
    };
}
