package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Entity.users;
import com.example.demo.JSON.JsonResult;
import com.example.demo.service.*;
import com.example.demo.auth.UserLoginToken;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/User",produces = "application/json;charset=UTF-8")
@CrossOrigin
public class UserController {

    @Autowired
    NumberService NumberService;

    @Autowired
    UserService UserService;

    @Autowired
    TokenService tokenService;

    @Autowired
    CommentService commentService;

    @Autowired
    CollectService collectService;

    @GetMapping(value = "/login")
    @ApiOperation(value = "登陆", notes = "输入用户名，密码登陆")
    public JsonResult login(
            @RequestParam("userName") String userName,
            @RequestParam("password") String password
    )
    {

        List<Object []> temp1=UserService.VerifyLogin(userName, password);
        if(temp1.size()==0)
        {
            return new JsonResult(temp1, "输入的用户名或者密码有误");
        }
        else
        {
            JSONObject b=new JSONObject();

            String []tags={"userID","role","username"};
            Object []temp=temp1.get(0);
            for(int i=0;i<temp.length;i++)
            {
                b.put(tags[i],temp[i]);
            }
            String token =
                    tokenService.getToken(Integer.parseInt(temp[0].toString()),password);
            b.put("token",token);
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
        int i=UserService.checkName(username);
        if(i==1)
        {
            return new JsonResult(0,"用户名重复");
        }
        else
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
            u.setCommentNum(0);
            u.setMovieCollectNum(0);
            u.setPeopleCollectNum(0);
            UserService.saveUser(u);
            //在存储表中id自增。
            //非修改id直接传0
            NumberService.updataNumber(++temp,0,0,0);

            String token = tokenService.getToken(temp - 1,password);
            thisUser.put("token",token);
            return new JsonResult(thisUser,"注册成功");
        }

    }


    @UserLoginToken
    @PostMapping("/getMessage")
    public String getMessage(){

        return "你已通过验证";
    }


    @PostMapping(value = "/ModifyInformation")
    @ApiOperation(value = "修改个人信息", notes = "前端传递修改项")
    @ResponseBody
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
//        int i=UserService.checkName(username);
//        if(i==1)
//        {
//            return new JsonResult(0,"用户名已被占用");
//        }

            UserService.UpdatePersonalInfor(userid, username,
                    sex, birthday, phone, email, signature);
            return new JsonResult(temp1, "修改个人信息成功");

    }

    @PostMapping(value = "/ModifyAvatar",headers="content-type=multipart/form-data")
    @ApiOperation(value = "修改个人头像", notes = "前端传递图片,注意文件名不要重复")
    public JsonResult ModifyAvatar(
            @RequestParam(value = "userid") Integer userid,
            @RequestParam(value="file",required = true) MultipartFile file
    ) throws IOException {

        System.out.println("[文件类型] - [{}]"+ file.getContentType());
        System.out.println("[文件名称] - [{}]"+ file.getOriginalFilename());
        System.out.println("[文件大小] - [{}]"+ file.getSize());

        /**
         * 如果有人要调用这个接口需要写好自己的路径。
         */

//        String filePathBefor=System.getProperty("user.dir")+"/src/main/resources/static/image/";
        String filePathBefor=System.getProperty("user.dir")+"/src/assets/images/";
        filePathBefor=filePathBefor.replace('\\','/');
        filePathBefor=filePathBefor.replace("demo","java_fontend");

        String CDY="";
        String CXW="";
        String TZY="";
        String LYF="D:/github/Chen/MovieAnalyzationPlatform-Web/demo/src/main/resources/static/images/";


        String tempfileName=RandomStringUtils.randomAlphanumeric(10)+
                file.getOriginalFilename();
        String filename=filePathBefor+tempfileName;


        file.transferTo(new File(filename));


//        tempfileName="../assets/images/"+tempfileName;
        UserService.UpdateAvator(userid,tempfileName);

        Map<String, String> result = new HashMap<>(4);
        result.put("contentType", file.getContentType());
        result.put("fileSize", file.getSize() + "");
        result.put("filepath", filename);
        return new JsonResult(result, "上传头像成功");
    }



    @PostMapping(value = "/GetInformation")
    @ApiOperation(value = "获取个人信息", notes = "传递token和id确认身份")
    public JsonResult SaveInformation(
            @RequestParam(value="token",required = false) String token,
            @RequestParam("userid") Integer userid
    )
    {
        users Users=UserService.findUserByIdWeb(userid);
        JSONObject temp1=new JSONObject();
        temp1.put("userID",Users.getUserID());
        temp1.put("username",Users.getProfileName());
        temp1.put("avatar",Users.getPhoto());
        temp1.put("sex",Users.getSex());
        temp1.put("birthday",Users.getBirthday());
        temp1.put("phone",Users.getPhone());
        temp1.put("email",Users.getEmail());
        temp1.put("signature",Users.getSignature());
        return new JsonResult(temp1, "获取个人信息成功");
    }

    @UserLoginToken
    @GetMapping(value = "/PersonalHomePage")
    @ApiOperation(value = "展示个人信息主页",
            notes = "传递token和id确认身份,要展示评论电影的分页情况，以及评论收藏总数")
    public JsonResult PersonalHomePage(
            @RequestParam(value="token",required = false) String token,
            @RequestParam("user_id") Integer userid
    )
    {
        JSONArray temp1=new JSONArray();
        JSONObject temp=UserService.queryNum(userid);
        temp1.add(temp);
        return new JsonResult(temp1, "成功");
    }

    @GetMapping(value = "/CommentMovie")
    @ApiOperation(value = "展示个人评论过的电影",
            notes = "传递token和id确认身份,要展示收藏电影的分页情况")

    public JsonResult CommentMovie(
            @RequestParam(value="token",required = false) String token,
            @RequestParam("user_id") Integer userid,
            @RequestParam("pagenum") Integer pagenum,
            @RequestParam("pagesize") Integer pagesize

    )
    {
        JSONArray temp1=commentService.getCommentMovie(userid,pagenum,pagesize);
        int i=temp1.size()-1;
        Object counts=temp1.getJSONObject(i).get("total");
        temp1.fluentRemove(i);
        return new JsonResult(temp1, "成功",counts);
    }



    @GetMapping(value = "/CollectionMovie")
    @ApiOperation(value = "展示个人收藏电影",
            notes = "传递token和id确认身份,要展示收藏电影的分页情况")
    public JsonResult CollectionMovie(
            @RequestParam(value="token",required = false) String token,
            @RequestParam("userid") Integer userid,
            @RequestParam("pagenum") Integer pagenum,
            @RequestParam("pagesize") Integer pagesize
    )
    {
        JSONArray temp1= collectService.getCollectMovie(userid,pagenum,pagesize);
        int i=temp1.size()-1;
        Object counts=temp1.getJSONObject(i).get("total");
        temp1.fluentRemove(i);
        return new JsonResult(temp1, "成功",counts);
    }

    @GetMapping(value = "/CollectionPeople")
    @ApiOperation(value = "展示个人收藏人物",
            notes = "传递token和id确认身份,要展示收藏电影的分页情况")
    public JsonResult CollectionPeople(
            @RequestParam(value="token",required = false) String token,
            @RequestParam("userid") Integer userid,
            @RequestParam("pagenum") Integer pagenum,
            @RequestParam("pagesize") Integer pagesize
    )
    {
        JSONArray temp1= collectService.getCollectPeople(userid,pagenum,pagesize);
        int i=temp1.size()-1;
        Object counts=temp1.getJSONObject(i).get("total");
        temp1.fluentRemove(i);
        return new JsonResult(temp1, "成功",counts);
    }


}
