package com.datingapp.datingapp.service;

import com.datingapp.datingapp.dao.UserRepository;
import com.datingapp.datingapp.entity.User;
import com.datingapp.datingapp.entity.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(final String phoneNumber) throws UsernameNotFoundException {
        final Optional<User> user = userRepo.findByPhoneNumber(phoneNumber);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("Phone number not found!");
        }
        return new UserDetailsImpl(user.get());
    }

}
