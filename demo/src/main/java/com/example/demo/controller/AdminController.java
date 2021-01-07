package com.example.demo.controller;


import com.example.demo.Entity.artist;
import com.example.demo.Entity.movie;
import com.example.demo.Entity.numbers;
import com.example.demo.Entity.participate;
import com.example.demo.JSON.JsonResult;
import com.example.demo.auth.PassToken;
import com.example.demo.service.ArtistService;
import com.example.demo.service.FollowService;
import com.example.demo.service.MovieService;
import com.example.demo.service.NumberService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value="/Admin",produces = "application/json;charset=UTF-8")
@CrossOrigin
public class AdminController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private NumberService numberService;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private FollowService followService;



    @PostMapping(value = "/UploadMovie")
    @ApiOperation(value = "上传一部电影", notes = "未完无法使用")
    public JsonResult UploadMovie(
            @RequestParam("movie_name") String movie_name,
            @RequestParam("type") String type,
            @RequestParam("area") String area,
            @RequestParam("directors") String directors,
            @RequestParam("movie_pic") MultipartFile file,
            @RequestParam("Introduction") String Introduction,
            @RequestParam("publish_time") Date publish_time,
            @RequestParam("duration") Integer duration
    ) throws IOException
    {
        int movieID=0;
        movie temp=new movie();
        temp.setArea(area);
        temp.setCollectnum(0);
        temp.setIntroduction(Introduction);
        numbers number=numberService.getSimpleNumbers();
        temp.setMovieID(number.getMovieNumadd());
        movieID=number.getMovieNumadd() + 1;
        numberService.updataNumber(0,0,
                movieID,0);
        temp.setPublishTime(publish_time);
        temp.setType(type);
        temp.setTitle(movie_name);
        temp.setDuration(duration);
        temp.setDirector(directors);
        temp.setCommentnum(0);
        movieService.saveMovie(temp);
        String movie_pic="";
        /**
         * 上传图片获得图片地址。
         */

        String filePathBefor=System.getProperty("user.dir")+"/src/assets/images/";
        filePathBefor=filePathBefor.replace('\\','/');
        filePathBefor=filePathBefor.replace("demo","java_fontend");

        String CDY="";
        String CXW="";
        String TZY="";
        String LYF="D:/github/Chen/MovieAnalyzationPlatform-Web/demo/src/main/resources/static/image/";

        String tempfileName=RandomStringUtils.randomAlphanumeric(10)+
                file.getOriginalFilename();

        String filename=filePathBefor+tempfileName;


        file.transferTo(new File(filename));

        movieService.UploadAvatar(movieID,tempfileName);

        Map<String, String> result = new HashMap<>(4);
        result.put("contentType", file.getContentType());
        result.put("fileSize", file.getSize() + "");
        result.put("filepath", filename);
        System.out.println(result);



        return new JsonResult(movieID, "上传成功");

    }



    @PostMapping(value = "/UploadMoviePerson")
    @ApiOperation(value = "上传电影的对应人物", notes = "未完无法使用")
    public JsonResult UploadMoviePerson(
            @RequestParam("movieID") Integer movieID,
            @RequestParam("personName") String personName,
            @RequestParam("sex") Integer sex,
            @RequestParam("birthday") Date birthday,
            @RequestParam("area") String area,
            @RequestParam("profession") String profession,
            @RequestParam("introduction") String introduction,
            @RequestParam("Avatar") MultipartFile file
            ) throws IOException
    {
        int personID=0;
        artist temp=new artist();
        numbers number=numberService.getSimpleNumbers();
        temp.setId(number.getArtistNumadd());
        personID=number.getArtistNumadd() +1;
        numberService.updataNumber(0,personID,
                0,0);
        temp.setPersonName(personName);
        temp.setSex(sex);
        temp.setBirthday(birthday);
        temp.setArea(area);
        temp.setProfession(profession);
        temp.setIntroduction(introduction);
        temp.setCollect_num(0);
        artistService.saveArtist(temp);

        String Artist_pic="";

        /**
         * 上传图片获得图片地址。
         */
        String filePathBefor=System.getProperty("user.dir")+"/src/assets/images/";
        filePathBefor=filePathBefor.replace('\\','/');
        filePathBefor=filePathBefor.replace("demo","java_fontend");

        String CDY="";
        String CXW="";
        String TZY="";
        String LYF="D:/github/Chen/MovieAnalyzationPlatform-Web/demo/src/main/resources/static/image/";

        String tempfileName=RandomStringUtils.randomAlphanumeric(10)+
                file.getOriginalFilename();

        String filename=filePathBefor+tempfileName;


        file.transferTo(new File(filename));

        artistService.UploadAvatar(personID,tempfileName);

        Map<String, String> result = new HashMap<>(4);
        result.put("contentType", file.getContentType());
        result.put("fileSize", file.getSize() + "");
        result.put("filepath", filename);
        System.out.println(result);
        /**
         * 再添加人物和电影之间的关系。
         */
        participate temp10=new participate();
        temp10.setMovieID(movieID);
        temp10.setArtistID(personID);
        followService.SaveMovieAndArtist(temp10);

        return new JsonResult(0, "上传成功");

    }




}
