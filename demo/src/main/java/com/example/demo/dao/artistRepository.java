package com.example.demo.dao;

import com.example.demo.Entity.artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

public interface artistRepository extends JpaRepository<artist,Integer> {


    @Modifying
    @Transactional
    @Query(value="insert into artist values (" +
            " ?1,?2,?3,?4,?5,?6,?7,?8,?9)",nativeQuery = true)
    void InsertArtist(Integer id, String name,
                     Integer sex, Date birthday,String area,
                     String profession,String intoduction,
                     Character type,String photo);



    @Query(value="select * from artist where type=?1 ",nativeQuery = true)
    List<artist> getArtistID(Character type);


    @Query(value="with some as (" +
            "    select * from artist" +
            "    where id=?1)" +
            "select m.title " +
            "from some s join participate p on s.id = p.artistID " +
            "join movie m on m.movieID = p.movieID ",nativeQuery = true)
    List<String> getArtistMovie(Integer temp);


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

}
