package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Entity.users;
import com.example.demo.JSON.JsonResult;
import com.example.demo.service.*;
import com.example.demo.annotation.UserLoginToken;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value="/User",produces = "application/json;charset=UTF-8")
@CrossOrigin
public class UserController {

    @Autowired
    numberService NumberService;

    @Autowired
    userService UserService;

    @Autowired
    TokenService tokenService;

    @Autowired
    CommentService commentService;

    @Autowired
    CollectService collectService;

    @GetMapping(value = "/login")
    @ApiOperation(value = "登陆", notes = "输入手机号，密码登陆")
    public JsonResult login(
            @RequestParam("phone") String phone,
            @RequestParam("password") String password
    )
    {

        Integer temp1=UserService.VerifyLogin(phone, password);
        if(temp1==-1)
        {
            return new JsonResult(temp1, "输入的用户名或者密码有误");
        }
        else
        {
            JSONObject b=new JSONObject();
            b.put("userID",temp1);
            return new JsonResult(b, "登陆成功");
        }
    }



    @GetMapping(value = "/register")
    @ApiOperation(value = "注册", notes = "输入手机号，密码，用户名注册")
    public JsonResult register(
            @RequestParam("phone") String phone,
            @RequestParam("password") String password,
            @RequestParam("username") String username
    )
    {

        users u=new users();

        JSONObject thisUser=new JSONObject();

        Integer temp=NumberService.getSimpleNumbers().getUserNumadd();
        thisUser.put("userID",temp);

        u.setUserID(temp);
        u.setPhone(phone);
        u.setProfileName(username);
        u.setPassword(password);
        u.setRole(0);
        UserService.saveUser(u);
        //在存储表中id自增。
        //非修改id直接传0
        NumberService.updataNumber(++temp,0,0);

        String token = tokenService.getToken(temp - 1,password);
        thisUser.put("token",token);
        return new JsonResult(thisUser,"注册成功");
    }


    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }


    @PostMapping(value = "/ModifyInformation")
    @ApiOperation(value = "修改个人信息", notes = "前端传递修改项")
    public JsonResult ModifyInformation(
            @RequestParam(value = "userid") Integer userid,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "sex") Integer sex,
            @RequestParam(value = "birthday") Date birthday,
            @RequestParam(value = "phone") String phone,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "signature") String signature
    )
    {
        int temp1=0;
        UserService.UpdatePersonalInfor(userid, username,
                sex, birthday, phone, email, signature);
        return new JsonResult(temp1, "修改个人信息成功");
    }

    @PostMapping(value = "/ModifyAvator")
    @ApiOperation(value = "修改个人头像", notes = "前端传递图片,注意文件名不要重复")
    public JsonResult ModifyAvator(
            @RequestParam(value = "userid") Integer userid,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        System.out.println("[文件类型] - [{}]"+ file.getContentType());
        System.out.println("[文件名称] - [{}]"+ file.getOriginalFilename());
        System.out.println("[文件大小] - [{}]"+ file.getSize());
        String fileName="D:/github/MovieAnalyzationPlatform/demo/src/main/resources/static/image/"
                + file.getOriginalFilename();
        file.transferTo(new File(fileName));

        UserService.UpdateAvator(userid,fileName);

        Map<String, String> result = new HashMap<>(4);
        result.put("contentType", file.getContentType());
        result.put("fileSize", file.getSize() + "");
        result.put("filepath", fileName);
        return new JsonResult(result, "上传头像成功");
    }





    @GetMapping(value = "/GetInformation")
    @ApiOperation(value = "获取个人信息", notes = "传递token和id确认身份")
    public JsonResult SaveInformation(
            @RequestParam(value="token",required = false) String token,
            @RequestParam("userid") Integer userid
    )
    {
        users Users=UserService.findUserByIdWeb(userid);
        JSONObject temp1=new JSONObject();
        temp1.put("用户ID",Users.getUserID());
        temp1.put("用户名",Users.getProfileName());
        temp1.put("用户头像地址",Users.getPhoto());
        temp1.put("用户性别",Users.getSex());
        temp1.put("用户生日",Users.getBirthday());
        temp1.put("用户电话号",Users.getPhone());
        temp1.put("用户邮件地址",Users.getEmail());
        temp1.put("用户个性签名",Users.getSignature());
        return new JsonResult(temp1, "获取个人信息成功");
    }



    @GetMapping(value = "/PersonalHomePage")
    @ApiOperation(value = "展示个人信息主页",
            notes = "传递token和id确认身份,要展示评论电影的分页情况，以及评论收藏总数")
    public JsonResult PersonalHomePage(
            @RequestParam(value="token",required = false) String token,
            @RequestParam("userid") Integer userid
    )
    {
        JSONArray temp1=new JSONArray();
        JSONObject temp=UserService.queryNum(userid);
        JSONArray temp2=commentService.getCommentMovie(userid);
        temp1.add(temp);
        temp1.addAll(temp2);
        return new JsonResult(temp1, "成功");
    }



    @GetMapping(value = "/CollectionMovie")
    @ApiOperation(value = "展示个人收藏",
            notes = "传递token和id确认身份,要展示收藏电影的分页情况")
    public JsonResult CollectionMovie(
            @RequestParam(value="token",required = false) String token,
            @RequestParam("userid") Integer userid
    )
    {
        JSONArray temp1= collectService.getCollectMovie(userid);
        return new JsonResult(temp1, "成功");
    }


}
