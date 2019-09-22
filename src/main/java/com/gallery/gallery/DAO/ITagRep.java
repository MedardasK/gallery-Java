package com.gallery.gallery.DAO;

import com.gallery.gallery.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ITagRep extends JpaRepository<Tag, Long> {

    @Query("select t from Tag t where name = :name")
    Tag findByName(@Param("name") String name);

    @Query("select t from Tag t")
    List<Tag> findAll();

    Set<Tag> findByIdIn(List<Long> id);
}