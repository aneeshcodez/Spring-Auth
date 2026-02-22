package com.example.demo.Controller;

import com.example.demo.Model.Item;
import com.example.demo.Model.User;
import com.example.demo.Repo.ItemRepo;
import com.example.demo.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ItemController {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private UserRepo userRepo;


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
}
