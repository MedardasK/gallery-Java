package com.gallery.gallery.entity;

import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "IMAGE")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ID",unique=true, nullable = false)
    private Long id;

    @Column(name = "DATA")
    private byte[] data;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "SIZE")
    private Long size;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "HEIGHT")
    private int height;

    @Column(name = "WIDTH")
    private int width;

    @Column(name = "DATE")
    private String date = new SimpleDateFormat("yyyy.MM.dd").format(new Date());

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "IMAGE_TAG",
            joinColumns = @JoinColumn(name = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TAG_ID")
    )

    private Set<Tag> tags = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "IMAGE_CATEGORY",
            joinColumns = @JoinColumn(name = "ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID")
    )
    private Set<Category> categories = new HashSet<>();

    @JoinColumn(name = "IMAGE_FULL_ID")
    @OneToOne(cascade = CascadeType.ALL)
//    @Transient
    private ImageFull imageFull;

    public Image() {
    }

    public Image(String name, String type, long size, byte[] data, String description, Integer height, Integer width, ImageFull imageFull) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.data = data;
        this.height = height;
        this.width = width;
        this.description = description;
        this.imageFull = imageFull;
    }

}