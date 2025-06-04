package com.example.py_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.py_backend.service.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.example.py_backend.entity.*;
import com.example.py_backend.dto.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public AuthResponse firebaseLogin(@RequestBody IdTokenRequest request) throws Exception {
        // 1. idToken 검증 (Firebase Admin SDK 초기화 필요)
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(request.getIdToken());

        String uid = decodedToken.getUid();
        String email = decodedToken.getEmail();
        String name = (String) decodedToken.getClaims().get("name"); // 구글로그인 등

        // 2. 회원가입(최초)/로그인(기존) 처리
        User user = userService.registerOrLogin(uid, email, name);

        // 3. 응답
        return new AuthResponse(user.getId(), user.getUid(), user.getEmail(), user.getName());
    }

}