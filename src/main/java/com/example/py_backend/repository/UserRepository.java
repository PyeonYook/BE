package com.example.py_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.py_backend.entity.*;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);

    User findByEmail(String email);
}