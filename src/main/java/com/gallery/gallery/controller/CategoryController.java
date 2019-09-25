package com.gallery.gallery.controller;

import com.gallery.gallery.entity.Category;
import com.gallery.gallery.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    public ICategoryService categoryService;

    @GetMapping()
    public List<Category> categoryList(){
        return categoryService.findAllCategories();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/create")
    public Category createCategory(@RequestBody String name) {
        if (name == null) {
            return null;
        } else {
            return categoryService.saveCategory(name);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id){
        if (id < 0) {
            return null;
        } else {
            return categoryService.deleteCategory(id);
        }
    }

}
