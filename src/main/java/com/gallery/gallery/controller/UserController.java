package com.gallery.gallery.controller;

import com.gallery.gallery.entity.User;
import com.gallery.gallery.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController("users")
public class UserController {

    @Autowired
    private IUserService IUserService;

    @PostMapping("/register")
    public User create(@RequestBody User user){
        if (user.getUsername() == null || user.getPassword() == null) {
            return null;
        } else {
            return IUserService.save(user);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public List listUser(){
        return IUserService.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user/{id}")
    public User getOne(@PathVariable(value = "id") Long id){
        if (id < 0) {
            return null;
        } else {
            return IUserService.findById(id);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id){
        if (id < 0) {
            return null;
        } else {
            return IUserService.delete(id);
        }
    }

}