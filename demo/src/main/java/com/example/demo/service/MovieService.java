package com.example.demo.service;


import com.alibaba.fastjson.JSONArray;
import com.example.demo.Entity.numbers;
import com.example.demo.dao.artistRepository;
import com.example.demo.dao.movieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.sql.Date;

@Service
public class MovieService {

    @Resource
    private movieRepository MovieRepository;

    @Autowired
    private NumberService numberService;

    @Resource
    private artistRepository ArtistRepository;

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


        numberService.updataNumber(0,0,number.getMovieNumadd() + 1);


    }

}
