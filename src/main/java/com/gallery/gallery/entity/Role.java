package com.gallery.gallery.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "ROLE")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ROLE_ID",unique=true, nullable = false)
    private String id;

    @Column
    private String role;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;
}
