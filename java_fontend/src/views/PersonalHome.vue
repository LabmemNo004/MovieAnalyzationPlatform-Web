<template>
    <div class="personalhome">
        <div class="head"><!--头像、昵称区域-->
          <div class="l">
            <img :src="avatar" class="ava_pic"/>
          </div>
          <div class="l top2">
            <span class="username">{{username}}</span>
          </div>
          <div class="clear"></div>
        </div>
        <div class="line"></div>
        <div class="collect"><!--收藏夹区域-->
          <div class="title">My Collections</div>
          <div class="l collectMovie">
            <div class="title1">Collection of Movies</div>
            <div class="icon">
              <i class="el-icon-s-unfold"></i>
            </div>
            <div class="num">Num: {{movieCollectNum}}</div>
          </div>
          <div class="l collectPeople">
            <div class="title1">Collection of People</div>
            <div class="icon">
              <i class="el-icon-s-unfold"></i>
            </div>
            <div class="num">Num: {{peopleCollectNum}}</div>
          </div>
          <div class="clear"></div>
        </div>
        <div class="line"></div>
        <div class="comment"><!--评论区域-->
          <div class="title">My Comments</div>
          <div class="num1">Num: {{commentNum}}</div>
          <div class="comments">
            <el-row>
              <el-col :span="12" class="col" v-for="comment in commentList" :key="comment.movie_id">
                <div class="l">
                  <img :src="comment.movie_pic" class="image"/>
                </div>
                <div class="l movieInfo">
                  <div class="movie_name">{{comment.movie_name}}</div>
                  <div class="my_rate">
                    <span>My Rate: </span>
                    <i class="el-icon-star-on color1"></i>
                    <span class="info_rate">{{comment.my_rate}}</span>
                  </div>
                  <div class="my_comment">My Comment: {{comment.my_comment}}</div>
                </div>
                <div class="clear"></div>
              </el-col>
            </el-row>
          </div>
        </div>
        <div class="page">
          <el-pagination
            @current-change="handleCurrentChange"
            :current-page="pagenum"
            layout="prev, pager, next"
            :total="total"
            :page-size="4">
          </el-pagination>
        </div>
    </div>
</template>
<script>
export default {
  name: 'PersonalHome',
  data(){
    return{
      avatar:require('../assets/images/avatar0.jpg'),
      username:'nianwuluo',
      movieCollectNum:0,
      peopleCollectNum:0,
      commentNum:0,
      pagenum:1,
      total:3,
      commentList:[
        {
          movie_id:1,
          movie_pic:require('../assets/images/1.png'),
          movie_name:'The God Father',
          my_rate:5.0,
          my_comment:"It beats me!It beats me!It beats me!It beats me!It beats me!"
        },
        {
          movie_id:2,
          movie_pic:require('../assets/images/2.png'),
          movie_name:'Man of Steel',
          my_rate:4.0,
          my_comment:"It beats me!"
        },
        {
          movie_id:3,
          movie_pic:require('../assets/images/3.png'),
          movie_name:'I am Legend',
          my_rate:5.0,
          my_comment:"It beats me!"
        }
      ]
    }
    
  },
  methods:{
    getCommentList(){

    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pagenum=val;
      this.getCommentList();
    },
  }
}
</script>

<style>
.personalhome{
  background-color: #fff;
  border-radius: 20px;
  margin:20px 100px;
  padding: 40px;
}

.personalhome .head{
  text-align: left;
}

.personalhome .ava_pic{
  width: 80px;
  height:80px;
  border-radius: 40px;
}

.personalhome .username{
  margin: 20px 40px;
  font-size: 25px;
  font-family:Verdana, Geneva, Tahoma, sans-serif
}

.personalhome .line{
  width:1220px;
  height: 2px;
  border-style: dashed none dashed none;
  border-color: #000;
  margin:30px 0px;
}

.personalhome .collect{
  text-align: left;
}

.personalhome .title{
  font-size: 30px;
  font-family:Verdana, Geneva, Tahoma, sans-serif;
  margin:30px 0px;
}

.personalhome .title1{
  font-size: 20px;
  font-family:'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
  color:#ffcc00;
}

.personalhome .collectMovie{
  width:200px;
  height:100px;
  background-color:lemonchiffon;
  padding:20px 0px 20px 20px;
  margin-right:50px;
}
.personalhome .icon{
  font-size: 35px;
  color:#ffcc00;
  text-align: right;
  margin:10px 5px;
}
.personalhome .icon:hover{
  color:#fd9d05;
  cursor: pointer;
}

.personalhome .num{
  font-size: 20px;
  font-family:'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
  color:#ffcc00;
}
.personalhome .num1{
  font-size: 20px;
  margin-bottom: 20px;
}
.personalhome .collectPeople{
  width:200px;
  height:100px;
  background-color:lemonchiffon;
  padding:20px 0px 20px 20px;
  margin-right:50px;
}

.personalhome .comment{
  text-align: left;
}

.personalhome .col{
  margin-bottom: 40px;
}

.personalhome .image{
  width:100px;
  height:140px;
}

.personalhome .movieInfo{
  margin:0px 40px 0px 20px;
  width:440px;
}

.personalhome .movie_name{
  font-size: 20px;
  font-family:Georgia, 'Times New Roman', Times, serif;
  margin-bottom: 10px;
}

.personalhome .my_rate{
  font-size: 18px;
  font-family:'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
  margin-bottom: 10px;
}

.personalhome .info_rate{
  margin-left: 5px;
}

.personalhome .my_comment{
  font-size: 18px;
  font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
}
</style>