package com.example.py_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.py_backend.entity.*;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUid(String uid);
    User findByEmail(String email);
}