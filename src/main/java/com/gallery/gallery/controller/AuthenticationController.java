package com.gallery.gallery.controller;


import com.gallery.gallery.entity.User;
import com.gallery.gallery.service.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthenticationController {

    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser) throws AuthenticationException {
        if (loginUser.getUsername() == null || loginUser.getUsername() == null) {
            return null;
        } else {
            return ResponseEntity.ok(authenticationService.loginUser(loginUser));
        }

    }

}