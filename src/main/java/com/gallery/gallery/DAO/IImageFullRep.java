package com.gallery.gallery.DAO;

import com.gallery.gallery.entity.ImageFull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageFullRep extends JpaRepository<ImageFull, Long> {
}
