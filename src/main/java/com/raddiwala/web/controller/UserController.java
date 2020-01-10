package com.raddiwala.web.controller;

import com.raddiwala.web.model.*;
import com.raddiwala.web.repository.BuyerRepository;
import com.raddiwala.web.repository.ProductRepository;
import com.raddiwala.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user-api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BuyerRepository buyerRepository;

    @GetMapping("/test")
    public String test(){
        return "user- api working correctly";
    }
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable(name = "id") Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception("No user is found with this id"));
    }

    @PostMapping("signup")
    public ResponseEntity<String> userSignup(@RequestBody SignupForm signupForm){
        //TODO: first check if username already exists or not if yes then return that username already registered if not then create a new user
        User user = new User.Builder()
                .name(signupForm.getName())
                .phoneNumber(signupForm.getPhoneNumber())
                .username(signupForm.getUsername())
                .password(signupForm.getPassword())
                .area(signupForm.getArea())
                .city(signupForm.getCity())
                .pincode(signupForm.getPincode())
                .build();
        userRepository.save(user);
        return new ResponseEntity<String>("user created successfully", HttpStatus.OK);

    }

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody LoginForm loginForm){
        //TODO: first check if username exists or not if not then return that user doesnot exist and if yes check the password
        User user = userRepository.findUserByUsername(loginForm.getUsername());
        if(user.login(loginForm.getPassword())){

            return new ResponseEntity<String>("password matched login successful", HttpStatus.OK);
        }
        return new ResponseEntity<String>("incorrect password try again", HttpStatus.BAD_REQUEST);
    };

    @GetMapping("/home")
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

//    @PostMapping("/selected-products")
//    public List<Product> getSelectedProducts(@RequestBody SelectedProducts selectedProducts){
//        List<String> productNames = selectedProducts.getProductNames();
//        List<Product> products = new ArrayList<>();
//        for(String name:productNames){
//            products.add(productRepository.findByProductName(name));
//        }
//        return products;
//    }

    @GetMapping("/selected-products/{productList}")
    public List<Product> getSelectedProducts(@PathVariable(value = "productList") List<String> productList){
        System.out.println(productList);
        List<Product> products = new ArrayList<>();
        for(String name:productList){
            products.add(productRepository.findByProductName(name));
        }
        return products;
    }

    @PostMapping("/place-order")
    public String placeOrder(){
        return "order placed successfully";
    }

    @GetMapping("/get-buyers/{uid}")
    public List<Buyer> getBuyers(@PathVariable(value = "uid") Long userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(()->new Exception("no user found while getting buyers list"));
        return buyerRepository.findAllByCity(user.getCity());
    }

}
