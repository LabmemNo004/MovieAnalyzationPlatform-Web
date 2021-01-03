package com.example.demo.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "artist")
public class artist {
    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY) //配置主键的生成策略
    @Column(name="id") //指定和表中cust_id字段的映射关系
    private Integer id;

    @Column(name="sex")
    private Integer sex;

    @Column(name="PersonName")
    private String PersonName;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @Column(name="birthday")
    private Date birthday;

    @Column(name="area")
    private String area;

    @Column(name="profession")
    private String profession;

    @Column(name="introduction")
    private String introduction;

    @Column(name="type")
    private Character type;

    @Column(name="picture")
    private String picture;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPersonName() {
        return PersonName;
    }

    public void setPersonName(String personName) {
        PersonName = personName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Character getType() {
        return type;
    }

    public void setType(Character type) {
        this.type = type;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}
