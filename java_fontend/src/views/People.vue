<template>
  <div class="person">
    <div class="star_container">
      <el-row>
        <el-col :span="3"></el-col>
        <el-col :span="18">
          <div class="starList_con">
            <!--star_sort begin-->
            <div class="person_sort">
              <!--年份分类begin-->
              <div class="pr_box">
                <span id="sorted_type">Profession: </span>
                <el-radio v-model="p_radio" label="1" @change="getPersonList">actor</el-radio>
                <el-radio v-model="p_radio" label="2" @change="getPersonList">director</el-radio>
              </div>
              <!--年份分类end-->
            </div>
            <!--star_sort end-->
            <!--star info list begin-->
              <div class="starList_info" v-for="person in personList" :key="person.person_id" @click="toPeopleInfo(person.person_id)">
                <div class="star_cards">
                  <div class="star_photos">
                    <a href="#">
                      <img :src="converPic(person.person_pic)" :alt="person.person_name"/>
                    </a>
                  </div>
                  <div class="person_info">
                    <p><span class="person_info_title">Name: </span><span class="person_name">{{person.person_name}}</span></p>
                    <p><span class="person_info_title">Profession: </span><span class="person_profession">{{person.person_profession}}</span></p>
                    <p><span class="person_info_title">Star in: </span><span class="person_movie">{{person.person_movies.join(' / ')}}</span></p>
                  </div>
                </div>
              </div>
            <!--star info list end-->
            <!--分页start-->
            <el-pagination
                @current-change="handleCurrentChange"
                :current-page="pagenum"
                layout="prev, pager, next"
                :total="personnum"
                :page-size="8">
            </el-pagination>
            <!--分页end-->
          </div>
        </el-col>

        <el-col :span="3"></el-col>
      </el-row>
    </div>
  </div>
</template>
<script>
import axios from "axios";
export default {
  name: 'person',
  data() {
    return {
      personnum:80,
      pagenum:1,
      p_radio:'1',
      personList: []
    }

  },
  methods:{
    toPeopleInfo(id){
      sessionStorage.setItem("person_id",id);
      this.$router.push('/PeopleInfo');
    },
    setPersonList(data){
      this.personList=[];
      for (let i=0;i<data.length;i++){
        var person={};
        person.person_id=data[i].person_id;
        person.person_pic=data[i].person_pic;
        person.person_name=data[i].person_name;
        person.person_profession=data[i].profession;
        person.person_movies=data[i].movies;
        this.personList.push(person);
      }
    },
    getPersonList(){
      axios.get("http://localhost:8070/Artist/ArtistList",
          {
            params:{
              profession:this.p_radio==='1'?'演员':'导演',
              pagenum:this.pagenum,
              pagesize:6
            }
          },
          { withCredentials: true }
      ).then((response)=>{
        console.log(response);
        var data=response.data.data;
        this.setPersonList(data);
      }).catch((error)=>{
        this.$message.error("Loading Failed!");
      })
    },
    handleCurrentChange(val){
      console.log(`当前页: ${val}`);
      this.pagenum=val;
      this.getPersonList();
    },
    converPic(url){
      if(url==null||url==''){
        return require('../assets/images/unload.png');
      }
      else{
        return require('../assets/images/'+url);
      }
    }
  },
  created() {
    this.getPersonList();//需要触发的函数
  }
}
</script>

<style>
.person .person_info_title{
  font-weight: bold;
  font-family: Georgia, 'Times New Roman', Times, serif;
}

.person .person_profession{
  color: #999999;
}

.person .person_name{
  font-size: 20px;
  font-family: Georgia, 'Times New Roman', Times, serif;
  color: #0066c0;
}

.person .person_movie{
  font-size: 15px;
}

.person .person_info{
  padding: 20px 30px;
  float: left;
}

.person .person_sort{
  margin: 20px;
}

.person .person .search {
  margin: 30px 480px;
}

.person .star_container{
  margin:30px 0px;
  text-align: left;
}

.person .el-col-3 {
  min-height: 100px;
}

.person .el-col-18 {
  min-height: 100px;
  background-color: #ffffff;
}

.person .el-col-6 {
  min-height: 100px;
}

.person .starList_con{
  margin: 20px 20px;
  min-height: 100px;
  /*background-color: aquamarine;*/
}

.star_photos{
  height: 150px;
  float:left;
}

.star_photos>a{
  height: 100%;
  width: auto;
}

.star_photos>a>img{
  height: 100%;
  width: auto;
}

.star_content{
  min-height: 100px;
  width: auto;
  /*background-color: brown;*/
  text-align: left;
  margin:15px;
  float: left;
}

.star_content>p{
  /*margin-left:15px;*/
  color:black;
}

.star_content>p>a{
  color:black;
  text-decoration: none;
}

.star_cards{
  border-top: solid 3px #d7d7d7;
  height: 150px;
  width: auto;
  padding: 20px;
}

#app > div > section > main > div > div.star_container > div > div.el-col.el-col-18 > div > div.el-pagination {
  text-align: center;
  margin: 50px 0px;
}
#app > div > section > main > div > div.star_container > div > div.el-col.el-col-18 > div > div.el-pagination .is-background .el-pager li:not(.disabled).active{
  background-color: #0066c0;
  color: white;
}
</style>