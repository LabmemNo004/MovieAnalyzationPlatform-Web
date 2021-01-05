<template>
    <div class="peopleinfo">
        <div class="person_pic l">
          <img :src="personInfo.person_pic"/>
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
                  <span class="span2">{{personInfo.movies}}</span>
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
        movies:'Fast & Furious,Wonder Woman',
        collect_num:2,
        is_collect:false,
        content:"Gal Gadot-Varsano is an Israeli actress, producer, and model. At age 18, she was crowned Miss Israel 2004. She then served two years in the Israel Defense Forces as a soldier, after which she began studying at the IDC Herzliya college, while building her modelling and acting careers."
      }
    }
    
  },
  methods:{
    collect(){
      if(this.personInfo.is_collect==false){
          this.personInfo.is_collect=true;
          this.personInfo.collect_num++;
      }
      else{
          this.personInfo.is_collect=false;
          this.personInfo.collect_num--;
      }
  },
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