package com.integrador.cv.repositories;

import com.integrador.cv.entities.Content;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {

    @Modifying
    @Query("UPDATE Content c SET c.status = false WHERE c.id = :id")
    void delete(@Param("id") Integer id);

    @Query("SELECT c FROM Content c WHERE c.headline.title = :headline")
    List<Content> findByHeadline(@Param("headline") String headline);

}
