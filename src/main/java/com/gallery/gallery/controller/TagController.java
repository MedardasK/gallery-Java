package com.gallery.gallery.controller;

import com.gallery.gallery.entity.Tag;
import com.gallery.gallery.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("tags")
public class TagController {

    @Autowired
    private ITagService tagService;

    @GetMapping()
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/create")
    public Tag saveTag(@RequestBody String name) {
        if (name == null) {
            return null;
        } else {
            return tagService.saveTag(name);
        }
    }

    @GetMapping("/tag/{id}")
    public Tag getTagById(@PathVariable(value = "id") Long id) {
        if (id < 0) {
            return null;
        } else {
            return tagService.getTagById(id);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public String deleteTag(@PathVariable("id") Long id){
        if (id < 0) {
            return null;
        } else {
            return tagService.deleteTag(id);
        }
    }
}