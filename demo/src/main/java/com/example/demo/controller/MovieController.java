package com.example.demo.controller;

import com.example.demo.JSON.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping(value="/Movie",produces = "application/json;charset=UTF-8")
public class MovieController {

    @GetMapping(value = "/MainPage")
    @ApiOperation(value = "展示主页", notes = "如果登陆返回用户信息")
    public JsonResult MainPage()
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        int temp1=0;
        myWatch.stop();
        return new JsonResult(temp1,
                "成功",myWatch.getLastTaskTimeMillis());
    }

    @GetMapping(value = "/MovieList")
    @ApiOperation(value = "展示电影列表", notes = "如果电影列表,前端可选类型、年代、地区")
    public JsonResult MovieList(
            @RequestParam(value = "type",required = false) String type,
            @RequestParam(value = "time",required = false) Date time,
            @RequestParam(value = "area",required = false) String area
    )
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        int temp1=0;
        myWatch.stop();
        return new JsonResult(temp1,
                "成功",myWatch.getLastTaskTimeMillis());
    }

    @GetMapping(value = "/MovieDetails")
    @ApiOperation(value = "展示单个电影信息页",
            notes = "展示电影所有情况,身份未确定评论等一些个人信息无法展示")
    public JsonResult MovieDetails(
            @RequestParam("token") String token,
            @RequestParam("userid") Integer userid,
            @RequestParam("movieId") Integer movieId
    )
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        int temp1=0;
        myWatch.stop();
        return new JsonResult(temp1,
                "成功",myWatch.getLastTaskTimeMillis());
    }

    @PostMapping(value = "/Comment")
    @ApiOperation(value = "用户评论电影",
            notes = "传递token和id确认身份。身份未确定，返回登陆指示")
    public JsonResult Comment(
            @RequestParam("token") String token,
            @RequestParam("userid") Integer userid,
            @RequestParam("movieId") Integer movieId
    )
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        int temp1=0;
        myWatch.stop();
        return new JsonResult(temp1,
                "成功",myWatch.getLastTaskTimeMillis());
    }

    @PostMapping(value = "/Score")
    @ApiOperation(value = "用户给电影打分",
            notes = "传递token和id确认身份。身份未确定，返回登陆指示")
    public JsonResult Score(
            @RequestParam("token") String token,
            @RequestParam("userid") Integer userid,
            @RequestParam("movieId") Integer movieId,
            @RequestParam("score") Float score
    )
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        int temp1=0;
        myWatch.stop();
        return new JsonResult(temp1,
                "成功",myWatch.getLastTaskTimeMillis());
    }

    @PostMapping(value = "/Collection")
    @ApiOperation(value = "用户收藏或者取消收藏电影",
            notes = "传递token和id确认身份。身份未确定，返回登陆指示,op为1表示点赞，0为取消")
    public JsonResult Collection(
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



}
