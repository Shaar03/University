package com.project.University.service;

import com.project.University.entity.MyUser;
import com.project.University.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    MyUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> user = userRepository.findByUsername(username);

        if(user.isPresent()){
            MyUser actualUser = user.get();
            return User.builder()
                    .username(actualUser.getUsername())
                    .password(actualUser.getPassword())
                    .roles(actualUser.getRole().name())
                    .build();
        }
        else{
            throw new UsernameNotFoundException(username);
        }
    }
}
