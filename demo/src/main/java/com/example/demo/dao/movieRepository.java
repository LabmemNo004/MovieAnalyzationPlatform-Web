package com.example.demo.dao;

import com.example.demo.Entity.movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface movieRepository extends JpaRepository<movie, Integer> {

    @Modifying
    @Transactional
    @Query(value="insert into movie values " +
            "(?1,?2,0.0,?3,?4,?5,?6,?7) ",nativeQuery = true)
    void UpdateAvator(Integer id, String title, String type, Float price,
                      Date publish, String photo,Integer duration);

    @Modifying
    @Transactional
    @Query(value="update movie set collect_num=collect_num + 1 " +
            "where movieID=?1",nativeQuery = true)
    void AddCollectedUser(Integer movieID);


    @Modifying
    @Transactional
    @Query(value="update movie set collect_num=collect_num - 1 " +
            "where movieID=?1",nativeQuery = true)
    void DeleteCollectedUser(Integer movieID);

    @Query(value="select * from movie " +
            " where publishTime>?1 AND publishTime<?2" +
            " ORDER BY score DESC limit 10",nativeQuery = true)
    List<movie> getTenMovie(Date start,Date end);

    @Query(value="select * from movie " +
            " where title like concat('%',?1,'%')",nativeQuery = true)
    List<movie> getMovieLike(String movieName);

    List<movie> findByTypeOrderByScoreDesc(String type);



    @Query(value="select * from movie where movieID=?1",nativeQuery = true)
    movie getDetailMovie(Integer movieID);



}
