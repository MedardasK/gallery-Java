package com.gallery.gallery.DAO;

import com.gallery.gallery.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRep extends JpaRepository<Category, Long> {

    Category findByName(String name);

}
