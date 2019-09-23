package com.gallery.gallery.service;

import com.gallery.gallery.entity.Image;
import com.gallery.gallery.payload.ImageUpdate;
import com.gallery.gallery.payload.ImageUpload;

import java.util.List;

public interface IImageService {

    Image saveImage(ImageUpload imageUpload);

    Image getImage(Long imageId);

    List<Image> getAllImages();

    void deleteImage(Long fileId);

    List<Image> getAllImagesBySearch(String searchString, List<String> tagsArray, List<Long> categoriesIds);

    Image updateImage(Long id, ImageUpdate imageUpdate);
}
