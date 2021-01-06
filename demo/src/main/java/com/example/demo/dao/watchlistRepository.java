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
            "select m.title,m.photo,m.score,m.movieID " +
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

    @Modifying
    @Transactional
    @Query(value="insert into watchlist values (?1,?2)",nativeQuery = true)
    void InsertNewWatchlist(Integer userId,Integer movieID);

    @Modifying
    @Transactional
    @Query(value="DELETE from watchlist " +
            "where userID=?1 AND movieID=?2",nativeQuery = true)
    void DeleteNewWatchlist(Integer userId,Integer movieID);

    watchlist findByUserIDAndMovieID(Integer UserID,Integer MovieID);




}
