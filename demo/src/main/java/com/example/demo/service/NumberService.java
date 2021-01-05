package com.example.demo.service;

import com.example.demo.Entity.numbers;
import com.example.demo.dao.numberRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NumberService {

    @Resource
    private numberRepository numberReposiroty;

    public void updataNumber(Integer userID, Integer ArtistID,Integer MovieID)
    {
        if(ArtistID==0&MovieID==0)
        {
            numberReposiroty.updateNumberById(userID);
        }
        if(userID==0&&MovieID==0)
        {
            numberReposiroty.updateNumberById1(ArtistID);
        }
        if(userID==0&ArtistID==0)
        {
            numberReposiroty.updateNumberById2(MovieID);
        }
    }

    public numbers getSimpleNumbers()
    {
        return numberReposiroty.getSimpleNumbers();
    }

}
