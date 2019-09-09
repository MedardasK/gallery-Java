package com.gallery.gallery.controller;

import com.gallery.gallery.entity.Category;
import com.gallery.gallery.entity.Image;
import com.gallery.gallery.entity.Tag;
import com.gallery.gallery.service.ImageService;
import com.gallery.gallery.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("images")
public class ImageController {

    @Autowired
    private ImageService imageService;
    @Autowired
    private TagService tagService;

    @GetMapping()
    public List<Image> getAllImages() {
        return imageService.getAllFiles();
    }

    @GetMapping("/image/{id}")
    public Image getImageById(@PathVariable(value = "id") Long fileId) {
        return imageService.getFile(fileId);
    }


    @PostMapping()
    public Image uploadFile(@RequestParam("file") MultipartFile file, String description) {
        return imageService.storeFile(file, description);
    }

   /* @PostMapping("/addCategory/{id}")
    public void addCategoryToImage(@PathVariable Long id, @RequestBody Category category) {
        imageService.addCategory(id, category);
    }*/

    // Delete a File
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable(value = "id") Long fileId) {
        // checkint??
        imageService.deleteFile(fileId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/imagesByCategories/{list}")
    public List<Image> getAllImagesByCategories(@PathVariable(value = "list") List<Category> list) {
        return imageService.getAllImagesByCategories(list);
    }

    @PostMapping("/update/{id}")
    public Image updateImage(@RequestParam("description") Long id, String description, String name,
                             List<Long> categoriesIds, List<Long> tagsIds) {
        return imageService.updateImage(id, description, name, categoriesIds, tagsIds);
    }

//    @GetMapping("/search/{text}")
//    public List<Image> getAllFilesByTagName(@PathVariable(value = "text") String text) {
//        List<Image> dbFiles = imageService.findByNameDes(text);
//        return dbFiles;
//    }

    /*
    @GetMapping("/search/{tagName}")
    public List<DBFile> getAllFilesByTagName(@PathVariable(value = "tagName") String tagName) {

        // gauti tag id pagal tag name

        // surasti visus files pagal tagID

        Long tagId;

        List<Tag> allTags = tagRepository.findAll();
        List<Image> matchedFiles = null;
        for (Tag tag : allTags){
            if(tag.getTagName().equals(tagName)){
                *//*Image dbFile = DBFileStorageService.getFileByTagName(tagName);*//*
               *//* matchedFiles.add(DBFileStorageService.getFileByTagName(tagName));*//*
            }
        }
        return DBFileStorageService.getFilesByTagName(tagName);
    }*/

    /*@PostMapping("/uploadMultipleFiles")
    public List<UploadImageResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }*/

   /* @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) {
        // Load file from database
        Image image = ImageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getName() + "\"")
                .body(new ByteArrayResource(image.getData()));
    }*/

}