package com.example.demo.Entity;


import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@Table(name = "follow")
@IdClass(followPK.class)
@Proxy(lazy = false)
//取消懒加载避免奇怪bug
public class follow implements Serializable {

    @Id
    @Column(name="artistID") //指定和表中cust_id字段的映射关系
    private Integer artistID;

    @Id
    @Column(name="userID")
    private Integer userID;



    public Integer getArtistID() {
        return artistID;
    }

    public void setArtistID(Integer artistID) {
        this.artistID = artistID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}
