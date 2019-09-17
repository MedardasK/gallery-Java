package com.gallery.gallery.DAO;


import com.gallery.gallery.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRep extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
