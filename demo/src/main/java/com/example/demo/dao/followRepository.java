package com.example.demo.dao;

import com.example.demo.Entity.follow;
import com.example.demo.Entity.followPK;
import com.example.demo.Entity.watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface followRepository extends JpaRepository<follow, followPK> {

    @Query(value="with my as (" +
            "    select userID from user" +
            "    where userID=?1" +
            ")" +
            "select a.PersonName,a.profession,a.picture,a.id " +
            "from my join follow on my.userID=follow.userID " +
            "join artist a on a.id = follow.artistID order by a.id ASC ",nativeQuery = true)
    List<Object []> getCollectPeople(Integer userid);

    @Query(value="select * from follow where userID=?1 " +
            "AND artistID=?2",nativeQuery = true)
    List<follow> findIfCollect(Integer useID, Integer artistID);

    @Modifying
    @Transactional
    @Query(value="insert into follow values (?2,?1)",nativeQuery = true)
    void InsertNewFollow(Integer userId,Integer artistID);

    @Modifying
    @Transactional
    @Query(value="DELETE from follow " +
            "where userID=?1 AND artistID=?2",nativeQuery = true)
    void DeleteNewFollow(Integer userId,Integer artistID);

}
