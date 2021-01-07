package com.example.demo.dao;

import com.example.demo.Entity.artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface artistRepository extends JpaRepository<artist,Integer> {


    @Override
    List<artist> findAll();

    @Override
    <S extends artist> S save(S s);

    @Query(value="select * from artist where profession=?1 ",nativeQuery = true)
    List<artist> getArtistID(String profession);


    @Query(value="with some as (" +
            "    select * from artist" +
            "    where id=?1)" +
            "select m.title,m.movieID " +
            "from some s join participate p on s.id = p.artistID " +
            "join movie m on m.movieID = p.movieID ",nativeQuery = true)
    List<Map<String,Integer>> getArtistMovie(Integer temp);


    @Query(value="with some as (" +
            "    select * from artist" +
            "    where id=?1)" +
            "select m.title " +
            "from some s join participate p on s.id = p.artistID " +
            "join movie m on m.movieID = p.movieID ",nativeQuery = true)
    List<String> getArtistMovie2(Integer temp);


    @Query(value="select * from artist where id=?1",nativeQuery = true)
    artist ShowArtistOwn(Integer id);

    @Modifying
    @Transactional
    @Query(value="update artist set collect_num=collect_num + 1 " +
            "where id=?1",nativeQuery = true)
    void AddCollectedUser(Integer artistID);

    @Modifying
    @Transactional
    @Query(value="update artist set collect_num=collect_num - 1 " +
            "where id=?1",nativeQuery = true)
    void DeleteCollectedUser(Integer artistID);


    @Modifying
    @Transactional
    @Query(value="update artist set picture=?2 where id=?1",nativeQuery = true)
    void UpdateAvator(Integer id,String filename);

}
