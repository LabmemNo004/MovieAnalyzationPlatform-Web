package com.example.demo.service;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.Entity.users;
import com.example.demo.dao.userRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@Service("UserService")
public class UserService {

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


    public List<Object []> VerifyLogin(String userName,String password)
    {
        List<Object []> temps=
                UserRepository.findVerifyLogin(userName,password);
        return temps;
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
        b.put("username",temp.getProfileName());
        b.put("avatar",temp.getPhoto());
        b.put("movieCollectNum",temp.getMovieCollectNum());
        b.put("peopleCollectNum",temp.getPeopleCollectNum());
        return b;
    }

    public int checkName(String name)
    {
        int flag=0;
        List<String> temp=UserRepository.checkName();
        if(temp.contains(name))
        {
            flag=1;
        }
        return flag;
    }


    public void AddCollectPeople(Integer id)
    {
        UserRepository.AddCollectPeople(id);
    }

}
