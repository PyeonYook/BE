package com.example.py_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.example.py_backend.service.*;
import com.example.py_backend.entity.*;
import com.example.py_backend.dto.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterRequestDTO request) {
        boolean result = userService.register(request.getName(), request.getEmail(), request.getPassword());
        if(result) return ResponseEntity.ok("회원가입 성공");
        else return ResponseEntity.badRequest().body("이미 존재하는 이메일입니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequestDTO request) {
        User user = userService.login(request.getEmail(), request.getPassword());
        if(user != null) return ResponseEntity.ok("로그인 성공: " + user.getName());
        else return ResponseEntity.status(401).body("이메일/비밀번호를 확인하세요.");
    }
}