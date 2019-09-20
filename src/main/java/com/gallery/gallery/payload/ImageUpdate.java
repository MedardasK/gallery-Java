package com.gallery.gallery.payload;

import com.gallery.gallery.entity.Category;
import com.gallery.gallery.entity.Tag;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class ImageUpdate {

    private String name;
    private String description;
    private String date = new SimpleDateFormat("yyyy.MM.dd").format(new Date());
    private Set<Tag> tags = new HashSet<>();
    private Set<Category> categories = new HashSet<>();
}
