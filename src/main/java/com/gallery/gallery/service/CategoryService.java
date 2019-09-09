package com.gallery.gallery.service;

import com.gallery.gallery.DAO.ICategoryRep;
import com.gallery.gallery.DAO.IImageRep;
import com.gallery.gallery.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private ICategoryRep categoryRep;

    public List<Category> findAllCategories() {
        return (List<Category>) categoryRep.findAll();
    }

    public void saveCategory(Category category) {
        categoryRep.save(category);
    }

//
//    Category getCategory(int categoryId);
//    Category updateCategory(Category category);
//    void deleteCategory(Category category);
//    void deleteCategory(int id);
}
