package com.example.py_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.py_backend.entity.*;

public interface UserRepository extends JpaRepository<User, Long> {
    //파이어베이스 uid로 찾기
    User findByUid(String uid);

    //이메일로 찾기
    User findByEmail(String email);
}