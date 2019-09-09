package com.gallery.gallery.entity;

import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "TAG")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "TAG_ID",unique=true, nullable = false)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DATE")
    private String date = new SimpleDateFormat("yyyy.MM.dd").format(new Date());

    @ManyToMany(mappedBy = "tags")
    private Set<Image> files = new HashSet<>();

    public Tag() {
    }

}
