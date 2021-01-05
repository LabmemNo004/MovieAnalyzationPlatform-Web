package com.example.demo.dao;

import com.example.demo.Entity.movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.sql.Date;

public interface movieRepository extends JpaRepository<movie, Integer> {

    @Modifying
    @Transactional
    @Query(value="insert into movie values " +
            "(?1,?2,0.0,?3,?4,?5,?6,?7) ",nativeQuery = true)
    void UpdateAvator(Integer id, String title, String type, Float price,
                      Date publish, String photo,Integer duration);

}
