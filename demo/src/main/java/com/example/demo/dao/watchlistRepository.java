package com.example.demo.dao;

import com.example.demo.Entity.commentmsg;
import com.example.demo.Entity.watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
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

    /**
     * 计算被多少人收藏
     * @param movieID
     * @return
     */
    @Query(value="select * from watchlist where movieID=?1",nativeQuery = true)
    List<watchlist> getMovieByCollectedNum(Integer movieID);




}
