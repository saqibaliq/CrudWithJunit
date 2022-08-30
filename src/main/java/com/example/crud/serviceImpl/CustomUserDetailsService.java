package com.example.crud.serviceImpl;

import com.example.crud.model.CustomUserDetails;
import com.example.crud.model.UserModel;
import com.example.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = null;
        try {
            userModel = userRepository.findByUserName(username);
            if (userModel == null) {
                throw new UsernameNotFoundException("User Not Found !!");
            } else {
                return new CustomUserDetails(userModel);
            }
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return null;
    }
}
