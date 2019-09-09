package com.gallery.gallery.controller;

import com.gallery.gallery.entity.Tag;
import com.gallery.gallery.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController

@RequestMapping("tags")
public class TagController {


    @Autowired
    private TagService tagService;

    // Get All Tags
    @GetMapping("/")
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }

    // Create a new Tag
    @PostMapping("/")
    public Tag saveTag(@RequestBody Tag tag) {
        return tagService.saveTag(tag);
    }

    // Get a Single Tag
    @GetMapping("/tag/{id}")
    public Tag getTagById(@PathVariable(value = "id") Long tagId) {
        return tagService.getTagById(tagId);
        // .orElseThrow(() -> new ResourceNotFoundException("Tag", "id", tagId))
    }

    // Update Tag
    @PutMapping("/tag/{id}")
    public Tag updateTag(@PathVariable(value = "id") Long tagId,
                           @Valid @RequestBody Tag tagName) {

        Tag tag = tagService.getTagById(tagId);
        //  .orElseThrow(() -> new ResourceNotFoundException("Tag", "id", tagId))

        tagName.setName(tagName.getName());

        Tag updatedTag = tagService.saveTag(tag);
        return updatedTag;
    }

    // Delete Tag
    @DeleteMapping("/tag/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable(value = "id") Long tagId) {
        // ar cia veikia?
        Tag tag = tagService.getTagById(tagId);
        // .orElseThrow(() -> new ResourceNotFoundException("Tag", "id", tagId))

        tagService.deleteTag(tagId);

        return ResponseEntity.ok().build();
    }

}