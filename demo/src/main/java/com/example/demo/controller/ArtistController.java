package com.example.demo.controller;


import com.alibaba.fastjson.JSONArray;
import com.example.demo.JSON.JsonResult;
import com.example.demo.service.ArtistService;
import com.example.demo.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value="/Artist",produces = "application/json;charset=UTF-8")
public class ArtistController {

    @Autowired
    private ArtistService artistService;


    @GetMapping(value = "/ArtistList")
    @ApiOperation(value = "展示演员或者导演列表",
            notes = "传递token和id确认身份.身份未确定，返回登陆指示." +
                    "否则无法显示用户的关注信息")
    public JsonResult ArtistList(
            @RequestParam("profession") String profession,
            @RequestParam("pagenum") Integer pagenum,
            @RequestParam("pagesize") Integer pagesize

    )
    {
        JSONArray temp=new JSONArray();
        if(profession.equals("Actor"))
        {
            temp=artistService.getArtistID('A',pagenum,pagesize);
        }
        else if(profession.equals("Director"))
        {
            temp=artistService.getArtistID('D',pagenum,pagesize);
        }
        else if(profession.equals("all"))
        {
            temp=artistService.getArtistID('A',pagenum,pagesize);
            temp.addAll(artistService.getArtistID('D',pagenum,pagesize));
        }
        int i=temp.size()-1;
        Object counts=temp.getJSONObject(i).get("total");
        temp.fluentRemove(i);
        return new JsonResult(temp,"成功",counts);
    }



    @GetMapping(value = "/ArtistOwnPage")
    @ApiOperation(value = "展示演员或者导演个人所有信息",
            notes = "传递token和id确认身份.身份未确定，返回登陆指示." +
                    "否则无法显示用户的关注信息")
    public JsonResult ArtistOwnPage(
            @RequestParam("person_id") Integer person_id,
            @RequestParam("user_id") Integer user_id
    )
    {
        JSONArray temp=artistService.ShowArtistOwn(user_id,person_id);
        return new JsonResult(temp,
                "成功");
    }


    @PostMapping(value = "/Follow")
    @ApiOperation(value = "用户收藏或者取消收藏导演或演员",
            notes = "传递token和id确认身份。身份未确定，返回登陆指示,op为1表示收藏，0为取消")
    public JsonResult Follow(
            @RequestParam(value="token",required = false) String token,
            @RequestParam("userid") Integer userid,
            @RequestParam("artistID") Integer artistID,
            @RequestParam("operations") Integer operations
    )
    {
        int temp1=0;
        artistService.AddCollectPeople(userid,artistID,operations);
        if(operations==1)
        {
            return new JsonResult(temp1,
                    "成功关注这个人物");
        }
        else {
            return new JsonResult(temp1,
                    "取消关注这个人物");
        }
    }

}
