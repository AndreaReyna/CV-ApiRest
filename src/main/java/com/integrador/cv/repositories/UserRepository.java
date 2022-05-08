package com.integrador.cv.repositories;

import com.integrador.cv.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    boolean existsUserByUsername(String username);

}
