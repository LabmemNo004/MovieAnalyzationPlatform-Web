<template>
    <div class="movieinfo">
        <div class="movie_pic l">
            <img :src="movieInfo.movie_pic" @error="away()"/>
        </div>
        <div class="movie_info l">
            <div class="name">{{movieInfo.movie_name}}</div>
            <div class="information">
                <div class="info1">
                    <span class="span1">Director: </span>
                    <span class="span2" v-for="director in movieInfo.director" :key="director">{{director  }}</span>
                </div>
                <div class="info1">
                    <span class="span1">Main Actors: </span>
                    <span class="span2" v-for="actor in movieInfo.actors" :key="actor">{{actor  }}</span>
                </div>
                 <div class="info1">
                    <span class="span1">Type: </span>
                    <span class="span2">{{movieInfo.type}}</span>
                </div>
                 <div class="info1">
                    <span class="span1">Area: </span>
                    <span class="span2">{{movieInfo.area}}</span>
                </div>
                 <div class="info1">
                    <span class="span1">Publish Time: </span>
                    <span class="span2">{{movieInfo.pub_time}}</span>
                </div>
                <div class="info1">
                    <span class="span1">Duration(minutes): </span>
                    <span class="span2">{{movieInfo.duration}}</span>
                </div>
                <div class="info1">
                    <span class="span1">Times to be Collected: </span>
                    <span class="span2">{{movieInfo.collect_num}}</span>
                </div>
            </div>
        </div>
        <div class="clear"></div>
        <div class="collect">
            <span class="span3">To Collect</span>
            <span v-bind:class="['span4',movieInfo.is_collect?'color1':'color2']" @click="collect()"><i class="el-icon-star-on"/></span>
        </div>
         
        <div class="introduction">
            <div class="title l">Introduction</div>
            <div class="line1 l"></div>
            <div class="clear"></div>
            <div class="content">{{movieInfo.introduction}}</div>
        </div>
        <div class="comment">
            <div class="title l">Rate and Comments</div>
            <div class="line2 l"></div>
            <div class="clear"></div>
            <div class="myRate">
                <div class="l">My Rate:</div>
                <div class="l">
                    <el-rate
                        v-model="movieInfo.my_rate"
                        disabled
                        show-score
                        text-color="#ff9900"
                        score-template="{value}" v-if="movieInfo.myrate>0">
                    </el-rate>
                    <span v-else class="span5">您还未打分</span>
                </div>
                <div class="clear"></div>
            </div>
            <div class="myComment">
                <span>My Comment:</span>
                <span v-if="movieInfo.my_comment!=''" class="span5">{{movieInfo.my_comment}}</span>
                <span v-else class="span5">You haven't writen comment.</span>
            </div>
            <div class="edit">
                <div class=editButton>
                    <el-button type="primary" round @click="show=true">Edit Rate and Comment</el-button>
                </div>
                <div class="form" v-if="show">
                    <el-form ref="form" :model="Form" label-width="100px" label-position="left" :inline="true">
                        <el-form-item label="Rate:" prop="rate">
                            <el-rate
                                v-model="Form.rate"
                                show-score
                                text-color="#ff9900">
                            </el-rate>
                        </el-form-item>
                        <el-form-item class="markButton">
                            <el-button type="primary" @click="submitForm1()" round size="small">Marking</el-button>
                        </el-form-item>
                    </el-form>
                    <el-form ref="form" :model="Form" label-width="100px" label-position="left">
                        <el-form-item label="Comment:" prop="comment">
                             <el-input type="textarea" v-model="Form.comment" :rows="4" maxlength="150" show-word-limit></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="submitForm2()" round>Submit</el-button>
                        </el-form-item>
                    </el-form>
                </div>
            </div>
            <div class="userComment">
                <div>User Comments({{this.totalNum}})</div>
                <div class=comments v-if="this.totalNum!=0">
                    <div v-for="comment in commentList" :key="comment.username" >
                        <el-divider></el-divider>
                        <div class="l">
                            <img :src="comment.avatar" @error="def()"/>
                        </div>
                        <div class="l username">
                            <span class="span6">{{comment.username}}</span>
                        </div>
                        <div class="l rate">
                            <el-rate
                                v-model="comment.rate"
                                disabled
                                show-score
                                text-color="#ff9900"
                                score-template="{value}" v-if="comment.rate>0">
                            </el-rate>
                            <span v-else class="span5">未打分</span>
                        </div>
                        <div class="l time">{{comment.time}}</div>
                        <div class="clear"></div>
                        <div class="comment_content">{{comment.content}}</div>
                    </div>
                </div>
                <div class="none" v-else>
                    <el-divider></el-divider>
                    <div>No Comments.</div>
                </div>
            </div>
            <div class="page" v-if="totalNum>0">
                <el-pagination
                    @current-change="handleCurrentChange"
                    :current-page="pagenum"
                    layout="prev, pager, next"
                    :total="totalNum"
                    :page-size="5">
                </el-pagination>
            </div>
        </div>
    </div>
</template>
<script>
import axios from "axios";
const dateFormat=function(t){
    let year=new Date(t).getFullYear();
    let month=new Date(t).getMonth() + 1 < 10? "0" + (new Date(t).getMonth() + 1): new Date(t).getMonth() + 1;
    let date=new Date(t).getDate() < 10? "0" + new Date(t).getDate(): new Date(t).getDate();
    let hour=new Date(t).getHours() < 10? "0" + new Date(t).getHours(): new Date(t).getHours();
    let minute=new Date(t).getMinutes() < 10? "0" + new Date(t).getMinutes(): new Date(t).getMinutes();
    let second=new Date(t).getSeconds() < 10? "0" + new Date(t).getSeconds(): new Date(t).getSeconds();
    return year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second;
}
const dateFormat1=function(t){
    let year=new Date(t).getFullYear();
    let month=new Date(t).getMonth() + 1 < 10? "0" + (new Date(t).getMonth() + 1): new Date(t).getMonth() + 1;
    let date=new Date(t).getDate() < 10? "0" + new Date(t).getDate(): new Date(t).getDate();
    return year+"-"+month+"-"+date;
}
const convert=function(t){
    return t/3600;
}
export default {
  name: 'MovieInfo',
  data(){
    return{
        show:false,
        pagenum:1,
        defaultImg:require('../assets/images/avatar.png'),
        unload:require('../assets/images/unload.png'),
        totalNum:0,
        movieInfo:{
           /* movie_pic:require('../assets/images/1.png'),
            movie_name:'The God Father',
            director:'Francis Ford Coppola',
            actors:'Marlon Brando,Al Pacino',
            type:'Action',
            area:'America',
            pub_time:'1972-3-24',
            duration:'175 minutes',
            collect_num:3,
            comment_num:3,
            is_collect:false,
            my_rate:4.8,
            my_comment:'',
            content:"The Godfather is an extraordinary novel which has become a modern day classic. Puzo pulls us inside the violent society of the Mafia and its gang wars.The leader, Vito Corleone, is the Godfather. He is a benevolent despot who stops at nothing to gain and hold power. His command post is a fortress on Long Island from which he presides over a vast underground empire that includes the rackets, gambling, bookmaking, and unions. His influence runs through all levels of American society, from the cop on the beat to the nation's mighty.Mario Puzo, a master storyteller, introduces us to unforgettable characters, and the elements of this world explode to life in this violent and impassioned chronicle."*/
            
        },
        Form:{
            rate:'',
            comment:''
        },
        commentList:[
            /*{
                avatar:require('../assets/images/avatar0.jpg'),
                username:'nianwuluo',
                rate:4.5,
                content:"It's boring!"
            },
            {
                avatar:require('../assets/images/avatar0.jpg'),
                username:'user2',
                rate:4.8,
                content:"It's boring!"
            },
            {
                avatar:require('../assets/images/avatar0.jpg'),
                username:'user3',
                rate:3.2,
                content:"It's boring!"
            }*/
            
        ]
        
    }
    
  },
  methods:{
      away(){
        let img = event.srcElement;   
        img.src = this.unload;   
        img.onerror = null; //防止闪图
    },
      def(){
           let img = event.srcElement;   
           img.src = this.defaultImg;   
           img.onerror = null; //防止闪图
       },
        collect(){
            var movieid=sessionStorage.getItem("movie_id");
            console.log(movieid);
            if(this.movieInfo.is_collect==false){
                var op=1;
                axios.post("http://localhost:8070/Movie/Collection?userid="+this.$store.state.id+"&movieId="+movieid+"&operations="+op).then((response)=>{
                    console.log(response);
                    this.$message.success("Collect Succeed!");
                    this.movieInfo.is_collect=true;
                    this.movieInfo.collect_num++;
                }).catch((error)=>{
                    this.$message.error("Collect Failed!");
                })
                
            }
            else{
                var op=0;
                axios.post("http://localhost:8070/Movie/Collection?userid="+this.$store.state.id+"&movieId="+movieid+"&operations="+op).then((response)=>{
                    console.log(response);
                    this.$message.success("Cancel Succeed!");
                    this.movieInfo.is_collect=false;
                    this.movieInfo.collect_num--;
                }).catch((error)=>{
                    this.$message.error("Cancel Failed!");
                })
               
            }
        },
        async getMovieInfo(){
            var movieid=sessionStorage.getItem("movie_id");
            console.log(movieid);
            axios.get("http://localhost:8070/Movie/MovieDetails",{
                params:{
                  movie_id:movieid,
                  user_id:this.$store.state.id
                }
            }).then((response)=>{
                console.log(response);
                this.movieInfo=response.data.data[0];
                var time=new Date(this.movieInfo.pub_time);
                this.movieInfo.pub_time=dateFormat1(time);
                //this.movieInfo.duration=convert(this.movieInfo.duration);
            }).catch((error)=>{
                this.$message.error("Get Movie's Details Failed!");
            })
        },
        async getCommentList(){
            var movieid=sessionStorage.getItem("movie_id");
            console.log(movieid);
            axios.get("http://localhost:8070/Movie/MovieComment",{
                 params:{
                  movie_id:movieid,
                  pagenum:this.pagenum,
                  pagesize:5
                }
            }).then((response)=>{
                console.log(response);
                this.commentList=response.data.data;
                if(response.data.totalNum<0){
                    this.totalNum=0;
                }
                else{
                    this.totalNum=response.data.totalNum;
                }
                
                
                var i=0;
                for(i=0;i<this.commentList.length;i++){
                    var date=new Date(this.commentList[i].time);
                    console.log(date);
                    this.commentList[i].time=dateFormat1(date);
                    if(this.commentList[i].rate<0){
                      this.commentList[i].rate=0;
                    }
                }
            }).catch((error)=>{
                this.$message.error("Get Comment List Failed!");
            })

        },
        async submitForm1(){
            if(this.Form.rate==''){
                this.$message.error("Marking First!");
                return;
            }
            var movieid=sessionStorage.getItem("movie_id");
            console.log(movieid);
            await axios.post("http://localhost:8070/Movie/Score?user_id="+this.$store.state.id+"&movie_id="+movieid+"&rate="+this.Form.rate
               ).then((response)=>{
                   console.log(response);
                   this.$message.success("Marking Success!");
                   this.movieInfo.my_rate=this.Form.rate;
               }).catch((error)=>{
                   this.$message.error("Marking Failed!");
               });
        
        },
        async submitForm2(){
            if(this.Form.comment==''){
                this.$message.error("Write Comment First!");
                return;
            }
            var movieid=sessionStorage.getItem("movie_id");
            console.log(movieid);
            var time=dateFormat1(new Date());

            await axios.post("http://localhost:8070/Movie/Comment?user_id="+this.$store.state.id+"&movie_id="+movieid+"&time="+time+"&content="+this.Form.comment
               ).then((response)=>{
                   console.log(response);
                   this.$message.success("Appraise Success!");
                   this.movieInfo.my_comment=this.Form.comment;
               }).catch((error)=>{
                   this.$message.error("Appraise Failed!");
            });
        
        },
        handleCurrentChange(val) {
            console.log(`当前页: ${val}`);
            this.pagenum=val;
            this.getCommentList();
        },
    },
    created(){
        this.getMovieInfo();
        this.getCommentList();
    }
}
</script>

<style>
.movieinfo{
    background-color: #fff;
    border-radius: 20px;
    margin:20px 150px;
    padding: 40px;
}

.movieinfo .movie_pic img{
  width:250px;
  height:350px;
}

.movieinfo .movie_info{
    height:350px;
    margin:20px 50px 10px 50px;
}

.movieinfo .name{
    text-align: left;
    margin-bottom:25px;
    font-size: 35px;
    font-weight: 500;
    font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.movieinfo .information{
    text-align: left;
    font-size:18px;
    font-family:'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
}

.movieinfo .span1{
    font-weight: 550;
}

.movieinfo .span3{
    font-weight: 550;
    margin-left: 45px;
    font-family:'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
    font-size: 25px;
}

.movieinfo .span4{
    margin-left: 20px;
    font-size: 35px;
    cursor: pointer;
}

.movieinfo .span5{
    margin-left: 20px;
    font-size: 16px;
}

.movieinfo .span6{
     font-size: 18px;
    font-family:'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
}

.movieinfo .username{
    margin-left:20px;
    margin-top:5px;
}

.movieinfo .collect{
    margin-bottom:20px;
    text-align: left;
}

.movieinfo .info1{
    margin-bottom: 15px;
}

.movieinfo .introduction{
    text-align: left;
    
}
.movieinfo .title{
    font-size: 30px;
    font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
}
.movieinfo .line1{
    height: 2px;
    border-style: dashed none dashed none;
    margin-left:5px;
    margin-top:15px;
    width: 960px;
}
.movieinfo .line2{
     height: 2px;
    border-style: dashed none dashed none;
    margin-left:5px;
    margin-top:15px;
    width: 850px;
}
.movieinfo .content{
    background-color:#f0f0f0;
    margin:20px 0px;
    padding:20px;
    font-size:18px;
    font-family:Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
}

.movieinfo .color1{
    color:#ff7b05;
}
.movieinfo .color2{
    color:#cbcbcb;
}

.movieinfo .myRate{
    font-size: 22px;
    margin:20px;
    text-align: left;
}

.movieinfo .myComment{
    font-size: 22px;
    margin:20px;
    text-align: left;
}

.movieinfo .el-rate{
    width: 200px;
    margin-left: 20px;
    font-size: 25px;
}

.movieinfo .edit{
    margin: 20px;
    text-align: left;
}

.movieinfo .el-button{
    font-size: 20px;
}
.movieinfo .markButton .el-button{
    font-size:16px;
}
.movieinfo .editButton{
    margin-bottom:20px;
}

.movieinfo .form{
    text-align: left;
    width: 600px;
}

.movieinfo .el-form-item__label{
    font-size: 20px;
}

.form .el-rate{
    width: 200px;
    font-size: 35px;
    margin-left: 0px;
}

.movieinfo .userComment{
    font-size: 22px;
    margin:40px 20px;
    text-align: left;
}

.movieinfo .comments{
    margin-top:20px;
}

.movieinfo .none{
    margin-top:20px;
    font-size:18px;
    font-family:Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
}


.movieinfo .userComment img{
    width:40px;
    height:40px;
    border-radius: 20px;
}

.movieinfo .comment_content{
    font-size:18px;
    font-family:Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
    margin-left: 20px;
    margin-top:15px;
}

.movieinfo .page .el-pagination button{
    background-color:  #fff;
    font-size: 18px;
}
.movieinfo .page .el-pagination .el-pager li{
  font-size: 18px;
  font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
  background-color:  #fff;
}
.movieinfo .page .el-pagination .el-pager li.active{
  color: #0084ff;
}
.movieinfo .page .el-pagination .el-pager li:hover{
  color: #6db9ff;
}

.movieinfo .rate{
    margin-top:5px;
}

.movieinfo .time{
    font-size: 18px;
    margin-top: 10px;
    font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}
</style>