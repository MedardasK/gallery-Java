package com.gallery.gallery.DAO;

import com.gallery.gallery.entity.Category;
import com.gallery.gallery.entity.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//public interface IImageRepository extends JpaRepository<Image, Long> {
// , IImageRepCustom
public interface IImageRep extends CrudRepository<Image, Long> {

    List<Image> findAllByName(String fileName);

    List<Image> findAllByCategories(List<Category> categoryList);


//    List<String> getAllImages();

}