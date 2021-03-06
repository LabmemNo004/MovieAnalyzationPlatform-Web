package com.example.demo.service;

import com.example.demo.Entity.numbers;
import com.example.demo.dao.numberRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NumberService {

    @Resource
    private numberRepository numberReposiroty;

    public void updataNumber(Integer userID, Integer ArtistID,
                             Integer MovieID,Integer CommentMsg)
    {
        if(CommentMsg!=0)
        {
            numberReposiroty.updateNumberById3(CommentMsg);
            return;
        }

        if(ArtistID==0&MovieID==0)
        {
            numberReposiroty.updateNumberById(userID);
            return;
        }
        if(userID==0&&MovieID==0)
        {
            numberReposiroty.updateNumberById2(ArtistID);
            return;
        }
        if(userID==0&ArtistID==0)
        {
            numberReposiroty.updateNumberById1(MovieID);
            return;
        }
    }

    public numbers getSimpleNumbers()
    {

        return numberReposiroty.getSimpleNumbers();
    }

}
