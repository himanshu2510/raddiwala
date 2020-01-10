package com.raddiwala.web.controller;

import com.raddiwala.web.model.*;
import com.raddiwala.web.model.Buyer;
import com.raddiwala.web.repository.BuyerRepository;
import com.raddiwala.web.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/buyer-api")
public class BuyerController {
    @Autowired
    BuyerRepository buyerRepository;

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/test")
    public String test(){
        return "buyer- api working correctly";
    }
    @GetMapping("/buyers")
    public List<Buyer> getAllbuyers() {
        return buyerRepository.findAll();
    }

    @GetMapping("/buyer/{id}")
    public Buyer getbuyerById(@PathVariable(name = "id") Long id) throws Exception {
        return buyerRepository.findById(id).orElseThrow(() -> new Exception("No buyer is found with this id"));
    }

    @PostMapping("signup")
    public ResponseEntity<String> buyerSignup(@RequestBody BuyerSignupForm buyerSignupForm){
        //TODO: first check if buyername already exists or not if yes then return that buyername already registered if not then create a new buyer
        Buyer buyer = new Buyer.Builder()
                .name(buyerSignupForm.getName())
                .shopName(buyerSignupForm.getShopName())
                .phoneNumber(buyerSignupForm.getPhoneNumber())
                .username(buyerSignupForm.getUsername())
                .password(buyerSignupForm.getPassword())
                .area(buyerSignupForm.getArea())
                .city(buyerSignupForm.getCity())
                .pincode(buyerSignupForm.getPincode())
                .build();
        buyerRepository.save(buyer);
        return new ResponseEntity<String>("buyer created successfully", HttpStatus.OK);

    }

    @PostMapping("/login")
    public ResponseEntity<String> buyerLogin(@RequestBody LoginForm loginForm){
        //TODO: first check if buyername exists or not if not then return that buyer doesnot exist and if yes check the password
        Buyer buyer = buyerRepository.findBuyerByUsername(loginForm.getUsername());
        if(buyer.login(loginForm.getPassword())){
            return new ResponseEntity<String>("password matched login successful", HttpStatus.OK);
        }
        return new ResponseEntity<String>("incorrect password try again", HttpStatus.BAD_REQUEST);
    };

    @GetMapping("/home/{bid}")
    public List<Order> orderList(@PathVariable(value = "bid") Long buyerId){
        return orderRepository.findAllByBuyer(buyerId);
    }
}
