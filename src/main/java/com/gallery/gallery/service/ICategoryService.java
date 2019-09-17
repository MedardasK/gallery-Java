package com.gallery.gallery.service;

import com.gallery.gallery.entity.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> findAllCategories();

    Category saveCategory(String name);
//    Category saveCategory(Category category);

    void deleteCategory(Long id);
}
