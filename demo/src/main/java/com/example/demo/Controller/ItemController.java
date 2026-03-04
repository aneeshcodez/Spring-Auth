package com.example.demo.Controller;

import com.example.demo.Model.AuthReq;
import com.example.demo.Model.Item;
import com.example.demo.Model.User;
import com.example.demo.Repo.ItemRepo;
import com.example.demo.Repo.UserRepo;
import com.example.demo.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ItemController {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;


    // ADD ITEM
    @PostMapping("/add")
    public Item addItem(@RequestBody Item item) {
        return itemRepo.save(item);
    }

    // DELETE ITEM
    @DeleteMapping("/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemRepo.deleteById(id);
        return "Deleted Successfully";
    }
    // REGISTER NEW USER
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {

        // Check if username already exists
        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            return "Username already exists!";
        }

        // Save new user
        userRepo.save(user);
        return "User Registered Successfully";
    }

    // TESTING
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Map<String, String>> authenticateUser(@RequestBody AuthReq authReq) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword()));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        if (authentication.isAuthenticated()) {

            String token = jwtService.generateToken(userDetails);
            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            return ResponseEntity.ok(response);
        } else {
            throw new UsernameNotFoundException("Invalid user details");
        }
    }
}
