package com.example.demo.dao;

import com.example.demo.Entity.movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface movieRepository extends JpaRepository<movie, Integer> {

    @Override
    <S extends movie> S save(S s);

    @Modifying
    @Transactional
    @Query(value="update movie set photo=?2 where movieID=?1",nativeQuery = true)
    void UpdateAvator(Integer id,String filename);

    @Modifying
    @Transactional
    @Query(value="update movie set commentNum=commentNum + 1 " +
            "where movieID=?1",nativeQuery = true)
    void AddCommentNum(Integer id);

    @Modifying
    @Transactional
    @Query(value="update movie set commentNum=commentNum - 1 " +
            "where movieID=?1",nativeQuery = true)
    void DeleteCommentNum(Integer id);


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

    List<movie> findByTypeOrderByCollectnumDesc(String type);

    List<movie> findByTypeOrderByCommentnumDesc(String type);

    @Query(value="select * from movie ORDER BY score DESC ",nativeQuery = true)
    List<movie> findMoviesAllTypeScore();

    @Query(value="select * from movie ORDER BY collect_num DESC ",nativeQuery = true)
    List<movie> findMoviesAllTypeCollect();

    @Query(value="select * from movie ORDER BY commentnum DESC ",nativeQuery = true)
    List<movie> findMoviesAllTypeComment();


    @Query(value="select * from movie where movieID=?1",nativeQuery = true)
    movie getDetailMovie(Integer movieID);

    @Query(value="select * from movie where movieID=?1",nativeQuery = true)
    movie getOneMovie(Integer movieID);

    @Query(value="select Director from movie where movieID=?1")
    String getDirectorsMovie(Integer movieID);

}
