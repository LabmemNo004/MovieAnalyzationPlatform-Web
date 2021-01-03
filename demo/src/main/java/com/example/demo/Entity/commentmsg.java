package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "commentmsg")
public class commentmsg {
    @Id
    //@GeneratedValue(strategy= GenerationType.IDENTITY) //配置主键的生成策略
    @Column(name="msgID") //指定和表中cust_id字段的映射关系
    private Integer msgID;

    @Column(name="movieID")
    private Integer movieID;

    @Column(name="userID")
    private Integer userID;

    @Column(name= "rate")
    private Float helpfulness;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @Column(name="time")
    private Date time;

    @Column(name="summary")
    private String summary;

    @Column(name="text")
    private String text;

    public Integer getMsgID() {
        return msgID;
    }

    public void setMsgID(Integer msgID) {
        this.msgID = msgID;
    }

    public Integer getMovieID() {
        return movieID;
    }

    public void setMovieID(Integer movieID) {
        this.movieID = movieID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Float getHelpfulness() {
        return helpfulness;
    }

    public void setHelpfulness(Float helpfulness) {
        this.helpfulness = helpfulness;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
