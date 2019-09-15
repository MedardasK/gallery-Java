package com.gallery.gallery.DAO;


import com.gallery.gallery.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRep extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
