package com.example.demo.dao;

import com.example.demo.Entity.commentmsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface watchlistRepository extends JpaRepository<commentmsg,Integer> {

    @Query(value="with my as (" +
            "    select userID from user" +
            "    where userID=?1" +
            ")" +
            "select m.title,m.photo,m.score " +
            "from my join watchlist on my.userID=watchlist.userID " +
            "join movie m on m.movieID = watchlist.movieID " +
            "ORDER BY m.score DESC ",nativeQuery = true)
    List<Object[]> getCollectMovie(Integer id);

}
