package com.gallery.gallery.DAO;

import com.gallery.gallery.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITagRep extends JpaRepository<Tag, Long> {

}