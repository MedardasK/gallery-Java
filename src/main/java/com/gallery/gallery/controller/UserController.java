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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public List listUser(){
        return IUserService.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/user/{id}")
    public User getOne(@PathVariable(value = "id") Long id){
        return IUserService.findById(id);
    }

    @PostMapping("/register")
    public User create(@RequestBody User user){
        return IUserService.save(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable(value = "id") Long id){
        IUserService.delete(id);
    }

}