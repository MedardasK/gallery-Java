package com.gallery.gallery.service;

import com.gallery.gallery.entity.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> findAllCategories();

    Category saveCategory(String name);

    String deleteCategory(Long id);

}
