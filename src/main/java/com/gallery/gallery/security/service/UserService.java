package com.gallery.gallery.security.service;

import com.gallery.gallery.entity.User;

import java.util.List;

public interface UserService {

    void delete(Long id);
    User findOne(String username);
    User findById(Long id);
    User save(User user);

    List findAll();
}
