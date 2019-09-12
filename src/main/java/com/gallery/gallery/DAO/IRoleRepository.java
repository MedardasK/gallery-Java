package com.gallery.gallery.DAO;

import com.gallery.gallery.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
