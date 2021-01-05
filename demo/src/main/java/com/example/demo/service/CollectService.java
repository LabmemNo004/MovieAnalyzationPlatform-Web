package com.example.demo.service;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.followRepository;
import com.example.demo.dao.watchlistRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CollectService {


    @Resource
    watchlistRepository WatchlistRepository;

    @Resource
    followRepository FollowRepository;


    public JSONArray getCollectMovie(Integer id)
    {
        JSONArray b= new JSONArray();
        List<Object[]> temp=WatchlistRepository.getCollectMovie(id);
        for(Object[] temp1:temp)
        {
            JSONObject temp3=new JSONObject();

            System.out.println(temp1);

            String[] tag={"title","photo","score"};
            for(int i=0;i< temp1.length;i++)
            {
                System.out.println(temp1[i]);
                temp3.put(tag[i],temp1[i]);
            }
            b.add(temp3);

        }
        return b;
    }


    public JSONArray getCollectPeople(Integer id)
    {
        JSONArray b= new JSONArray();

        List<Object[]> temp=FollowRepository.getCollectPeople(id);
        for(Object[] temp1:temp)
        {
            JSONObject temp3=new JSONObject();

            System.out.println(temp1);

            String[] tag={"PersonName","profession","picture"};
            for(int i=0;i< temp1.length;i++)
            {
                System.out.println(temp1[i]);
                temp3.put(tag[i],temp1[i]);
            }
            b.add(temp3);
        }
        return b;
    }

}
