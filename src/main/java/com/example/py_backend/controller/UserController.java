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

        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(request.getIdToken());

        String uid = decodedToken.getUid();
        String email = decodedToken.getEmail();
        String name = (String) decodedToken.getClaims().get("name");

        User user = userService.registerOrLogin(uid, email, name);

        return new AuthResponse(user.getId(), user.getUid(), user.getEmail(), user.getName());
    }

}