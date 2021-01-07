package com.example.demo.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Entity.commentmsg;
import com.example.demo.Entity.movie;
import com.example.demo.Entity.numbers;
import com.example.demo.dao.commentmsgRepository;
import com.example.demo.dao.movieRepository;
import com.example.demo.dao.userRepository;
import org.springframework.stereotype.Service;
import com.example.demo.Entity.users;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

@Service
public class CommentService {

    @Resource
    private commentmsgRepository CommentRepository;

    @Resource
    private userRepository UserRepository;

    @Resource
    private movieRepository MovieRepository;

    @Resource
    private NumberService numberService;

    public JSONArray getCommentMovie(Integer id)
    {
        JSONArray b= new JSONArray();
        List<Object[]> temp=CommentRepository.getCommentMovie(id);
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

    public commentmsg QueryExistMovie(Integer movieID,Integer userID)
    {
        return CommentRepository.findByMovieIDAndAndUserID(movieID, userID);
    }


    public void saveCommentMsg(Integer commentID,Integer user_id,Integer movie_id,
        Float rate,Date time,String Content)
    {
        commentmsg dd=new commentmsg();
        dd.setMsgID(commentID);
        dd.setUserID(user_id);
        dd.setMovieID(movie_id);
        dd.setHelpfulness(rate);
        dd.setTime(time);
        dd.setText(Content);
        CommentRepository.save(dd);

        UserRepository.AddCommentNum(user_id);

        MovieRepository.AddCommentNum(movie_id);
    }


    public void UpdateScore(Integer movieID,Integer userID,Float Score)
    {
        CommentRepository.UpdateScore(movieID, userID, Score);
    }

    public void UpdateComment(Integer movieID,Integer userID,String content)
    {
        CommentRepository.UpdateComment(movieID, userID, content);
    }

}
