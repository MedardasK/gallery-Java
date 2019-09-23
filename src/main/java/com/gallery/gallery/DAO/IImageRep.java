package com.gallery.gallery.DAO;

import com.gallery.gallery.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageRep extends JpaRepository<Image, Long> {

    @Query("select i from Image i inner join i.imageFull where i.id = :id")
    Image findByIdCustom(@Param("id") Long id);

}