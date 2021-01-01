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
      <el-col :span="2"></el-col>
      <!--left box start-->
      <el-col :span="14">
        <div class="movie_left_con">
          <div class="movie_main_left">
            <!--movie_sort begin-->
            <div class="movie_sort">
              <div class="mv_box">
                <div class="mvb_title" style="color:black">format</div>
                <div class="mvb_main">
                  <a href="#" class=" movie_list_load blue movie_list_load">All</a>
                  <a href="#" class=" movie_list_load  movie_list_load">Movies</a>
                  <a href="#" class=" movie_list_load  movie_list_load">Videos</a>
                  <a href="#" class=" movie_list_load  movie_list_load">TV Movie</a>
                  <a href="#" class=" movie_list_load  movie_list_load">TV Episode</a>
                  <a href="#" class=" movie_list_load  movie_list_load">TV Programs</a>
                  <a href="#" class=" movie_list_load  movie_list_load">Mini movie</a>
                  <a href="#" class=" movie_list_load  movie_list_load">Shorts</a>
                  <a href="#" class=" movie_list_load  movie_list_load">Documentary</a>
                  <a href="#" class=" movie_list_load  movie_list_load">Video Games</a>
                </div>
              </div>
              <div class="mv_box">
                <div class="mvb_title" style="color:black">type</div>
                <div class="mvb_main" id="genre_list">
                  <a href="#" class=" movie_list_load blue movie_list_load">All</a>
                  <a href="#" class=" movie_list_load  movie_list_load">Action</a>
                  <a href="#" class=" movie_list_load  movie_list_load">Adventure</a>
                  <a href="#" class=" movie_list_load  movie_list_load">Animation</a>
                  <a href="#" class=" movie_list_load  movie_list_load">Biography</a>
                  <a href="#" class=" movie_list_load  movie_list_load">Crime</a>
                  <a href="#" class=" movie_list_load  movie_list_load">Comedy</a>
                  <a href="#" class=" movie_list_load  movie_list_load">Documentary</a>
                  <a href="#" class=" movie_list_load  movie_list_load">Storyline</a>
                  <a href="#" class=" movie_list_load  movie_list_load">Family</a>
                  <a href="#" class=" movie_list_load  movie_list_load">Fantasy</a>
                  <a href="#" class=" movie_list_load  movie_list_load">Black</a>
                  <a href="#" class=" movie_list_load  movie_list_load">History</a>
                  <a href="#" class=" movie_list_load  movie_list_load">Ancient</a>
                  <a href="#" class=" movie_list_load  movie_list_load">Terror</a>
                  <a href="#" class=" movie_list_load  movie_list_load">Musicals</a>
                  <a href="#" class=" movie_list_load  movie_list_load">Suspense</a>
                  <a href="#" class=" movie_list_load  movie_list_load">Love</a>
                  <a href="#" class=" movie_list_load  movie_list_load">Scentific</a>
                  <a href="#" class=" movie_list_load  movie_list_load">Shorts</a>
                  <a href="#" class=" movie_list_load  movie_list_load">Sports</a>
                  <a href="#" class=" movie_list_load  movie_list_load">Heros</a>
                  <a href="#" class=" movie_list_load  movie_list_load">Thrill</a>
                  <a href="#" class=" movie_list_load  movie_list_load">War</a>
                  <a href="#" class=" movie_list_load  movie_list_load">Western</a>
                  <a href="#" class=" movie_list_load  movie_list_load">Talk Show</a>
                  <a href="#" class=" movie_list_load  movie_list_load">News</a>
                  <a href="#" class=" movie_list_load  movie_list_load">Games</a>
                  <a href="#" class=" movie_list_load  movie_list_load">Reality Show</a>
                  <a href="#" class=" movie_list_load  movie_list_load">Homosexuality</a>
                </div>
              </div>
            </div>
            <!--movie_sort end-->

            <!--movie_screen begin-->
            <div class="movie_screen">
              <!--排序依据begin-->
              <div class="movie_screen_hd"></div>
              <div class="movie_order">
                <span>sorted by:</span>
                <span class="movie_order_type">
                  <a class="blue movie_list_load" href="#">hot</a>
                  <a class="movie_list_load" href="#">points</a>
                  <a class="movie_list_load" href="#">time</a>
                </span>
              </div>
              <!--排序依据end-->

              <div class="movie1" v-if="seen1">
                <div class="title">---HollyWood Movies of 2019---</div>
                <div class=movieList1>
                  <el-row>
                    <el-col :span="6" class="col3" v-for="movie in movieList1" :key="movie.movie_id">
                      <div class="movieCard">
                        <el-card :body-style="{ padding: '0px' }">
                          <img :src="movie.movie_pic"/>
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
              </div>
              <div class="movie2" v-if="seen1">
                <div class="title">---HollyWood Movies of 2018---</div>
                <div class="movieList2">
                  <el-row>
                    <el-col :span="6" class="col3" v-for="movie in movieList2" :key="movie.movie_id">
                      <div class="movieCard">
                        <el-card :body-style="{ padding: '0px' }">
                          <img :src="movie.movie_pic"/>
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
              </div>
              <div class="searchMovie" v-if="seen2">
                <div class="title1">Find the results for you as follows:</div>
                <div class="searchMovieList">
                  <el-row>
                    <el-col :span="4" class="col3" v-for="movie in searchMovieList" :key="movie.movie_id">
                      <div class="movieCard">
                        <el-card :body-style="{ padding: '0px' }">
                          <img :src="movie.movie_pic"/>
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
                      @current-change="handleCurrentChange"
                      :current-page="pagenum"
                      layout="prev, pager, next"
                      :total="80">
                  </el-pagination>
                </div>
              </div>
            </div>
            <!--movie_screen end-->
          </div>
        </div>
      </el-col>
      <!--left box end-->
      <!--right box start-->
      <el-col :span="6">
        <div class="movie_main_right">
          <div class="mvr_keywords">
            <div class="mvr_keywords_age">
              <h3>Ages</h3>
              <div class="vrk_list2 right_tag">
                <a href="#" class="blue movie_list_load keyword_ages_list">all</a>
                <a href="#" class=" movie_list_load keyword_ages_list">2020</a>
                <a href="#" class=" movie_list_load keyword_ages_list">2019</a>
                <a href="#" class=" movie_list_load keyword_ages_list">2010s</a>
                <a href="#" class=" movie_list_load keyword_ages_list">2000s</a>
                <a href="#" class=" movie_list_load keyword_ages_list">1990s</a>
                <a href="#" class=" movie_list_load keyword_ages_list">1980s</a>
                <a href="#" class=" movie_list_load keyword_ages_list">1970s</a>
                <a href="#" class=" movie_list_load keyword_ages_list">1960s</a>
                <a href="#" class=" movie_list_load keyword_ages_list">earlier</a>
              </div>
            </div>
          </div>
        </div>
      </el-col>
      <!--right box end-->
      <el-col :span="2"></el-col>
    </el-row>
    <!--主线部分end-->
  </div>
</template>
<script>
export default {
  name: 'Movies',
  data() {
    return {
      keyword: '',
      seen1: true,
      seen2: false,
      total: 6,
      pagenum: 1,

      headPictures: [
        {id: 0, source: require('../assets/images/header1.png')},
        {id: 1, source: require('../assets/images/header2.png')},
        {id: 2, source: require('../assets/images/header3.png')},
        {id: 3, source: require('../assets/images/header4.png')},
        {id: 4, source: require('../assets/images/header5.png')}
      ],
      movieList1: [
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
      ],
      movieList2: [
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
      ],
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
  methods: {
    searchMovies() {
      this.seen1 = false;
      this.seen2 = true;
    },
    getMovieList() {

    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pagenum = val;
      this.getMovieList();
    },
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


.movies .movieCard {
  width: 170px;
}

.movieCard .el-card__body>img{
  width:100%;
  height: auto;
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
  font-size: 20px;
  text-align: left;
  font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
}

.movies .info_rate {
  margin-left: 10px;
}

.movies .info2 {
  font-size: 22px;
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

#app > div > section > main > div > div.el-row > div.el-col.el-col-14 > div {
  background-color: #ffffff;
}

#app > div > section > main > div > div.el-row > div.el-col.el-col-14 > div > div {
  padding: 30px 20px;
}

.movie_sort {
  font-family: Microsoft YaHei, helvetica, arial;
  color: #222222;
  list-style: none outside none;
  margin: 0;
  padding: 0;
  font-size: 14px;
}

.mv_box {
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

a.movie_list_load{
  list-style: none outside none;
  font-size: 14px;
  padding: 0;
  font-family: Microsoft YaHei;
  cursor: pointer;
  text-decoration: none;
  display: inline-block;
  margin: 0 20px 10px 0;
  color: #000000;
}

#genre_list a {
  min-width: 120px;
  text-align: left;
}


div.movie_screen_hd {
  font-family: Microsoft YaHei, helvetica, arial;
  font-size: 14px;
  color: #222222;
  list-style: none outside none;
  margin: 0;
  padding: 0 0 10px;
  border-bottom: 1px solid #d7d7d7;
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

div.movie_order > span{
  list-style: none outside none;
  display: block;
  float: left;
  padding-right: 16px;
  overflow: hidden;
  margin: 10px 0px;
}

.movie_order_type{
  margin: 10px 20px;
}

a.movie_list_load{
  -webkit-font-smoothing: antialiased;
  text-align: left;
  list-style: none outside none;
  font-size: 16px;
}

#app > div > section > main > div > div.el-row > div.el-col.el-col-2{
  min-height: 100px;
  /*background-color: black;*/
}

#app > div > section > main > div > div.el-row > div.el-col.el-col-6{
  height: 100px;
  /*background-color: royalblue;*/
}

.movie_main_right{
  margin: 0px 10px;
  background-color: #ffffff;
  min-height: 50px;
  padding: 20px 20px;
}

.mvr_keywords{
  text-align: left;
}

.mvr_keywords_age{
  /*margin:30px 0px 0px 0px;*/
  background-color: #ebeef0;
  min-height: 10px;
  padding: 5px 10px;
}

.keyword_ages_list{
  width: 75px;
}

</style>
