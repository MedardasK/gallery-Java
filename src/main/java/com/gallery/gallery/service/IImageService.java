package com.gallery.gallery.service;

import com.gallery.gallery.entity.Category;
import com.gallery.gallery.entity.Image;
import com.gallery.gallery.entity.Tag;
import com.gallery.gallery.payload.ImageUpdate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface IImageService {

    Image saveImage(MultipartFile file, String description, Set<Category> categories, Set<Tag> tags);

    Image getImage(Long imageId);

    List<Image> getAllImages();

    void deleteImage(Long fileId);

    List<Image> customFindByNameDes(String text);

    List<Image> customFindByAny(String text);

    Image updateImage(ImageUpdate imageUpdate);
}
