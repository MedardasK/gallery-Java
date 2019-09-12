package com.gallery.gallery.DAO;


import com.gallery.gallery.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {

    List<User> findAll();
    Optional<User> findById(Long id);
    User findByUsername(String userName);

}
