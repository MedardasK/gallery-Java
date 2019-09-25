package com.gallery.gallery.service;

import com.gallery.gallery.entity.User;

import java.util.List;

public interface IUserService {

    String delete(Long id);

    User findById(Long id);

    User save(User user);

    List<User> findAll();

}
