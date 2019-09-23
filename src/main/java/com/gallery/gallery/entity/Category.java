package com.gallery.gallery.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Entity
@Table(name = "CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CATEGORY_ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    @Fetch(value= FetchMode.SELECT)
    private Set<Image> image = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @JsonBackReference
    public Set<Image> getImage() {
        return image;
    }

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }
}
