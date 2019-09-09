package com.gallery.gallery.controller;

import com.gallery.gallery.entity.Category;
import com.gallery.gallery.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    public CategoryService categoryService;

    @GetMapping()
    public List<Category> categoryList(){
        return categoryService.findAllCategories();
    }

    @PostMapping()
    public void saveCategory(@RequestBody Category category){
        categoryService.saveCategory(category);
    }

    @GetMapping("/")
    @ResponseBody
    public String getFoos(@RequestParam List<String> id) {
        return "ID: " + id;
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
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @DeleteMapping("/delete")
//    public void deleteCategory(@RequestBody Category category){
//        categoryService.deleteCategory(category);
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @DeleteMapping("/delete/{id}")
//    public void deleteCategory(@PathVariable("id") int id){
//        categoryService.deleteCategory(id);
//    }


}
