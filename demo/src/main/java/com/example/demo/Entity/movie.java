package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "movie")
public class movie {
    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY) //配置主键的生成策略
    @Column(name="movieID") //指定和表中cust_id字段的映射关系
    private Integer movieID;

    @Column(name="title")
    private String title;

    @Column(name="productNum")
    private Integer productNum;

    @Column(name="directorNum")
    private Integer directorNum;

    @Column(name="actorNum")
    private Integer actorNum;

    @Column(name="score")
    private Float score;


    @Column(name="type")
    private String type;

    @Column(name="price")
    private Float price;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @Column(name="publishTime")
    private Date publishTime;

    @Column(name="photo")
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Float getPrice() {
        return price;
    }

    public Float getScore() {
        return score;
    }

    public Integer getActorNum() {
        return actorNum;
    }

    public Integer getDirectorNum() {
        return directorNum;
    }

    public Integer getMovieID() {
        return movieID;
    }

    public Integer getProductNum() {
        return productNum;
    }


    public Date getPublishTime() {
        return publishTime;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public void setActorNum(Integer actorNum) {
        this.actorNum = actorNum;
    }

    public void setDirectorNum(Integer directorNum) {
        this.directorNum = directorNum;
    }

    public void setMovieID(Integer movieID) {
        this.movieID = movieID;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

}
