package com.integrador.cv.repositories;

import com.integrador.cv.entities.Headline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HeadlineRepository extends JpaRepository<Headline, Integer> {

    @Query("SELECT t FROM Headline t WHERE t.title = :headline")
    Headline findByHeadline(@Param("headline") String headline);

    @Modifying
    @Query("UPDATE Headline t SET t.status = false WHERE t.id = :id")
    void delete(@Param("id") Integer id);

}
