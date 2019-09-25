package com.gallery.gallery.payload;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class ImageUpdate {

    private String name;
    private String description;
    private String date = new SimpleDateFormat("yyyy.MM.dd").format(new Date());
    private String tags;
    private String categories;
}
