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
import java.util.Map;

@Service
public class ArtistService {

    @Resource
    private artistRepository ArtistRepository;

    @Resource
    private followRepository FollowRepository;

    @Resource
    private userRepository UserRepository;

    public JSONArray getArtistID(String profession,Integer pagenum,Integer pagesize)
    {
        JSONArray temp=new JSONArray();
        List<artist> some=new ArrayList<>();
        if(profession.equals("all"))
        {
            some=ArtistRepository.findAll();
        }
        else
        {
            some=ArtistRepository.getArtistID(profession);
        }
        int total=some.size();
        int start=0;
        int end=0;
        if(total>(pagenum - 1)*pagesize)
        {
            start=(pagenum - 1)*pagesize;
            if(total>pagenum*pagesize)
            {
                end=pagenum*pagesize;
            }
            else
            {
                end=total;
            }
        }
        else
        {
            return null;
        }
        /**
         * 自index开始到total/或者本页结尾
         */
        int index=0;
        for(artist shabi:some)
        {
            index++;
            if(index<=start) continue;
            if(index>end) break;
            JSONObject temp3=new JSONObject();

            List<String> temp1=ArtistRepository.getArtistMovie2(shabi.getId());

            temp3.put("person_id",shabi.getId());
            temp3.put("person_name",shabi.getPersonName());
            temp3.put("profession",shabi.getProfession());
            temp3.put("movies",temp1);
            temp3.put("person_pic",shabi.getPicture());
            temp.add(temp3);
        }
        JSONObject ss=new JSONObject();
        ss.put("total",total);
        temp.add(ss);
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

        List<Map<String,Integer>> temp2=ArtistRepository.getArtistMovie(temp1.getId());

        JSONArray nanshou=new JSONArray();

        for(Map<String,Integer> temp9:temp2)
        {
            JSONObject tempMovie=new JSONObject();
            tempMovie.put("movieName",temp9.get("title"));
            tempMovie.put("movieID",temp9.get("movieID"));
            nanshou.add(tempMovie);
        }


        b.put("movies",nanshou);
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

    public Integer AddCollectPeople(Integer userID,Integer artistID,Integer operation)
    {
        if(operation==1)
        {
            FollowRepository.InsertNewFollow(userID,artistID);
            UserRepository.AddCollectPeople(userID);
            ArtistRepository.AddCollectedUser(artistID);
        }
        else
        {
            follow temp=FollowRepository.findByUserIDAndArtistID(userID,artistID);
            if(temp==null)
            {
                return 1;
            }
            FollowRepository.DeleteNewFollow(userID,artistID);
            UserRepository.DeleteCollectPeople(userID);
            ArtistRepository.DeleteCollectedUser(artistID);
        }
        return 0;
    }


    public void saveArtist(artist temp)
    {

        ArtistRepository.save(temp);
    }

    public void UploadAvatar(Integer personID,String filename)
    {

        ArtistRepository.UpdateAvator(personID, filename);
    }

}
