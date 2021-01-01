<template>
    <div class="home">
      <div class="head_pic">
        <el-carousel indicator-position="outside" height="400px" :interval="4000">
          <el-carousel-item v-for="item in headPictures" :key="item.id">
            <img :src="item.source" class="images" />
          </el-carousel-item>
        </el-carousel>
      </div>
      <div class="search">
        <el-input placeholder="Input movie's name to search." v-model="keyword" class="input-with-select">
          <el-button slot="append" icon="el-icon-search" @click="searchMovies()"></el-button>
        </el-input>
      </div>
      <div class="movie1" v-if="seen1">
        <div class="title">---HollyWood Movies of 2019---</div>
        <div class=movieList1>
          <el-row>
            <el-col :span="4" class="col3" v-for="movie in movieList1" :key="movie.movie_id">
              <div class="movieCard" @click="toMovieInfo()">
                <el-card :body-style="{ padding: '0px' }">
                  <img :src="movie.movie_pic" />
                  <div class="info">
                    <div class="info1">
                      <i class="el-icon-star-on color1"></i>
                      <span class="info_rate">{{movie.movie_rate}}</span>
                    </div> 
                    <div class="info2">{{movie.movie_name}}</div>  
                  </div>
                </el-card>
              </div>
            </el-col>
          </el-row>
        </div>
      </div>
      <div class="movie2" v-if="seen1">
        <div class="title">---HollyWood Movies of 2018---</div>
        <div class="movieList2">
          <el-row>
            <el-col :span="4" class="col3" v-for="movie in movieList2" :key="movie.movie_id">
              <div class="movieCard"  @click="toMovieInfo()">
                <el-card :body-style="{ padding: '0px' }">
              <img :src="movie.movie_pic"/>
              <div class="info"> 
                <div class="info1">
                  <i class="el-icon-star-on color1"></i>
                  <span class="info_rate">{{movie.movie_rate}}</span>
                </div> 
                <div class="info2">{{movie.movie_name}}</div>  
              </div>
            </el-card>
              </div>
            </el-col>
          </el-row>
        </div>
      </div>
      <div class="searchMovie" v-if="seen2">
        <div class="title1">Find the results for you as follows:</div>
        <div class="searchMovieList">
          <el-row>
            <el-col :span="4" class="col3" v-for="movie in searchMovieList" :key="movie.movie_id">
              <div class="movieCard"  @click="toMovieInfo()">
                <el-card :body-style="{ padding: '0px' }">
              <img :src="movie.movie_pic"/>
              <div class="info">
                <div class="info1">
                  <i class="el-icon-star-on color1"></i>
                  <span class="info_rate">{{movie.movie_rate}}</span>
                </div> 
                <div class="info2">{{movie.movie_name}}</div>  
              </div>
            </el-card>
              </div>
            </el-col>
          </el-row>
        </div>
        <div class="page">
          <el-pagination
            @current-change="handleCurrentChange"
            :current-page="pagenum"
            layout="prev, pager, next"
            :total="80">
          </el-pagination>
        </div>
      </div>
    </div>
</template>

<script>
export default {
  name: 'Home',
  data(){
    return{
      keyword:'',
      seen1:true,
      seen2:false,
      total:6,
      pagenum:1,

      headPictures: [
        { id: 0, source: require('../assets/images/header1.png') },
        { id: 1, source: require('../assets/images/header2.png') },
        { id: 2, source: require('../assets/images/header3.png') },
        { id: 3, source: require('../assets/images/header4.png') },
        { id: 4, source: require('../assets/images/header5.png') }
      ],
      movieList1: [
        {
          movie_id:1,
          movie_pic:require('../assets/images/1.png'),
          movie_name:'The God Father',
          movie_rate:4.8
        },
        {
          movie_id:2,
          movie_pic:require('../assets/images/2.png'),
          movie_name:'Man of Steel',
          movie_rate:4.9
        },
        {
          movie_id:3,
          movie_pic:require('../assets/images/3.png'),
          movie_name:'I am Legend',
          movie_rate:'4.8'
        },
        {
          movie_id:4,
          movie_pic:require('../assets/images/1.png'),
          movie_name:'The God Father',
          movie_rate:4.8
        },
        {
          movie_id:5,
          movie_pic:require('../assets/images/2.png'),
          movie_name:'Man of Steel',
          movie_rate:4.9
        },
        {
          movie_id:6,
          movie_pic:require('../assets/images/3.png'),
          movie_name:'I am Legend',
          movie_rate:'4.8'
        }
      ],
      movieList2: [
        {
          movie_id:1,
          movie_pic:require('../assets/images/1.png'),
          movie_name:'The God Father',
          movie_rate:4.8
        },
        {
          movie_id:2,
          movie_pic:require('../assets/images/2.png'),
          movie_name:'Man of Steel',
          movie_rate:4.9
        },
        {
          movie_id:3,
          movie_pic:require('../assets/images/3.png'),
          movie_name:'I am Legend',
          movie_rate:'4.8'
        },
        {
          movie_id:4,
          movie_pic:require('../assets/images/1.png'),
          movie_name:'The God Father',
          movie_rate:4.8
        },
        {
          movie_id:5,
          movie_pic:require('../assets/images/2.png'),
          movie_name:'Man of Steel',
          movie_rate:4.9
        },
        {
          movie_id:6,
          movie_pic:require('../assets/images/3.png'),
          movie_name:'I am Legend',
          movie_rate:'4.8'
        }
      ],
      searchMovieList:[
         {
          movie_id:1,
          movie_pic:require('../assets/images/1.png'),
          movie_name:'The God Father',
          movie_rate:4.8
        },
        {
          movie_id:2,
          movie_pic:require('../assets/images/2.png'),
          movie_name:'Man of Steel',
          movie_rate:4.9
        },
        {
          movie_id:3,
          movie_pic:require('../assets/images/3.png'),
          movie_name:'I am Legend',
          movie_rate:'4.8'
        },
        {
          movie_id:4,
          movie_pic:require('../assets/images/1.png'),
          movie_name:'The God Father',
          movie_rate:4.8
        },
        {
          movie_id:5,
          movie_pic:require('../assets/images/2.png'),
          movie_name:'Man of Steel',
          movie_rate:4.9
        },
        {
          movie_id:6,
          movie_pic:require('../assets/images/3.png'),
          movie_name:'I am Legend',
          movie_rate:'4.8'
        }
      ]
    }
    
  },
  methods:{
    searchMovies(){
      this.seen1=false;
      this.seen2=true;
    },
    getMovieList(){

    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pagenum=val;
      this.getMovieList();
    },
    toMovieInfo(){
      this.$router.push('/MovieInfo');
    }
  }
}
</script>

<style>
.home{
  padding: 20px;
}
.home .head_pic{
  margin:20px 150px;
}

.home .search{
  margin: 30px 480px;
}

.home .search .el-input{
  font-size: 22px;
}

.home .movie1{
  margin: 50px 35px;
}
.home .movie2{
  margin: 50px 35px;
}
.home .searchMovie{
  margin: 50px 35px;
}
.home .title{
  font-size:25px;
  font-family: Verdana, Geneva, Tahoma, sans-serif;
  text-align: left;
}
.home .title1{
  font-size:18px;
  font-family: Verdana, Geneva, Tahoma, sans-serif;
  text-align: left;
}
.home .col3{
  margin: 30px 20px;
}

.home .movieCard{
  width:250px;
  cursor: pointer;
}

.home .movieList1{
  margin-top:20px;
}
.home .movieList2{
  margin-top:20px;
}
.home .searchMovieList{
  margin-top:20px;
}
.color1{
  color:gold;
}
.home .info{
  margin:10px;
}
.home .info1{
  font-size: 20px;
  text-align: left;
  font-family:'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
}
.home .info_rate{
  margin-left: 10px;
}
.home .info2{
  font-size: 22px;
  text-align: left;
  margin-top:10px ;
  font-family:'Times New Roman', Times, serif;
}

.home .page{
  margin: 30px 0px;
}
.home .page .el-pagination button{
    background-color:  #ebeef0;
    font-size: 18px;
}
.home .page .el-pagination .el-pager li{
  font-size: 18px;
  font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
  background-color:  #ebeef0;
}
.home .page .el-pagination .el-pager li.active{
  color: #0084ff;
}
.home .page .el-pagination .el-pager li:hover{
  color: #6db9ff;
}
</style>