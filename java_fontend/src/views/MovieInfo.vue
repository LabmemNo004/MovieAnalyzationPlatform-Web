<template>
    <div class="movieinfo">
        <div class="movie_pic l">
            <img :src="movieInfo.movie_pic"/>
        </div>
        <div class="movie_info l">
            <div class="name">{{movieInfo.movie_name}}</div>
            <div class="information">
                <div class="info1">
                    <span class="span1">Director: </span>
                    <span class="span2">{{movieInfo.director}}</span>
                </div>
                <div class="info1">
                    <span class="span1">Main Actors: </span>
                    <span class="span2">{{movieInfo.actors}}</span>
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
                    <span class="span1">Duration: </span>
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
            <div class="content">{{movieInfo.content}}</div>
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
                        score-template="{value}" v-if="movieInfo.myrate!=0">
                    </el-rate>
                    <span v-else class="span5">You haven't given rate.</span>
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
                    <el-form ref="form" :model="Form" label-width="100px" label-position="left">
                        <el-form-item label="Rate:" prop="rate">
                            <el-rate
                                v-model="Form.rate"
                                show-score
                                text-color="#ff9900">
                            </el-rate>
                        </el-form-item>
                        <el-form-item label="Comment:" prop="comment">
                             <el-input type="textarea" v-model="Form.comment" :rows="4" maxlength="150" show-word-limit></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="submitForm()" round>Submit</el-button>
                        </el-form-item>
                    </el-form>
                </div>
            </div>
            <div class="userComment">
                <div>User Comments({{movieInfo.comment_num}})</div>
                <div class=comments v-if="movieInfo.comment_num!=0">
                    <div v-for="comment in commentList" :key="comment.username" >
                        <el-divider></el-divider>
                        <div class="l">
                            <img :src="comment.avatar"/>
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
                                score-template="{value}">
                            </el-rate>
                        </div>
                        <div class="clear"></div>
                        <div class="comment_content">{{comment.content}}</div>
                    </div>
                </div>
                <div class="none" v-else>
                    <el-divider></el-divider>
                    <div>No Comments.</div>
                </div>
            </div>
            <div class="page" v-if="movieInfo.comment_num!=0">
                <el-pagination
                    @current-change="handleCurrentChange"
                    :current-page="pagenum"
                    layout="prev, pager, next"
                    :total="movieInfo.comment_num"
                    :page-size="5">
                </el-pagination>
            </div>
        </div>
    </div>
</template>
<script>
export default {
  name: 'MovieInfo',
  data(){
    return{
        show:false,
        pagenum:1,
        movieInfo:{
            movie_pic:require('../assets/images/1.png'),
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
            content:"The Godfather is an extraordinary novel which has become a modern day classic. Puzo pulls us inside the violent society of the Mafia and its gang wars.The leader, Vito Corleone, is the Godfather. He is a benevolent despot who stops at nothing to gain and hold power. His command post is a fortress on Long Island from which he presides over a vast underground empire that includes the rackets, gambling, bookmaking, and unions. His influence runs through all levels of American society, from the cop on the beat to the nation's mighty.Mario Puzo, a master storyteller, introduces us to unforgettable characters, and the elements of this world explode to life in this violent and impassioned chronicle."
            
        },
        Form:{
            rate:3,
            comment:''
        },
        commentList:[
            {
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
            }
            
        ]
        
    }
    
  },
  methods:{
        collect(){
            if(this.movieInfo.is_collect==false){
                this.movieInfo.is_collect=true;
                this.movieInfo.collect_num++;
            }
            else{
                this.movieInfo.is_collect=false;
                this.movieInfo.collect_num--;
            }
        },
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
</style>