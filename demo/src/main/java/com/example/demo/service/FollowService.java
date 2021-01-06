package com.example.demo.service;

import com.example.demo.Entity.participate;
import com.example.demo.dao.participateRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FollowService {

    @Resource

    private participateRepository ParticipateRepository;

    public void SaveMovieAndArtist(participate temp)
    {
        ParticipateRepository.save(temp);
    }
}
