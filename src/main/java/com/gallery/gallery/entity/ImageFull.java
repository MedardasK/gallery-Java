package com.gallery.gallery.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Entity
@Table(name = "IMAGE_FULL")
public class ImageFull {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IMAGE_FULL_ID", updatable = false, nullable = false)
    private Long id;


    @Lob
    @Column(name = "DATA")
    private byte[] data;

    @OneToOne(mappedBy = "imageFull")
    private Image image;

    public Long getId() {
        return id;
    }

    public byte[] getData() {
        return data;
    }

    @JsonBackReference
    public Image getImage() {
        return image;
    }

    public ImageFull() {
    }

    public ImageFull(byte[] data) {
        this.data = data;
    }
}
