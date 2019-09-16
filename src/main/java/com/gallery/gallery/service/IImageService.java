package com.gallery.gallery.service;

import com.gallery.gallery.entity.Image;
import com.gallery.gallery.payload.ImageUpdate;
import com.gallery.gallery.payload.ImageUpload;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {

    Image saveImage(ImageUpload imageUpload);

    Image getImage(Long imageId);

    List<Image> getAllImages();

    void deleteImage(Long fileId);

    List<Image> customFindByNameDes(String text);

    List<Image> customFindByAny(String text);

    Image updateImage(ImageUpdate imageUpdate);
}
