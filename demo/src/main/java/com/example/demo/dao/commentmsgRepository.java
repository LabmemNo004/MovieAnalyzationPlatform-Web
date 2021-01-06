package com.example.demo.dao;

import com.example.demo.Entity.commentmsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.sql.Date;
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

    commentmsg findByMovieIDAndAndUserID(Integer movieID,Integer userID);




    @Modifying
    @Transactional
    @Query(value="update commentmsg set rate=?3" +
            " where userID=?2 AND movieID=?1 ",nativeQuery = true)
    void UpdateScore(Integer movieID,Integer userID,Float score);



    @Modifying
    @Transactional
    @Query(value="update commentmsg set text=?3" +
            " where userID=?2 AND movieID=?1 ",nativeQuery = true)
    void UpdateComment(Integer movieID,Integer userID,String content);


    @Modifying
    @Transactional
    @Query(value="insert into commentmsg " +
            "values (?1,?2,?3,?4,?5,?6)",nativeQuery = true)
    void InsertaComment(Integer commentID,Integer user_id,Integer movie_id,
                        Float rate,Date time,String Content);

}
