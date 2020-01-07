package com.raddiwala.web.controller;

import com.raddiwala.web.model.LoginForm;
import com.raddiwala.web.model.SignupForm;
import com.raddiwala.web.model.User;
import com.raddiwala.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user-api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable(name = "id") Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception("No user is found with this id"));
    }

    @PostMapping("signup")
    public String userSignup(@RequestBody SignupForm signupForm){
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
        return "user created successfully";

    }

    @PostMapping("/login")
    public String userLogin(@RequestBody LoginForm loginForm){
        //TODO: first check if username exists or not if not then return that user doesnot exist and if yes check the password
        User user = userRepository.findUserByUsername(loginForm.getUsername());
        if(user.login(loginForm.getPassword())){
            return "password matched login successful";
        }
        return "incorrect password try again";
    };

//    public createUser();
//
//    public updateUser();
//
//    public deleteUser();
}
