package com.example.demo.dao;

import com.example.demo.Entity.follow;
import com.example.demo.Entity.followPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface followRepository extends JpaRepository<follow, followPK> {

    @Query(value="with my as (" +
            "    select userID from user" +
            "    where userID=?1" +
            ")" +
            "select a.PersonName,a.profession,a.picture " +
            "from my join follow on my.userID=follow.userID " +
            "join artist a on a.id = follow.artistID",nativeQuery = true)
    List<Object []> getCollectPeople(Integer userid);

}
