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
        if (id < 0) {
            return null; // error string
        } else {
            return imageService.getImage(id);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/upload")
    public Image uploadFile(@ModelAttribute ImageUpload imageUpload) {
        if (imageUpload.getFile() == null || imageUpload.getCategories() == null || imageUpload.getTags() == null
        || imageUpload.getDescription() == null){
            return null; // error string
        } else {
            return imageService.saveImage(imageUpload);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping("/update/{id}")
    public Image updateImage(@PathVariable(value = "id") Long id, @ModelAttribute ImageUpdate imageUpdate) {
        if (id < 0 || imageUpdate.getCategories() == null || imageUpdate.getTags() == null
                || imageUpdate.getDescription() == null || imageUpdate.getName() == null){
            return null; // error string
        } else {
            return imageService.updateImage(id, imageUpdate);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable(value = "id") Long id) {
        if (id < 0) {
            return null; // error string
        } else {
            imageService.deleteImage(id);
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping("/search/{searchParams}")
    public List<Image> getAllImagesBySearch(@PathVariable(value = "searchParams") String searchParams) {
        return imageService.getAllImagesBySearch(searchParams);
    }
}