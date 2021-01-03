package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class watchlistPK implements Serializable {

    private Integer userID;
    private Integer movieID;

    public watchlistPK(int userID, int movieID) {
        this.userID = userID;
        this.movieID = movieID;
    }

    public watchlistPK() {
    }

    //  ***重写hashCode与equals方法***  划重点！
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((userID == null) ? null : userID.hashCode());
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

        final watchlistPK other = (watchlistPK)obj;
        if(userID == null){
            if(other.userID != null){
                return false;
            }
        }else if(!userID.equals(other.userID)){
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }
}
