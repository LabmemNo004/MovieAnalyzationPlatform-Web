<template>
  <div class="movies">
    <!--一些头部元素，可能会修改-->
    <!--一serch start-->
    <div class="search">
      <el-input placeholder="Input movie's name to search." v-model="keyword" class="input-with-select">
        <el-button slot="append" icon="el-icon-search" @click="searchMovies()"></el-button>
      </el-input>
    </div>
    <!--一serch end-->

    <!--主线部分开始-->
    <el-row>
      <el-col :span="3"></el-col>
      <!--left box start-->

      <el-col :span="18">

        <div class="movie_left_con">
          <div class="movie_main_left">
            <!--movie_sort begin-->
            <div class="movie_sort">
              <!--年份分类begin-->
              <div class="mv_box">
                <span id="sorted_type">Type: </span>
                <el-radio v-model="t_radio" label="100" @change="getMovieList">All</el-radio>
                <el-radio v-model="t_radio" label="1" @change="getMovieList">Action</el-radio>
                <el-radio v-model="t_radio" label="2" @change="getMovieList">Fantasy</el-radio>
                <el-radio v-model="t_radio" label="3" @change="getMovieList">Comedy</el-radio>
              </div>
              <!--年份分类end-->
              <!--排序依据begin-->
              <div class="mv_box">
                <span id="order_by">Ordered By: </span>
                <el-radio v-model="o_radio" label="1" @change="getMovieList">highest scores</el-radio>
                <el-radio v-model="o_radio" label="2" @change="getMovieList">comment most</el-radio>
                <el-radio v-model="o_radio" label="3" @change="getMovieList">collection most</el-radio>
              </div>
              <!--排序依据end-->
            </div>
            <!--movie_sort end-->

            <div class="movie_screen_hd"></div>

            <div class="movie1" v-if="seen1">
              <div class=movieList1>
                <el-row :gutter="20">
                  <el-col :span="6" class="col3" v-for="movie in movieList1" :key="movie.movie_id">
                    <div class="movieCard" @click="toMovieInfo(movie.movie_id)">
                      <el-card :body-style="{ padding: '0px'}">
                        <img :src="converPic(movie.movie_pic)"/>
                        <div class="info">
                          <div class="info1">
                            <i class="el-icon-star-on color1"></i>
                            <span class="info_rate">{{ movie.movie_rate }}</span>
                          </div>
                          <div class="info2">{{ movie.movie_name }}</div>
                        </div>
                      </el-card>
                    </div>
                  </el-col>
                </el-row>
              </div>
              <!--分页start-->
              <el-pagination
                  @current-change="handleCurrentChange"
                  :current-page="pagenum"
                  layout="prev, pager, next"
                  :total="movie_num">
              </el-pagination>
              <!--分页end-->
            </div>

            <div class="searchMovie" v-if="seen2">
              <div class="title1">Find the results for you as follows:</div>
              <div class="searchMovieList">
                <el-row>
                  <el-col :span="4" class="col3" v-for="movie in searchMovieList" :key="movie.movie_id">
                    <div class="movieCard" @click="toMovieInfo()">
                      <el-card :body-style="{ padding: '0px' }">
                        <img :src="converPic(movie.movie_pic)"/>
                        <div class="info">
                          <div class="info1">
                            <i class="el-icon-star-on color1"></i>
                            <span class="info_rate">{{ movie.movie_rate }}</span>
                          </div>
                          <div class="info2">{{ movie.movie_name }}</div>
                        </div>
                      </el-card>
                    </div>
                  </el-col>
                </el-row>
              </div>

              <div class="page">
                <el-pagination
                    background
                    layout="prev, pager, next"
                    :total="80">
                </el-pagination>
              </div>
            </div>
          </div>
          <!--movie_screen end-->
        </div>
      </el-col>
      <el-col :span="3"></el-col>
    </el-row>
    <!--主线部分end-->
  </div>
</template>
<script>
import axios from "axios";
export default {
  name: 'Movies',
  data() {
    return {
      keyword: '',
      seen1: true,
      seen2: false,
      total: 6,
      pagenum: 1,
      t_radio: '100',
      o_radio: '1',
      movie_num:120,
      movie_per_page:12,
      movie_type:null,
      movie_time:null,
      movie_area:null,
      movieList1: [],
      searchMovieList: [
        {
          movie_id: 1,
          movie_pic: require('../assets/images/1.png'),
          movie_name: 'The God Father',
          movie_rate: 4.8
        },
        {
          movie_id: 2,
          movie_pic: require('../assets/images/2.png'),
          movie_name: 'Man of Steel',
          movie_rate: 4.9
        },
        {
          movie_id: 3,
          movie_pic: require('../assets/images/3.png'),
          movie_name: 'I am Legend',
          movie_rate: '4.8'
        },
        {
          movie_id: 4,
          movie_pic: require('../assets/images/1.png'),
          movie_name: 'The God Father',
          movie_rate: 4.8
        },
        {
          movie_id: 5,
          movie_pic: require('../assets/images/2.png'),
          movie_name: 'Man of Steel',
          movie_rate: 4.9
        },
        {
          movie_id: 6,
          movie_pic: require('../assets/images/3.png'),
          movie_name: 'I am Legend',
          movie_rate: '4.8'
        }
      ]
    }

  },
  mounted:function(){
    this.getMovieList();//需要触发的函数
  },
  methods:{
    searchMovies(){
      this.seen1=false;
      this.seen2=true;
    },
    setMovies(data){
      this.movieList1=[];
      for(let i=0;i<data.length;i++){
        var movie={};
        movie.movie_id=data[i].movie_id;
        movie.movie_pic=data[i].movie_pic;
        movie.movie_name=data[i].movie_name;
        movie.movie_rate=data[i].movie_rate;
        this.movieList1.push(movie);
      }
    },
    getMovieListByType(){
      axios.get("http://localhost:8070/Movie/SearchMovieByType",
          {
            params:{
              type:this.movie_type,
              order:parseInt(this.o_radio)-1,
              pagenum:this.pagenum,
              pagesize:10
            }
          },
          { withCredentials: true }
      ).then((response)=>{
        console.log(response);
        var data=response.data.data;
        this.setMovies(data);
      }).catch((error)=>{
        this.$message.error("Loading Failed!");
      })
    },
    getMovieList(){
      console.log("getMovieList");
      switch (this.t_radio) {
        case "100":
          this.movie_type="all";
          break;
        case "1":
          this.movie_type = "Action";
          break;
        case "2":
          this.movie_type = "Fantasy";
          break;
        case "3":
          this.movie_type = "Comedy";
          break;
        default:
          this.movie_type = "Action";
      }
      this.getMovieListByType();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pagenum=val;
      this.getMovieList();
    },
    toMovieInfo(id){
      sessionStorage.setItem("movie_id",id);
      this.$router.push('/MovieInfo');
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
  created(){

  }
}
</script>

<style>
.movies {
}

.movies .search {
  margin: 30px 480px;
}

.movies .searchMovie {
  margin: 50px 35px;
}

.movies .title {
  font-size: 25px;
  font-family: Verdana, Geneva, Tahoma, sans-serif;
  text-align: left;
}

.movies .title1 {
  font-size: 18px;
  font-family: Verdana, Geneva, Tahoma, sans-serif;
  text-align: left;
}

.movies .col3 {
  margin-bottom: 20px;
}


.movieCard .el-card__body > img {
  width: 100%;

  height: auto;
  cursor:pointer;
}

.movies .movieList1 {
  margin-top: 20px;
}

.movies .movieList2 {
  margin-top: 20px;
}

.movies .searchMovieList {
  margin-top: 20px;
}

.color1 {
  color: gold;
}

.movies .info {
  margin: 10px;
}

.movies .info1 {
  font-size: 14px;
  text-align: left;
  font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
}

.movies .info_rate {
  margin-left: 10px;
}

.movies .info2 {
  font-size: 17px;
  text-align: left;
  margin-top: 10px;
  font-family: 'Times New Roman', Times, serif;
}

.movies .page {
  margin: 30px 0px;
}

.movies .page .el-pagination button {
  background-color: #ebeef0;
  font-size: 18px;
}

.movies .page .el-pagination .el-pager li {
  font-size: 18px;
  font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
  background-color: #ebeef0;
}

.movies .page .el-pagination .el-pager li.active {
  color: #0084ff;
}

.movies .page .el-pagination .el-pager li:hover {
  color: #6db9ff;
}


#app > div > section > main > div > div.el-row > div.el-col.el-col-18 > div {
  background-color: #ffffff;
}

#app > div > section > main > div > div.el-row > div.el-col.el-col-18 > div > div {

  padding: 30px 20px;
}

.movie_sort {
  font-family: Microsoft YaHei, helvetica, arial;
  color: #222222;
  list-style: none outside none;
  margin: 20px;
  padding: 0;
  font-size: 14px;
}

.mv_box {
  margin: 0px 10px 20px;
  overflow: hidden;
  /*display: inline;*/
  text-align: left;
}

.mvb_title {
  font-family: Microsoft YaHei, helvetica, arial;
  list-style: none outside none;
  font-size: 14px;
  padding: 0;
  float: left;
  font-weight: 550;
  margin: 0 -20px 20px 0;
  color: black;
  width: 50px;
}

.mvb_main {
  font-family: Microsoft YaHei, helvetica, arial;
  color: #222222;
  list-style: none outside none;
  font-size: 14px;
  margin: 0;
  padding: 0;
  float: right;
  width: 720px;
}

a.blue {
  color: #0066c0 !important;
}

a.movie_list_load {
  list-style: none outside none;
  font-size: 14px;
  padding: 0;
  font-family: Microsoft YaHei;
  cursor: pointer;
  text-decoration: none;
  display: inline-block;
  margin: 0 20px 10px 0;
  color: #000000;
  -webkit-font-smoothing: antialiased;
  text-align: left;
  font-size: 16px;
}

#genre_list a {
  min-width: 120px;
  text-align: left;
}


div.movie_screen_hd {
  color: #222222;
  list-style: none outside none;
  margin: 20px;
  padding: 0 0 10px;
  border-bottom: 3px solid #d7d7d7;
  overflow: hidden;
  /*background-color: black;*/
}

div.movie_order {
  margin-bottom: 10px;
  margin-top: 10px;
  font-family: Microsoft YaHei, helvetica, arial;
  list-style: none outside none;
  color: #666666;
  font-size: 16px;
  overflow: hidden;
  text-align: left;
}

div.movie_order > span {
  list-style: none outside none;
  display: block;
  float: left;
  padding-right: 16px;
  overflow: hidden;
  margin: 10px 0px;
}

.movie_order_type {
  margin: 10px 20px;
}

#app > div > section > main > div > div.el-row > div.el-col.el-col-3 {
  min-height: 100px;
  /*background-color: black;*/
}

#app > div > section > main > div > div.el-row > div.el-col.el-col-6 {
  height: 100px;
  /*background-color: royalblue;*/
}

.movie_main_right {
  margin: 0px 10px;
  background-color: #ffffff;
  min-height: 50px;
  padding: 20px 20px;
}

.mvr_keywords {
  text-align: left;
}

.mvr_keywords_age {
  /*margin:30px 0px 0px 0px;*/
  background-color: #ebeef0;
  min-height: 10px;
  padding: 5px 10px;
}

.keyword_ages_list {
  width: 75px;
}


#sorted_type, #order_by {
  font-family: Microsoft YaHei, helvetica, arial;
  margin-right: 10px;
}

div.movie1>div.el-pagination{
  text-align: center;
  margin: 50px 0px;
}

div.movie1>div.el-pagination.is-background .el-pager li:not(.disabled).active{
  background-color: #0066c0;
  color: white;
}

</style>
