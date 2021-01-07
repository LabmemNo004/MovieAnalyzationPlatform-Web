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


    public JSONArray getCollectMovie(Integer id,Integer pagenum,Integer pagesize)
    {
        JSONArray b= new JSONArray();
        List<Object[]> temp=WatchlistRepository.getCollectMovie(id);
        if(temp.isEmpty())
        {
            JSONObject temp3=new JSONObject();
            temp3.put("total",-100);
            b.add(temp3);
            return b;
        }
        int total=temp.size();
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
        for(Object[] temp1:temp)
        {
            index++;
            if(index<=start) continue;
            if(index>end) break;
            JSONObject temp3=new JSONObject();

            String[] tag={"movie_name","movie_pic","movie_rate","movie_id"};
            for(int i=0;i< temp1.length;i++)
            {
                System.out.println(temp1[i]);
                temp3.put(tag[i],temp1[i]);
            }
            b.add(temp3);
        }
        JSONObject temp3=new JSONObject();
        temp3.put("total",total);
        b.add(temp3);
        return b;
    }


    public JSONArray getCollectPeople(Integer id,Integer pagenum,Integer pagesize)
    {
        JSONArray b= new JSONArray();

        List<Object[]> temp=FollowRepository.getCollectPeople(id);
        if(temp.isEmpty())
        {
            JSONObject temp3=new JSONObject();
            temp3.put("total",-100);
            b.add(temp3);
            return b;
        }
        int total=temp.size();
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
        for(Object[] temp1:temp)
        {
            index++;
            if(index<=start) continue;
            if(index>end) break;
            JSONObject temp3=new JSONObject();

            System.out.println(temp1);

            String[] tag={"person_name","profession","picture","personID"};
            for(int i=0;i< temp1.length;i++)
            {
                System.out.println(temp1[i]);
                temp3.put(tag[i],temp1[i]);
            }
            b.add(temp3);
        }
        JSONObject ss=new JSONObject();
        ss.put("total",total);
        b.add(ss);
        return b;
    }

}
