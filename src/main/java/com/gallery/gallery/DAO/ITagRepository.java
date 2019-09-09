package com.gallery.gallery.DAO;

import com.gallery.gallery.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ITagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findAllByName(String name);

}