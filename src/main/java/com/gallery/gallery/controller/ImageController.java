package com.gallery.gallery.controller;

import com.gallery.gallery.entity.Image;
import com.gallery.gallery.payload.ImageUpdate;
import com.gallery.gallery.payload.ImageUpload;
import com.gallery.gallery.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
    public Image getImageById(@PathVariable(value = "id") Long id) {
        return imageService.getImage(id);
    }

    @PostMapping("/upload")
    public Image uploadFile(@ModelAttribute ImageUpload imageUpload) {
        return imageService.saveImage(imageUpload);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable(value = "id") Long id) {
        imageService.deleteImage(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public Image updateImage(@PathVariable(value = "id") Long id, @ModelAttribute ImageUpdate imageUpdate) {
        return imageService.updateImage(id, imageUpdate);
    }

    @GetMapping("/search/{searchString}{tagsIds}{categoriesIds}")
    public List<Image> getAllImagesBySearch(@PathVariable(value = "searchString") String searchString,
                                            @PathVariable(value = "tagsIds") List<Long> tagsIds,
                                            @PathVariable(value = "categoriesIds") List<Long> categoriesIds) {
        return imageService.getAllImagesBySearch(searchString, tagsIds, categoriesIds);
    }
}