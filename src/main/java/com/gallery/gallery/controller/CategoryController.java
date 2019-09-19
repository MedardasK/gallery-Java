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

    @PostMapping("/create/{name}")
    public Category saveCategory(@RequestParam String name) {
        System.out.println("test");
        return categoryService.saveCategory(name);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
    }

}
