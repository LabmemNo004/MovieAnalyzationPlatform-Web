package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.Entity.commentmsg;
import com.example.demo.Entity.numbers;
import com.example.demo.JSON.JsonResult;
import com.example.demo.service.CommentService;
import com.example.demo.service.MovieService;
import com.example.demo.service.NumberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@RestController
//@UserLoginToken
@CrossOrigin
@RequestMapping(value="/Movie",produces = "application/json;charset=UTF-8")
public class MovieController {

    @Resource
    private MovieService movieService;

    @Resource
    private CommentService commentService;

    @Resource
    private NumberService numberService;

    @GetMapping(value = "/MainPageTenMovie")
    @ApiOperation(value = "展示主页前十部电影", notes = "如果登陆返回用户信息")
    public JsonResult MainPageTenMovie() throws ParseException
    {
        JSONArray temp=movieService.getTenMovie();
        return new JsonResult(temp,
                "成功");
    }

    @GetMapping(value = "/SearchMovieByKey")
    @ApiOperation(value = "主页按电影名关键词搜索电影", notes = "主页搜索电影")
    public JsonResult SearchMovieByKey(
            @RequestParam(value = "query") String query,
            @RequestParam(value = "pagenum") Integer pagenum,
            @RequestParam(value = "pagesize") Integer pagesize
    )
    {
        JSONArray temp=movieService.getMovieLike(query,pagenum,pagesize);
        int i=temp.size()-1;
        Object counts=temp.getJSONObject(i).get("total");
        temp.fluentRemove(i);
        return new JsonResult(temp,"成功",counts);
    }



    @GetMapping(value = "/SearchMovieByType")
    @ApiOperation(value = "按照电影类别搜索电影", notes = "如果电影列表,前端可选类型、年代、地区")
    public JsonResult SearchMovieByType(
            @RequestParam(value = "type") String type,
            @RequestParam(value = "order") Integer order,
            @RequestParam(value = "pagenum") Integer pagenum,
            @RequestParam(value = "pagesize") Integer pagesize
    )
    {
        JSONArray temp=movieService.getMovieByType(type,order,pagenum,pagesize);
        int i=temp.size()-1;
        Object counts=temp.getJSONObject(i).get("total");
        temp.fluentRemove(i);
        return new JsonResult(temp,"成功",counts);
    }


    @GetMapping(value = "/MovieDetails")
    @ApiOperation(value = "展示单个电影信息页",
            notes = "展示电影所有情况,身份未确定评论等一些个人信息无法展示")
    public JsonResult MovieDetails(
            @RequestParam(value="token",required = false) String token,
            @RequestParam("movie_id") Integer movie_id,
            @RequestParam("user_id") Integer user_id
    )
    {
        JSONArray temp=movieService.getDetailMovie(movie_id,user_id);
        return new JsonResult(temp,"成功");
    }

    @GetMapping(value = "/MovieComment")
    @ApiOperation(value = "展示单个电影的所有评论",
            notes = "展示电影所有情况,身份未确定评论等一些个人信息无法展示")
    public JsonResult MovieComment(
            @RequestParam(value="token",required = false) String token,
            @RequestParam("movie_id") Integer movie_id,
            @RequestParam("pagenum") Integer pagenum,
            @RequestParam("pagesize") Integer pagesize
            )
    {
        JSONArray temp=movieService.getMovieComment(movie_id,pagenum,pagesize);


        int i=temp.size()-1;
        Object counts=temp.getJSONObject(i).get("total");
        if(counts.equals(-100))
        {
            return new JsonResult(temp,"成功",counts);
        }
        else
        {
            temp.fluentRemove(i);
        }
        return new JsonResult(temp,"成功",counts);
    }


    @PostMapping(value = "/Comment")
    @ApiOperation(value = "用户评论电影",
            notes = "传递token和id确认身份。身份未确定，返回登陆指示")
    public JsonResult Comment(
            @RequestParam(value="token",required = false) String token,
            @RequestParam("user_id") Integer user_id,
            @RequestParam("movie_id") Integer movie_id,
            @RequestParam("time") Date time,
            @RequestParam("content") String content
            )
    {
        commentmsg temp=
                commentService.QueryExistMovie(movie_id,user_id);
        if(temp==null) {
            numbers number=numberService.getSimpleNumbers();
            commentService.saveCommentMsg(number.getCommentMsg(),
                    user_id,movie_id,0f,time,content);
            numberService.updataNumber(0,0,
                    0,number.getCommentMsg() + 1);

            return new JsonResult(0,"成功添加");
        }
        else
        {
            commentService.UpdateComment(movie_id,user_id,content,time);
            return new JsonResult(0,"成功修改评论");
        }

    }

    @PostMapping(value = "/Score")
    @ApiOperation(value = "用户给电影打分",
            notes = "传递token和id确认身份。身份未确定，返回登陆指示")
    public JsonResult Score(
            @RequestParam(value="token",required = false) String token,
            @RequestParam("user_id") Integer user_id,
            @RequestParam("movie_id") Integer movie_id,
            @RequestParam("rate") Float rate
    )
    {
        commentmsg temp=
                commentService.QueryExistMovie(movie_id,user_id);
        if(temp==null)
        {
            numbers number=numberService.getSimpleNumbers();

            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date dates = new Date(System.currentTimeMillis());
            System.out.println(formatter.format(dates));

            commentService.saveCommentMsg(number.getCommentMsg(),user_id,movie_id,
                    rate,dates,"");
            numberService.updataNumber(0,0,
                    0,number.getCommentMsg() + 1);
        }
        else
        {
            commentService.UpdateScore(movie_id,user_id,rate);
        }
        return new JsonResult(0,
                "成功");
    }

    @PostMapping(value = "/Collection")
    @ApiOperation(value = "用户收藏或者取消收藏电影",
            notes = "传递token和id确认身份。身份未确定，返回登陆指示,op为1表示点赞，0为取消")
    public JsonResult Collection(
            @RequestParam(value="token",required = false) String token,
            @RequestParam("userid") Integer userid,
            @RequestParam("movieId") Integer movieId,
            @RequestParam("operations") Integer operations
            )
    {
        int temp1=0;
        movieService.AddCollectMovie(userid, movieId, operations);
        if(operations==1)
        {
            return new JsonResult(temp1,"收藏电影成功");
        }
        else{
            return new JsonResult(temp1,"取消收藏电影");
        }
    }

}
