package com.example.demo.Entity;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@Table(name = "participate")
@IdClass(participatePK.class)
@Proxy(lazy = false)
//取消懒加载避免奇怪bug
public class participate implements Serializable {

    @Id
    @Column(name="artistID") //指定和表中cust_id字段的映射关系
    private Integer artistID;

    @Id
    @Column(name="movieID")
    private Integer movieID;

    public Integer getArtistID() {
        return artistID;
    }

    public void setArtistID(Integer artistID) {
        this.artistID = artistID;
    }

    public Integer getMovieID() {
        return movieID;
    }

    public void setMovieID(Integer movieID) {
        this.movieID = movieID;
    }
}
