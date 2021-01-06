package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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


    @Column(name="score")
    private Float score;

    @Column(name="area")
    private String area;


    @Column(name="type")
    private String type;


    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name="publishTime")
    private Date publishTime;

    @Column(name="photo")
    private String photo;

    @Column(name="duration")
    private Integer duration;

    @Column(name="collect_num")
    private Integer collect_num;

    @Column(name="introduction")
    private String introduction;

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getCollect_num() {
        return collect_num;
    }

    public void setCollect_num(Integer collect_num) {
        this.collect_num = collect_num;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    public Float getScore() {
        return score;
    }


    public Integer getMovieID() {
        return movieID;
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


    public void setMovieID(Integer movieID) {
        this.movieID = movieID;
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
