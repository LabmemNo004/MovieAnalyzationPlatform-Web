<template>
    <div class="peopleinfo">
        <div class="person_pic l">
          <img :src="converPic(personInfo.person_pic)"/>
        </div>
        <div class="person_info l">
          <div class="name">{{personInfo.person_name}}</div>
          <div class="information">
              <div class="info1">
                  <span class="span1">Gender: </span>
                  <span class="span2">{{personInfo.gender}}</span>
              </div>
              <div class="info1">
                  <span class="span1">Birthday: </span>
                  <span class="span2">{{personInfo.birthday}}</span>
              </div>
              <div class="info1">
                  <span class="span1">Area: </span>
                  <span class="span2">{{personInfo.area}}</span>
              </div>
               <div class="info1">
                  <span class="span1">Profession: </span>
                  <span class="span2">{{personInfo.profession}}</span>
              </div>
              <div class="info1">
                  <span class="span1">Movies: </span>
                <span class="span2"><el-link v-for="m in personInfo.movies" :key="m.movieID" @click="toMovieInfo(m.movieID)">{{ m.movieName }} / </el-link></span>
              </div>
              <div class="info1">
                  <span class="span1">Times to be Collected: </span>
                  <span class="span2">{{personInfo.collect_num}}</span>
              </div>
          </div>
        </div>
        <div class="clear"></div>
        <div class="collect">
            <span class="span3">To Collect</span>
            <span v-bind:class="['span4',personInfo.is_collect?'color1':'color2']" @click="collect()"><i class="el-icon-star-on"/></span>
        </div>
        <div class="introduction">
            <div class="title l">Introduction</div>
            <div class="line1 l"></div>
            <div class="clear"></div>
            <div class="content">{{personInfo.content}}</div>
        </div>
    </div>
</template>
<script>
import axios from "axios";
export default {
  name: 'PeopleInfo',
  data(){
    return{
      personInfo:{
        person_pic:require('../assets/images/person1.jpg'),
        person_name:'Gal Gadot',
        gender:'female',
        birthday:'1985-04-30',
        area:'Israel',
        profession:'Actress,Producer',
        movies:[{movieID:1,movieName:'Fast & Furious,Wonder Woman'}],
        collect_num:2,
        is_collect:false,
        content:"Gal Gadot-Varsano is an Israeli actress, producer, and model. At age 18, she was crowned Miss Israel 2004. She then served two years in the Israel Defense Forces as a soldier, after which she began studying at the IDC Herzliya college, while building her modelling and acting careers."
      }
    }
    
  },
  mounted:function(){
    this.getPersonInfo();//需要触发的函数
  },
  methods:{
    collect(){
      if(this.personInfo.is_collect===false){
          this.personInfo.is_collect=true;
          this.personInfo.collect_num++;
      }
      else{
          this.personInfo.is_collect=false;
          this.personInfo.collect_num--;
      }
      var tag=this.personInfo.is_collect===false?0:1;
      axios.post("http://localhost:8070/Artist/Follow?" +
      "userid="+this.$store.state.id+
      "&artistID="+sessionStorage.getItem("person_id")+
      "&operations="+tag
    ).then((response)=>{
        console.log(response);
        // var data=response.data.data;
        // this.setPersonInfo(data);
      }).catch((error)=>{
        this.$message.error("Loading Failed!");
      })
    },
    toMovieInfo(id){
      sessionStorage.setItem("movie_id",id);
      this.$router.push('/MovieInfo');
    },
    setPersonInfo(data){
      if(data.length>0){
        data=data[0];
      }
      else return;
      this.personInfo.person_pic=data.person_pic;
      this.personInfo.person_name=data.person_name;
      this.personInfo.gender=data.gender===1?"female":"male";
      var dateType = "";
      var date = new Date();
      date.setTime(data.birthday);
      dateType = date.getFullYear()+"-"+date.getMonth()+1+"-"+date.getDate();
      this.personInfo.birthday=dateType;
      this.personInfo.area=data.area;
      this.personInfo.profession=data.profession;
      this.personInfo.movies=data.movies;
      this.personInfo.collect_num=data.collect_num+1;
      this.personInfo.is_collect=data.is_collect;
      this.personInfo.content=data.introduction;
    },
    getPersonInfo(){
      var person_id=sessionStorage.getItem("person_id");
      console.log(person_id);
      axios.get("http://localhost:8070/Artist/ArtistOwnPage",
          {
            params:{
              user_id:this.$store.state.id,
              person_id:person_id
            }
          },
          { withCredentials: true }
      ).then((response)=>{
        console.log(response);
        var data=response.data.data;
        this.setPersonInfo(data);
      }).catch((error)=>{
        this.$message.error("Loading Failed!");
      })
    },
    converPic(url){
      if(url==null||url==''){
        return require('../assets/images/unload.png');
      }
      else{
        return require('../assets/images/'+url);
      }
    }
  }
}
</script>

<style>
.peopleinfo{
   background-color: #fff;
    border-radius: 20px;
    margin:20px 200px;
    padding: 40px;
}

.peopleinfo .person_pic img{
  width:250px;
  height:350px;
}

.peopleinfo .person_info{
    height:350px;
    margin:20px 50px 10px 50px;
}

.peopleinfo .name{
    text-align: left;
    margin-bottom:25px;
    font-size: 35px;
    font-weight: 500;
    font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.peopleinfo .information{
    text-align: left;
    font-size:18px;
    font-family:'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
}

.peopleinfo .span1{
    font-weight: 550;
}

.peopleinfo .span3{
    font-weight: 550;
    margin-left: 45px;
    font-family:'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
    font-size: 25px;
}

.peopleinfo .span4{
    margin-left: 20px;
    font-size: 35px;
    cursor: pointer;
}

.peopleinfo .info1{
    margin-bottom: 20px;
  width: 600px;
}

.peopleinfo .collect{
    margin-bottom:20px;
    text-align: left;
}

.peopleinfo .color1{
    color:#ff7b05;
}
.peopleinfo .color2{
    color:#cbcbcb;
}

.peopleinfo .introduction{
    text-align: left;
    
}

.peopleinfo .title{
    font-size: 30px;
    font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
}
.peopleinfo .line1{
    height: 2px;
    border-style: dashed none dashed none;
    margin-left:5px;
    margin-top:15px;
    width: 860px;
}

.peopleinfo .content{
    background-color:#f0f0f0;
    margin:20px 0px;
    padding:20px;
    font-size:18px;
    font-family:Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
}
</style>