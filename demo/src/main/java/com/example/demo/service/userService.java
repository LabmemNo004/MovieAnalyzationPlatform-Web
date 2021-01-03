package com.example.demo.service;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.Entity.users;
import com.example.demo.dao.userRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class userService {

    @Resource
    private userRepository UserRepository;

    public void saveUser(users u)
    {
        UserRepository.save(u);
    }

    public Map<Integer,String> findUserById(Integer id){
        return UserRepository.findByUserId(id);
    }

    public users findUserByIdWeb(Integer id){
        return UserRepository.findByUserIdWeb(id);
    }

    public void UpdateAvator(Integer id,String filename)
    {
        UserRepository.UpdateAvator(id, filename);
    }


    public Integer VerifyLogin(String phone,String password)
    {
        int temp=-1;
        temp=UserRepository.findVerifyLogin(phone,password);
        return temp;
    }

    public void UpdatePersonalInfor(Integer userid,String username,Integer sex,
                                    Date birthday,String phone,String email, String signature)
    {
        UserRepository.UpdatePersonalInfor(userid, username,
                sex, birthday, phone, email, signature);
    }

    public JSONObject queryNum(Integer id)
    {
        users temp=UserRepository.queryNum(id);
        JSONObject b=new JSONObject();
        b.put("评论电影数",temp.getCommentNum());
        b.put("收藏电影数",temp.getMovieCollectNum());
        b.put("收藏人物数",temp.getPeopleCollectNum());
        return b;
    }

}
