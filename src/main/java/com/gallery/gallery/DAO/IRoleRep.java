package com.gallery.gallery.DAO;

import com.gallery.gallery.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRep extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
