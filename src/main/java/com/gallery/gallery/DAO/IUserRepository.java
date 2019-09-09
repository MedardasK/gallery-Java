package com.gallery.gallery.DAO;


import com.gallery.gallery.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String userName);
}
