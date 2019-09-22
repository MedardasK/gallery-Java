package com.gallery.gallery.payload;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUpload {
        private MultipartFile file;
        private String description;
        private String categories;
        private String tags;
}
