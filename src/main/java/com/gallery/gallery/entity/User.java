package com.gallery.gallery.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;


@Data
@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "USER_ID",unique=true, nullable = false)
    private Long id;

    @Column
    private String username;

    @Column
    @JsonIgnore
    private String password;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID", nullable=false)
    private Role roles;

}