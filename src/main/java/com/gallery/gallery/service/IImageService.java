package com.gallery.gallery.service;

import com.gallery.gallery.entity.Image;
import com.gallery.gallery.payload.ImageUpdate;
import com.gallery.gallery.payload.ImageUpload;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface IImageService {

    Image saveImage(ImageUpload imageUpload);

    Image getImage(Long imageId);

    List<Image> getAllImages();

    void deleteImage(Long fileId);

    List<Image> getAllImagesBySearch(String searchString, List<Long> tagsIds, List<Long> categoriesIds);

    Image updateImage(ImageUpdate imageUpdate);
}
