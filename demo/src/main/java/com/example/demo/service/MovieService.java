package com.example.demo.service;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Entity.commentmsg;
import com.example.demo.Entity.movie;
import com.example.demo.Entity.watchlist;
import com.example.demo.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class MovieService {

    @Resource
    private commentmsgRepository CommentmsdRepository;

    @Resource
    private movieRepository MovieRepository;

    @Autowired
    private NumberService numberService;

    @Resource
    private participateRepository ParticipateRepository;

    @Resource
    private watchlistRepository WatchlistRepository;

    @Resource
    private userRepository UserRepository;


    public void saveMovie(movie temp)
    {

        MovieRepository.save(temp);
    }


    public void AddCollectMovie(Integer userid,Integer movieId,Integer operations)
    {
        if(operations==1)
        {
            WatchlistRepository.InsertNewWatchlist(userid,movieId);
            UserRepository.AddCollectMovie(userid);
            MovieRepository.AddCollectedUser(movieId);
        }
        else
        {
            WatchlistRepository.DeleteNewWatchlist(userid,movieId);
            UserRepository.DeleteCollectMovie(userid);
            MovieRepository.DeleteCollectedUser(movieId);
        }
    }

    public JSONArray getTenMovie()throws ParseException
    {

        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date start=dateFormat1.parse("2019-01-01");
        Date end=dateFormat1.parse("2020-12-31");
        Date start1=dateFormat1.parse("2017-01-01");
        Date end1=dateFormat1.parse("2018-12-31");
        JSONArray a=new JSONArray();

        List<movie> temp=MovieRepository.getTenMovie(start,end);
        List<movie> temp1=MovieRepository.getTenMovie(start1,end1);
        temp.addAll(temp1);
        if(temp.isEmpty())
        {
            JSONObject temp3=new JSONObject();
            temp3.put("total",-100);
            a.add(temp3);
            return a;
        }




        for(movie temp2:temp)
        {
            JSONObject b=new JSONObject();
            b.put("movie_id",temp2.getMovieID());
            b.put("movie_name",temp2.getTitle());
            b.put("movie_rate",temp2.getScore());
            b.put("movie_pic",temp2.getPhoto());
            a.add(b);
        }
        return a;
    }

    public JSONArray getMovieLike(String movieName,Integer pagenum,Integer pagesize)
    {
        JSONArray a=new JSONArray();
        List<movie> temp=MovieRepository.getMovieLike(movieName);

        if(temp.isEmpty())
        {
            JSONObject temp3=new JSONObject();
            temp3.put("total",-100);
            a.add(temp3);
            return a;
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
        for(movie temp2:temp)
        {
            index++;
            if(index<=start) continue;
            if(index>end) break;
            JSONObject b=new JSONObject();
            b.put("movie_id",temp2.getMovieID());
            b.put("movie_name",temp2.getTitle());
            b.put("movie_rate",temp2.getScore());
            b.put("movie_pic",temp2.getPhoto());
            a.add(b);
        }
        JSONObject ss=new JSONObject();
        ss.put("total",total);
        a.add(ss);
        return a;
    }

    public JSONArray getMovieByType(String type,Integer order,Integer pagenum,Integer pagesize)
    {

        JSONArray a=new JSONArray();
        List<movie> temp=new ArrayList<>();
        if(order==0)
        {
            if(!type.equals("all"))
            {
                temp=MovieRepository.findByTypeOrderByScoreDesc(type);
            }
            else
            {
                temp=MovieRepository.findMoviesAllTypeScore();
            }
        }
        else if(order==1)
        {
            if(!type.equals("all"))
            {
                temp=MovieRepository.findByTypeOrderByCommentnumDesc(type);
            }
            else
            {
                temp=MovieRepository.findMoviesAllTypeComment();
            }
        }
        else if(order==2)
        {
            if(!type.equals("all"))
            {
                temp=MovieRepository.findByTypeOrderByCollectnumDesc(type);
            }
            else
            {
                temp=MovieRepository.findMoviesAllTypeCollect();
            }
        }

        if(temp.isEmpty())
        {
            JSONObject temp3=new JSONObject();
            temp3.put("total",-100);
            a.add(temp3);
            return a;
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
        for(movie temp2:temp)
        {
            index++;
            if(index<=start) continue;
            if(index>end) break;
            JSONObject b=new JSONObject();
            b.put("movie_id",temp2.getMovieID());
            b.put("movie_name",temp2.getTitle());
            b.put("movie_rate",temp2.getScore());
            b.put("movie_pic",temp2.getPhoto());
            a.add(b);

        }
        JSONObject ss=new JSONObject();
        ss.put("total",total);
        a.add(ss);
        return a;
    }

    public JSONArray getDetailMovie(Integer movieID,Integer userID)
    {
        JSONArray a=new JSONArray();

        movie tempMovie=MovieRepository.getDetailMovie(movieID);
        List<String> ActorName=
                ParticipateRepository.GetMoviesPerson(movieID,"演员");
        List<String> DirectorName=
                ParticipateRepository.GetMoviesPerson(movieID,"导演");
        String ttt=MovieRepository.getDirectorsMovie(movieID);
        DirectorName.add(ttt);
        JSONObject b=new JSONObject();
        b.put("movie_name",tempMovie.getTitle());
        b.put("movie_pic",tempMovie.getPhoto());
        b.put("director",DirectorName);
        b.put("actors",ActorName);
        b.put("type",tempMovie.getType());
        b.put("area",tempMovie.getArea());
        b.put("pub_time",tempMovie.getPublishTime());
        b.put("duration",tempMovie.getDuration());
        b.put("introduction",tempMovie.getIntroduction());
        b.put("collect_num",tempMovie.getCollectnum());
        b.put("comment_num",tempMovie.getCommentnum());

        commentmsg temp9=
                CommentmsdRepository.findByMovieIDAndAndUserID(movieID, userID);

        watchlist temp10=
                WatchlistRepository.findByUserIDAndMovieID(userID,movieID);
        if(temp9!=null)
        {
            b.put("my_rate",temp9.getHelpfulness());
            b.put("my_comment",temp9.getText());
        }
        else
        {
            b.put("my_rate",-100);
            b.put("my_comment","您暂未对该电影有任何评论");
        }
        if(temp10==null)
        {
            b.put("is_collect",false);
        }
        else
        {
            b.put("is_collect",true);
        }
        a.add(b);



        return a;
    }


    public JSONArray getMovieComment(Integer movieID,Integer pagenum,Integer pagesize)
    {
        //1
        JSONArray a=new JSONArray();

        List<Object []> temp=
                CommentmsdRepository.getOtherCommentMovie(movieID);

        if(temp.isEmpty())
        {
            JSONObject b=new JSONObject();
            b.put("total",-100);
            a.add(b);
            return a;
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

        for(Object [] temp1:temp)
        {
            index++;
            if(index<=start) continue;
            if(index>end) break;
            JSONObject b=new JSONObject();
            String []tag={  "username", "avatar","rate", "content", "time"};
            for(int i=0;i<temp1.length;++i)
            {
                b.put(tag[i],temp1[i]);
            }
            a.add(b);
        }
        JSONObject ss=new JSONObject();
        ss.put("total",total);
        a.add(ss);
        return a;
    }

    public void UploadAvatar(Integer movieID,String filename)
    {
        MovieRepository.UpdateAvator(movieID, filename);
    }

}
