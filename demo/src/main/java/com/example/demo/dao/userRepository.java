package com.example.demo.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Entity.users;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface userRepository extends JpaRepository<users, Integer>{

    List<users> findAll();

    @Query(value = "select userID,password from user where userID=?1",nativeQuery = true)
    Map<Integer,String> findByUserId(Integer id);

    @Query(value="select userID,role,profileName from user where profileName=?1 AND password=?2",nativeQuery = true)
    List<Object []> findVerifyLogin(String userName,String password);

    @Query(value = "select * from user where userID=?1",nativeQuery = true)
    users findByUserIdWeb(Integer id);

    @Modifying
    @Transactional
    @Query(value="update user set user.photo=?2 where user.userID=?1",nativeQuery = true)
    void UpdateAvator(Integer id,String filename);

    @Modifying
    @Transactional
    @Query(value="update user set profileName=?2, sex=?3" +
            " ,birthday=?4 ,phone=?5 ,email=?6 ,signature=?7" +
            " where user.userID=?1",nativeQuery = true)
    void UpdatePersonalInfor(Integer userid, String username, Integer sex,
                             Date birthday, String phone, String email, String signature);


    @Query(value="select * from user where userID=?1",
            nativeQuery = true)
    users queryNum(Integer id);

    @Query(value="select profileName from user;",nativeQuery = true)
    List<String> checkName();



    @Modifying
    @Transactional
    @Query(value="update user set commentNum=commentNum + 1 " +
            "where user.userID=?1",nativeQuery = true)
    void AddCommentNum(Integer id);

    @Modifying
    @Transactional
    @Query(value="update user set commentNum=commentNum - 1 " +
            "where user.userID=?1",nativeQuery = true)
    void DeleteCommentNum(Integer id);

    @Modifying
    @Transactional
    @Query(value="update user set peopleCollectNum=peopleCollectNum + 1 " +
            "where user.userID=?1",nativeQuery = true)
    void AddCollectPeople(Integer id);


    @Modifying
    @Transactional
    @Query(value="update user set peopleCollectNum=peopleCollectNum - 1 " +
            "where userID=?1",nativeQuery = true)
    void DeleteCollectPeople(Integer id);

    @Modifying
    @Transactional
    @Query(value="update user set movieCollectNum=movieCollectNum + 1 " +
            "where userID=?1",nativeQuery = true)
    void AddCollectMovie(Integer id);

    @Modifying
    @Transactional
    @Query(value="update user set movieCollectNum=movieCollectNum - 1 " +
            "where userID=?1",nativeQuery = true)
    void DeleteCollectMovie(Integer id);





}
