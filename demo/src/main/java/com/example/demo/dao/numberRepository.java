package com.example.demo.dao;

import com.example.demo.Entity.numbers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface numberRepository extends JpaRepository<numbers, Integer> {

    @Query(value = "select * from numbers where N=1",nativeQuery = true)
    numbers getSimpleNumbers();


    @Modifying
    @Transactional
    @Query("update numbers as c set c.userNumadd=?1 " +
            "where c.N=1")
    int updateNumberById(Integer userID);

}
