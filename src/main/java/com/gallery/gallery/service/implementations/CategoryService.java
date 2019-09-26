package com.gallery.gallery.service.implementations;

import com.gallery.gallery.DAO.ICategoryRep;
import com.gallery.gallery.entity.Category;
import com.gallery.gallery.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRep categoryRep;

    public List<Category> findAllCategories() {
        return categoryRep.findAll();
    }

    public Category saveCategory(String name) {
        if (categoryRep.findByName(name.toLowerCase()) == null){
            Category category = new Category();
            category.setName(name);
            return categoryRep.save(category);
        } else {
            return null;
        }
    }

    public String deleteCategory(Long tagId) {
        try {
            categoryRep.deleteById(tagId);
            return "Success";
        } catch (Exception exception) {

            return "Failed";
        }
    }
}
