package com.example.demo.dao;

import com.example.demo.Entity.commentmsg;
import com.example.demo.Entity.movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface commentmsgRepository extends JpaRepository<commentmsg,Integer> {


    @Query(value="with my as (" +
            "    select userID from user" +
            "    where userID=?1" +
            ")" +
            "select m.title,m.photo,commentmsg.rate,commentmsg.time,commentmsg.text " +
            "from my join commentmsg on my.userID=commentmsg.userID " +
            "join movie m on m.movieID = commentmsg.movieID " +
            "ORDER BY m.score DESC ",nativeQuery = true)
    List<Object[]> getCommentMovie(Integer id);

}
