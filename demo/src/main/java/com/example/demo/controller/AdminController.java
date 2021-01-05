package com.example.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.JSON.JsonResult;
import com.example.demo.service.MovieService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(value="/Admin",produces = "application/json;charset=UTF-8")
@CrossOrigin
public class AdminController {

    @Autowired
    private MovieService movieService;

    @PostMapping(value = "/UploadMovie")
    @ApiOperation(value = "上传一部电影", notes = "未完无法使用")
    public JsonResult UploadMovie(
            @RequestParam("movie_name") String movie_name,
            @RequestParam("director") String director,
            @RequestParam("actors") String actors,
            @RequestParam("type") String type,
            @RequestParam("area") String area,
            @RequestParam(value="file",required = true) MultipartFile file,
            @RequestParam("publish_time") Date publish_time,
            @RequestParam("duration") Integer duration,
            @RequestParam("price") Float price
    )
    {
        String movie_pic="";


        movieService.movieRepository(director,actors,
                type,area,movie_pic,publish_time,duration,price);



        return new JsonResult(0, "上传成功");

    }





}
