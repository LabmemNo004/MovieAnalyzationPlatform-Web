package com.example.demo.Entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "numbers")
public class numbers {

    @Id
    @Column(name="N")
    private Integer N;

    @Column(name="userNumadd")
    private Integer userNumadd;

    @Column(name="artistNumadd")
    private Integer artistNumadd;

    @Column(name="movieNumadd")
    private Integer movieNumadd;

    public Integer getN() {
        return N;
    }

    public void setN(Integer n) {
        N = n;
    }

    public Integer getUserNumadd() {
        return userNumadd;
    }

    public void setUserNumadd(Integer userNumadd) {
        this.userNumadd = userNumadd;
    }

    public Integer getArtistNumadd() {
        return artistNumadd;
    }

    public void setArtistNumadd(Integer artistNumadd) {
        this.artistNumadd = artistNumadd;
    }

    public Integer getMovieNumadd() {
        return movieNumadd;
    }

    public void setMovieNumadd(Integer movieNumadd) {
        this.movieNumadd = movieNumadd;
    }
}
