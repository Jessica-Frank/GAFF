package com.gaff.demo;

import com.gaff.demo.models.User;
import com.gaff.demo.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/*
 * This class is used to load users and their details from our database.
 * Last updated 11/14/2022
 * Author(s): Jessica Frank
 */
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository appUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = appUserRepo.getUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username:" + username);
        }
        return new CustomUserDetails(user);
    }

}
