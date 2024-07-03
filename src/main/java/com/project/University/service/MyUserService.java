package com.project.University.service;

import com.project.University.entity.MyUser;
import com.project.University.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserService {

    @Autowired
    private MyUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public MyUser addUser(MyUser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
