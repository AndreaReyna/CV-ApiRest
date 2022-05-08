package com.integrador.cv.repositories;

import com.integrador.cv.entities.Header;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeaderRepository extends JpaRepository<Header, Integer>{
    
}
