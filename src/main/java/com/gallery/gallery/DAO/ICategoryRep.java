package com.gallery.gallery.DAO;

import com.gallery.gallery.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRep extends CrudRepository<Category, Long> {

}
