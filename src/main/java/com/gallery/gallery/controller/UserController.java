package com.gallery.gallery.controller;

import com.gallery.gallery.entity.User;
import com.gallery.gallery.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController("users")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> create(@RequestBody User user){
        if (user.getUsername() != null || user.getPassword() != null) {
            return ResponseEntity.ok(userService.save(user));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public List listUser(){
        return userService.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getOne(@PathVariable(value = "id") Long id){
        if (id > 0) {
            return ResponseEntity.ok(userService.findById(id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "id") Long id){
        if (id > 0) {
            return ResponseEntity.ok(userService.delete(id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}