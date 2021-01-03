package com.example.demo.controller;


import com.example.demo.JSON.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value="/Artist",produces = "application/json;charset=UTF-8")
public class ArtistController {

    @GetMapping(value = "/ArtistList")
    @ApiOperation(value = "展示演员或者导演列表",
            notes = "传递token和id确认身份.身份未确定，返回登陆指示." +
                    "否则无法显示用户的关注信息")
    public JsonResult ArtistList(
            @RequestParam("token") String token,
            @RequestParam("userid") Integer userid
    )
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        int temp1=0;
        myWatch.stop();
        return new JsonResult(temp1,
                "成功",myWatch.getLastTaskTimeMillis());
    }

    @PostMapping(value = "/Follow")
    @ApiOperation(value = "用户收藏或者取消收藏导演或演员",
            notes = "传递token和id确认身份。身份未确定，返回登陆指示,op为1表示点赞，0为取消")
    public JsonResult Follow(
            @RequestParam("token") String token,
            @RequestParam("userid") Integer userid,
            @RequestParam("movieId") Integer movieId,
            @RequestParam("operations") Integer operations
    )
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        int temp1=0;
        myWatch.stop();
        return new JsonResult(temp1,
                "成功",myWatch.getLastTaskTimeMillis());
    }

    @GetMapping(value = "/ArtistOwnPage")
    @ApiOperation(value = "展示演员或者导演个人所有信息",
            notes = "传递token和id确认身份.身份未确定，返回登陆指示." +
                    "否则无法显示用户的关注信息")
    public JsonResult ArtistOwnPage(
            @RequestParam("token") String token,
            @RequestParam("userid") Integer userid,
            @RequestParam("artistID") Integer artistID
    )
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        int temp1=0;
        myWatch.stop();
        return new JsonResult(temp1,
                "成功",myWatch.getLastTaskTimeMillis());
    }


}
