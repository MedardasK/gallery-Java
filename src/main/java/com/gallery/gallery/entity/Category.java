package com.gallery.gallery.entity;

import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CATEGORY_ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Image> image = new HashSet<>();
}
