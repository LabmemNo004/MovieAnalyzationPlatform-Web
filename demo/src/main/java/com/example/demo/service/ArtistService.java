package com.example.demo.service;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Entity.artist;
import com.example.demo.Entity.follow;
import com.example.demo.dao.artistRepository;
import com.example.demo.dao.followRepository;
import com.example.demo.dao.userRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistService {

    @Resource
    private artistRepository ArtistRepository;

    @Resource
    private followRepository FollowRepository;

    @Resource
    private userRepository UserRepository;

    public JSONArray getArtistID(Character type)
    {
        JSONArray temp=new JSONArray();
        List<artist> some=ArtistRepository.getArtistID(type);

        for(artist shabi:some)
        {
            JSONObject temp3=new JSONObject();

            List<String> temp1=ArtistRepository.getArtistMovie(shabi.getId());

            temp3.put("person_id",shabi.getId());
            temp3.put("person_name",shabi.getPersonName());
            temp3.put("profession",shabi.getProfession());
            temp3.put("movies",temp1);
            temp3.put("person_pic",shabi.getPicture());
            temp.add(temp3);
        }
        return temp;


    }

    public JSONArray ShowArtistOwn(Integer userid,Integer artistid)
    {
        JSONArray temp=new JSONArray();
        artist temp1=ArtistRepository.ShowArtistOwn(artistid);
        JSONObject b=new JSONObject();
        b.put("person_name",temp1.getPersonName());
        b.put("person_pic",temp1.getPicture());
        b.put("gender",temp1.getSex());
        b.put("birthday",temp1.getBirthday());
        b.put("area",temp1.getArea());
        b.put("profession",temp1.getProfession());
        List<String> temp2=ArtistRepository.getArtistMovie(temp1.getId());

        b.put("movies",temp2);
        b.put("introduction",temp1.getIntroduction());
        b.put("collect_num",temp1.getCollect_num());

        List<follow> temp3=FollowRepository.findIfCollect(userid, artistid);
        if(temp3.isEmpty())
        {
            b.put("is_collect",false);
        }
        else
        {
            b.put("is_collect",true);
        }
        temp.add(b);
        return temp;
    }

    public void AddCollectPeople(Integer userID,Integer artistID,Integer operation)
    {
        if(operation==1)
        {
            FollowRepository.InsertNewFollow(userID,artistID);
            UserRepository.AddCollectPeople(userID);
            ArtistRepository.AddCollectedUser(artistID);
        }
        else
        {
            FollowRepository.DeleteNewFollow(userID,artistID);
            UserRepository.DeleteCollectPeople(userID);
            ArtistRepository.DeleteCollectedUser(artistID);
        }
    }

}
