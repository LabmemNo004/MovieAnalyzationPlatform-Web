package com.example.demo.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.commentmsgRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentService {

    @Resource
    commentmsgRepository commentRepository;

    public JSONArray getCommentMovie(Integer id)
    {
        JSONArray b= new JSONArray();
        List<Object[]> temp=commentRepository.getCommentMovie(id);
        for(Object[] temp1:temp)
        {
            JSONObject temp3=new JSONObject();
            String[] tag={"movie_name","movie_pic","rate","time","content"};
            for(int i=0;i< temp1.length;i++)
            {
                System.out.println(temp1[i]);
                temp3.put(tag[i],temp1[i]);
            }
            b.add(temp3);
        }
        JSONObject temp3=new JSONObject();
        temp3.put("total",b.size());
        b.add(temp3);
        return b;
    }
}
