package com.example.demo.Entity;

import java.io.Serializable;

public class followPK implements Serializable {

    private Integer artistID;
    private Integer userID;

    public followPK(Integer artistID, Integer userID) {
        this.artistID = artistID;
        this.userID = userID;
    }

    public followPK() {
    }

    //  ***重写hashCode与equals方法***  划重点！
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((artistID == null) ? null : artistID.hashCode());
        result = PRIME * result + ((userID == null) ? null : userID.hashCode());
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

        final followPK other = (followPK)obj;
        if(artistID == null){
            if(other.artistID != null){
                return false;
            }
        }else if(!artistID.equals(other.artistID)){
            return false;
        }
        if(userID == null){
            if(other.userID != null){
                return false;
            }
        }else if(!userID.equals(other.userID)){
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

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}
