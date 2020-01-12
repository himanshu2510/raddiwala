package com.raddiwala.web.controller;
import com.google.gson.Gson;
import com.raddiwala.web.model.*;
import com.raddiwala.web.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Null;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@SessionAttributes("userId")
@RequestMapping("/user-api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BuyerRepository buyerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    private static String sessionUserName = new String();

    private static   Gson gson = new Gson();

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

    @RequestMapping(value = "/signup",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> userSignup(@RequestBody SignupForm signupForm){

        System.out.println("backend");
        //TODO: first check if username already exists or not if yes then return that username already registered if not then create a new user


        User usr  = userRepository.findUserByUsername(signupForm.getUsername());
        if(usr != null){
            return ResponseEntity.badRequest().body(gson.toJson("user exist already"));
        }

        User user = new User.Builder()
                .name(signupForm.getName())
                .phoneNumber(signupForm.getPhoneNumber())
                .username(signupForm.getUsername())
                .password(signupForm.getPassword())
                .area(signupForm.getArea())
                .city(signupForm.getCity())
                .pincode(signupForm.getPincode())
                .build();
        sessionUserName = user.getUsername();
        userRepository.save(user);
        return ResponseEntity.ok(gson.toJson("user created successfully"));

    }

    @RequestMapping(value = "/login",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> userLogin(@RequestBody LoginForm loginForm){

        User user = userRepository.findUserByUsername(loginForm.getUsername());
        if(user!= null) {
            if (user.login(loginForm.getPassword())) {
                return ResponseEntity.ok(gson.toJson("password matched"));
            }
        }
            return ResponseEntity.badRequest().body(gson.toJson("incorrect password"));
    }

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
    public Invoice placeOrder(@RequestBody Order tentativeOrder) throws Exception {
        System.out.println(tentativeOrder);
        Long invoiceAmount = 0l;
       List<ProductQuantity> prodQtyMap = tentativeOrder.getProdQty();
       System.out.println(prodQtyMap);
       for(ProductQuantity p:prodQtyMap){
           Product product = productRepository.findById(p.getProductId()).orElseThrow(()->new Exception("product not found"));
           invoiceAmount += product.getPrice()*p.getQuantity();
       }
        orderRepository.save(tentativeOrder);
       Invoice invoice = new Invoice();
       invoice.setOrderId(tentativeOrder.getId());
       System.out.println(tentativeOrder.getId());
       invoice.setInvoiceAmount(invoiceAmount);
       invoiceRepository.save(invoice);
        return invoice;
    }

    @GetMapping("show-orders")
    public List<Order> getOrders(){
        return orderRepository.findAll();
    }

    @GetMapping("/get-buyers/{uid}")
    public List<Buyer> getBuyers(@PathVariable(value = "uid") Long userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(()->new Exception("no user found while getting buyers list"));
        return buyerRepository.findAllByCity(user.getCity());
    }

    @RequestMapping(value = "/getUserId",method = RequestMethod.GET, produces =MediaType.APPLICATION_JSON_VALUE)
    public Long getUserId(){
        User user = userRepository.findUserByUsername(sessionUserName);
        return user.getId();
    }



}
