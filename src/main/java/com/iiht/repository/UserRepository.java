package com.iiht.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iiht.entity.User;


public interface UserRepository extends JpaRepository<User,Integer> {
        User findByUsername(String username);
}


