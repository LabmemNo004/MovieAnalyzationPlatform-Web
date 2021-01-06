package com.example.demo.service;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Entity.commentmsg;
import com.example.demo.Entity.movie;
import com.example.demo.Entity.numbers;
import com.example.demo.Entity.watchlist;
import com.example.demo.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
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

    public void movieRepository(String director,String actors,String type,
                                String area,String movie_pic,Date publish_time,
                                Integer duration,Float price)
    {

        numbers number=numberService.getSimpleNumbers();

        MovieRepository.UpdateAvator(number.getMovieNumadd() + 1, movie_pic,
                type, price, publish_time, movie_pic, duration);

        /**
         *
         */

//        ArtistRepository.InsertArtist();
        numberService.updataNumber(0,0,
                number.getMovieNumadd() + 1,0);


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
        List<movie> temp=MovieRepository.getTenMovie(start,end);
        List<movie> temp1=MovieRepository.getTenMovie(start1,end1);
        temp.addAll(temp1);
        JSONArray a=new JSONArray();
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

    public JSONArray getMovieLike(String movieName)
    {
        JSONArray a=new JSONArray();
        List<movie> temp=MovieRepository.getMovieLike(movieName);
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

    public JSONArray getMovieByType(String type,Integer order)
    {
        JSONArray a=new JSONArray();
        if(order==0)
        {
            List<movie> temp=MovieRepository.findByTypeOrderByScoreDesc(type);
            for(movie temp2:temp)
            {
                JSONObject b=new JSONObject();
                b.put("movie_id",temp2.getMovieID());
                b.put("movie_name",temp2.getTitle());
                b.put("movie_rate",temp2.getScore());
                b.put("movie_pic",temp2.getPhoto());
                a.add(b);
            }
        }
        return a;
    }

    public JSONArray getDetailMovie(Integer movieID,Integer userID)
    {
        JSONArray a=new JSONArray();

        movie tempMovie=MovieRepository.getDetailMovie(movieID);
        List<String> ActorName=
                ParticipateRepository.GetMoviesPerson(movieID,"Actor");
        List<String> DirectorName=
                ParticipateRepository.GetMoviesPerson(movieID,"Director");
        JSONObject b=new JSONObject();
        b.put("movie_name",tempMovie.getTitle());
        b.put("movie_pic",tempMovie.getPhoto());
        b.put("director",DirectorName);
        b.put("actors",ActorName);
        b.put("type",tempMovie.getType());
        b.put("area",tempMovie.getArea());
        b.put("pub_time",tempMovie.getPublishTime());
        b.put("duration",tempMovie.getPublishTime());
        b.put("introduction",tempMovie.getIntroduction());
        b.put("collect_num",tempMovie.getCollect_num());


        commentmsg temp9=
                CommentmsdRepository.findByMovieIDAndAndUserID(movieID, userID);
       // b.put("comment_num",tempMovie.getPublishTime());
        b.put("my_rate",temp9.getHelpfulness());
        b.put("my_comment",temp9.getText());

        watchlist temp10=WatchlistRepository.findByUserIDAndMovieID(userID,movieID);
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


}
