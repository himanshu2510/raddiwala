package com.raddiwala.web.controller;
import com.google.gson.Gson;
import com.raddiwala.web.model.*;
import com.raddiwala.web.model.Buyer;
import com.raddiwala.web.repository.BuyerRepository;
import com.raddiwala.web.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/buyer-api")
public class BuyerController {
    @Autowired
    BuyerRepository buyerRepository;

    @Autowired
    OrderRepository orderRepository;

    private static final Gson gson = new Gson();

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

    @RequestMapping(value = "/signup",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> buyerSignup(@RequestBody BuyerSignupForm buyerSignupForm){

        Buyer buyer1  = buyerRepository.findBuyerByUsername(buyerSignupForm.getUsername());
        if(buyer1 != null){
            return new ResponseEntity<String>("buyer  exist already", HttpStatus.BAD_REQUEST);
        }
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
        return ResponseEntity.ok(gson.toJson("buyer created successfully"));


    }

    @RequestMapping(value = "/login",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> buyerLogin(@RequestBody LoginForm loginForm){
        Buyer buyer = buyerRepository.findBuyerByUsername(loginForm.getUsername());
        if(buyer!= null){
            if(buyer.login(loginForm.getPassword())) {
                return new ResponseEntity<String>("password matched login successful", HttpStatus.OK);
            }
        }
        return ResponseEntity.badRequest().body(gson.toJson("incorrect password"));
    };

    @GetMapping("/home/{bid}")
    public List<Order> orderList(@PathVariable(value = "bid") Long buyerId){
        return orderRepository.findAllByBuyerId(buyerId);
    }
}
