package com.example.py_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.py_backend.entity.*;
import com.example.py_backend.repository.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // UID로 유저 찾기
    public User findByUid(String uid) {
        return userRepository.findByUid(uid);
    }

    // UID로 없으면 회원가입, 있으면 로그인
    public User registerOrLogin(String uid, String email, String name) {
        User user = userRepository.findByUid(uid);
        if (user == null) {
            user = new User();
            user.setUid(uid);
            user.setEmail(email);
            user.setName(name);
            userRepository.save(user);
        }
        return user;
    }
}
