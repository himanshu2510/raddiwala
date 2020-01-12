package com.raddiwala.web.controller;

import com.raddiwala.web.model.*;
import com.raddiwala.web.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
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

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

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
        System.out.println("backend");
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

//            return new RedirectView("http://localhost:8080/user-api/home");
            return new ResponseEntity<String>("password matched login successful", HttpStatus.OK);
        }
        return new ResponseEntity<String>("incorrect password try again", HttpStatus.BAD_REQUEST);
//        return new RedirectView("http://localhost:8080/user-api/login");
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

    @GetMapping("/redirectWithRedirectView")
    public RedirectView redirect(RedirectAttributes attr){
//        System.out.println();
//        attr.addFlashAttribute("flashAttribute","redirectWithRedirectView");
//        attr.addAttribute("attribute","redirectWithRedirectView");
        return new RedirectView("http://localhost:8080/user-api/home");
    }

}
