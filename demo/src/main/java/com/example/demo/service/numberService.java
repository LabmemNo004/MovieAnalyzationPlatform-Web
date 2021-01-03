package com.example.demo.service;

import com.example.demo.Entity.numbers;
import com.example.demo.dao.numberRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class numberService {

    @Resource
    private numberRepository numberReposiroty;

    public void updataNumber(Integer userID, Integer ArtistID,Integer MovieID)
    {
        if(ArtistID==0&MovieID==0)
        {
            numberReposiroty.updateNumberById(userID);
        }
    }

    public numbers getSimpleNumbers()
    {
        return numberReposiroty.getSimpleNumbers();
    }
}
