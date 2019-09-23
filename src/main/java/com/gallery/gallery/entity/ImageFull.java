package com.gallery.gallery.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "IMAGE_FULL")
public class ImageFull {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IMAGE_FULL_ID", updatable = false, nullable = false)
    private Long id;

    @JsonBackReference
    @Lob
    @Column(name = "DATA")
    private byte[] data;

}
