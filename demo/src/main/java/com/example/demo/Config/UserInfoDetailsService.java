package com.example.demo.Config;

import com.example.demo.Model.User;
import com.example.demo.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

// This class loads UserInfo from the database during login
@Component
public class UserInfoDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepo.findByUsername(username);

        // It is calling the constructor of UserInfoUserDetails
        return optionalUser.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
    }
}
