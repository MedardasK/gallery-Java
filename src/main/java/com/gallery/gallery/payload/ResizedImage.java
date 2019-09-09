package com.gallery.gallery.payload;

import lombok.Data;

@Data
public class ResizedImage {

    private byte[] data;
    private Integer width;
    private Integer height;

}
