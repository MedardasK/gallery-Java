package com.gallery.gallery.DAO;

import com.gallery.gallery.entity.ImageFull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageFullRep extends CrudRepository<ImageFull, Long> {
}
