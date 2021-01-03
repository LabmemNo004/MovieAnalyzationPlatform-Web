package com.example.demo.Entity;


import java.io.Serializable;

public class participatePK implements Serializable {

    private Integer artistID;
    private Integer movieID;

    public participatePK(Integer artistID, Integer movieID) {
        this.artistID = artistID;
        this.movieID = movieID;
    }

    public participatePK() {
    }

    //  ***重写hashCode与equals方法***  划重点！
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((artistID == null) ? null : artistID.hashCode());
        result = PRIME * result + ((movieID == null) ? null : movieID.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }

        final participatePK other = (participatePK)obj;
        if(artistID == null){
            if(other.artistID != null){
                return false;
            }
        }else if(!artistID.equals(other.artistID)){
            return false;
        }
        if(movieID == null){
            if(other.movieID != null){
                return false;
            }
        }else if(!movieID.equals(other.movieID)){
            return false;
        }
        return true;
    }

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
