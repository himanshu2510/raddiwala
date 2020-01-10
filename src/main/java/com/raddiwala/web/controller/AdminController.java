package com.raddiwala.web.controller;

import com.raddiwala.web.model.*;
import com.raddiwala.web.repository.AdminRepository;
import com.raddiwala.web.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/admin-api")
public class AdminController {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/test")
    public String test(){
        return "admin- api working correctly";
    }

    @GetMapping("/admin")
    public List<Admin> getAdmin(){
        return adminRepository.findAll();
    }

    @PostMapping("/signup")
    public ResponseEntity<String> createAdmin(@RequestBody AdminSignupForm signupForm){
        Admin admin = adminRepository.findAllByUsername(signupForm.getUsername());
        if(admin != null){
            return new ResponseEntity<String>("Admin Already created",HttpStatus.BAD_REQUEST);
        }
        admin = new Admin.Builder()
                .username(signupForm.getUsername())
                .password(signupForm.getPassword()).build();
        adminRepository.save(admin);
        return new ResponseEntity<String>("admin created successfully",HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<String> adminLogin(@RequestBody LoginForm loginForm){
        Admin admin = adminRepository.findAllByUsername(loginForm.getUsername());
        if(admin.login(loginForm.getPassword())){
            return new ResponseEntity<String>("admin login successfully", HttpStatus.OK);
        }
        return new ResponseEntity<String>("incorrect password try again", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/home")
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    @PostMapping("/updateProducts")
    public ResponseEntity<String> updateProducts(@RequestBody List<Product> products) throws Exception {
        for(Product p : products){
            Product toUpdate = productRepository.findById(p.getId()).orElseThrow(()->new Exception("no product is found while updating products"));
            toUpdate.setMeasure(p.getMeasure());
            toUpdate.setPrice(p.getPrice());
            productRepository.save(toUpdate);
        }
        return new ResponseEntity<String>("products updated successfully",HttpStatus.OK);
    }

    @PostMapping("/add-product")
    public ResponseEntity<String> addProduct(@RequestBody ProductForm productForm){
        Product products = productRepository.findByProductName(productForm.getProductName());
        if(products != null){
            return new ResponseEntity<String>("product already added",HttpStatus.BAD_REQUEST);
        }
        Product newProduct = new Product.Builder()
                .productName(productForm.getProductName())
                .price(productForm.getPrice())
                .measure(productForm.getMeasure())
                .build();
        productRepository.save(newProduct);
        return new ResponseEntity<String>("product added successfully",HttpStatus.OK);

    }
}
