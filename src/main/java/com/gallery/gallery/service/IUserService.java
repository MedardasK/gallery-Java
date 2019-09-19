package com.gallery.gallery.service;

import com.gallery.gallery.entity.User;

import java.util.List;

public interface IUserService {

    void delete(Long id);
    User findOne(String username);
    User findById(Long id);
    User save(User user);

    List<User> findAll();
}