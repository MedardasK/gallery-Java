package com.gallery.gallery.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gallery.gallery.entity.Category;
import com.gallery.gallery.entity.Image;
import com.gallery.gallery.entity.Tag;
import com.gallery.gallery.payload.ImageUpdate;
import com.gallery.gallery.payload.ImageUpload;
import com.gallery.gallery.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("images")
public class ImageController {

    @Autowired
    private IImageService imageService;

    @GetMapping()
    public List<Image> getAllImages() {
        return imageService.getAllImages();
    }

    @GetMapping("/image/{id}")
    public Image getImageById(@PathVariable(value = "id") Long fileId) {
        return imageService.getImage(fileId);
    }

    @JsonIgnore
    @PostMapping("/upload")
    public Image uploadFile(@RequestBody ImageUpload imageUpload) {
        return imageService.saveImage(imageUpload);
    }

//    @PostMapping("/upload")
//    public Image uploadFile(@RequestParam("file") MultipartFile image, String description,
//                            Set<Category> categories, Set<Tag> tags) {
//        return imageService.saveImage(image, description, categories, tags);
//    }

//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable(value = "id") Long imageId) {
        // checkint??
        imageService.deleteImage(imageId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update/{imageUpdate}")
    public Image updateImage(@RequestBody ImageUpdate imageUpdate) {
        return imageService.updateImage(imageUpdate);
    }
}