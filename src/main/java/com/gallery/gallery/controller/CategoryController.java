package com.gallery.gallery.controller;

import com.gallery.gallery.entity.Category;
import com.gallery.gallery.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/create")
    public Category saveCategory(@RequestBody Category category){
        return categoryService.saveCategory(category);
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
    }

//    @GetMapping("/{id}")
//    public Category getCategory(@PathVariable("id") int id){
//        return categoryService.getCategory(id);
//    }
//
//    @PutMapping("/update")
//    public Category updateCategory(@RequestBody Category category){
//        return categoryService.updateCategory(category);
//    }

}
