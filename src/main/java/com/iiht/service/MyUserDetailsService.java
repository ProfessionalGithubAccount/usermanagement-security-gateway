package com.iiht.service;

import com.iiht.entity.Professor;
import com.iiht.entity.User;
import com.iiht.repository.ProfessorRepository;
import com.iiht.repository.StudentRepository;

import com.iiht.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        List authorities=new ArrayList();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return new org.springframework.security.core.userdetails.User
                (user.getUsername(), user.getPassword(), authorities);

//        return new org.springframework.security.core.userdetails.User
//                (user.getUsername(), user.getPassword());
    }




}
