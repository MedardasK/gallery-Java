package com.gallery.gallery.DAO;


import com.gallery.gallery.entity.Image;

import java.util.List;

public interface IImageRepCustom {

//    List<String> getAllImages();
    List<Image> customFindByNameDes(String text);
}
