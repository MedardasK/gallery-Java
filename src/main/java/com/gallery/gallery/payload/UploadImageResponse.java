package com.gallery.gallery.payload;

import lombok.Data;

@Data
public class UploadImageResponse {
    private String imageName;
    private String imageDownloadUri;
    private String imageType;
    private long size;
}