package com.gallery.gallery.service.implementations;

import com.gallery.gallery.DAO.ICategoryRep;
import com.gallery.gallery.DAO.IImageRep;
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
        return (List<Category>) categoryRep.findAll();
    }

    public Category saveCategory(Category category) {
        return categoryRep.save(category);
    }

    public void deleteCategory(Long tagId) {
        categoryRep.deleteById(tagId)
               /* .orElseThrow(() -> new MyFileNotFoundException("Category not found with id " + categoryId) {
                })*/
        ;
    }

//
//    Category getCategory(int categoryId);
//    Category updateCategory(Category category);
//    void deleteCategory(Category category);
//    void deleteCategory(int id);
}
