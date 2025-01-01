package com.easy_bytes_spring_security.EazyBytes_Spring_Security.controller;

import com.easy_bytes_spring_security.EazyBytes_Spring_Security.model.Customer;
import com.easy_bytes_spring_security.EazyBytes_Spring_Security.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final CustomerRepository customerRepository;
    private  final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer){
        try{
            String hashPwd=passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashPwd);
            customer= customerRepository.save(customer);

            if(customer.getId()>0){
                return ResponseEntity.status(HttpStatus.CREATED).body("user registration is successful.");
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user registration failed.");
            }
        }catch (Exception e){
              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An exception occurred: "+e.getMessage());
        }
    }
}
