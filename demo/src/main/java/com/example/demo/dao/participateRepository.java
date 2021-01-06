package com.example.demo.dao;

import com.example.demo.Entity.follow;
import com.example.demo.Entity.followPK;
import com.example.demo.Entity.participate;
import com.example.demo.Entity.participatePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface participateRepository extends JpaRepository<participate, participatePK> {


    @Query(value = "select a.PersonName from participate " +
            "join artist a on a.id = participate.artistID " +
            "where movieID=?1 AND a.profession=?2",nativeQuery = true)
    List<String> GetMoviesPerson(Integer movieID,String type);


    @Override
    <S extends participate> S save(S s);
}
