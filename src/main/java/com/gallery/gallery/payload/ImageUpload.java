package com.gallery.gallery.payload;

import com.gallery.gallery.entity.Category;
import com.gallery.gallery.entity.Tag;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashSet;
import java.util.Set;

@Data
public class ImageUpload {
        private MultipartFile file;
        private String description;
        private Set<Tag> tags = new HashSet<>();
        private Set<Category> categories = new HashSet<>();
}
