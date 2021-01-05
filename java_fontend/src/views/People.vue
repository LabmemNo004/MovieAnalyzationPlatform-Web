<template>
  <div class="person">
    <!--一serch start-->
    <div class="search">
      <el-input placeholder="Input movie's name to search." v-model="keyword" class="input-with-select">
        <el-button slot="append" icon="el-icon-search" @click="searchMovies()"></el-button>
      </el-input>
    </div>
    <!--一serch end-->
    <div class="star_container">
      <el-row>
        <el-col span="3"></el-col>
        <el-col span="18">
          <div class="starList_con">
            <!--movie_sort begin-->
            <div class="person_sort">
              <!--年份分类begin-->
              <div class="pr_box">
                <span id="sorted_type">Profession: </span>
                <el-radio v-model="p_radio" label="1">all</el-radio>
                <el-radio v-model="p_radio" label="2">director</el-radio>
                <el-radio v-model="p_radio" label="3">actor</el-radio>
              </div>
              <!--年份分类end-->
            </div>
            <!--movie_sort end-->

              <div span="12" class="starList_info" v-for="person in personList" :key="person.person_id" @click="toPeopleInfo()">
                <div class="star_cards">
                  <div class="star_photos">
                    <a href="#">
                      <img :src="person.person_pic" :alt="person.person_name"/>
                    </a>
                  </div>
                  <div class="person_info">
                    <p class="blue person_name">{{person.person_name}}</p>
                    <p class="person_profession">{{person.person_profession}}</p>
                    <p class="person_movie">{{person.person_movies.join(' / ')}}</p>
                  </div>

                </div>
              </div>

          </div>
        </el-col>

        <el-col span="3"></el-col>
      </el-row>
    </div>
  </div>
</template>
<script>
export default {
  name: 'person',
  data() {
    return {
      p_radio:'1',
      personList: [
        {
          person_id: 1,
          person_pic: require('../assets/images/person1.jpg'),
          person_name: 'Gal Gadot',
          person_profession:'actor',
          person_movies:['Batman v Superman: Dawn of Justice','Wonder Woman','Justice League']
        },
        {
          person_id: 2,
          person_pic: require('../assets/images/person2.jpg'),
          person_name: 'Chris Evans',
          person_profession:'actor',
          person_movies: ['The Avengers','Avengers: Infinity War','Avengers: Age of Ultron']
        },
        {
          person_id: 3,
          person_pic: require('../assets/images/person3.jpg'),
          person_name: 'Angelina Jolie',
          person_profession:'actor',
          person_movies: ['Mr. & Mrs. Smith','Wanted','Maleficent']
        },
        {
          person_id: 4,
          person_pic: require('../assets/images/person1.jpg'),
          person_name: 'Gal Gadotr',
          person_profession:'actor',
          person_movies:['Batman v Superman: Dawn of Justice','Wonder Woman','Justice League']
        },
        {
          person_id: 5,
          person_pic: require('../assets/images/person2.jpg'),
          person_name: 'Chris Evans',
          person_profession:'actor',
          person_movies: ['The Avengers','Avengers: Infinity War','Avengers: Age of Ultron']
        },
        {
          person_id: 6,
          person_pic: require('../assets/images/person3.jpg'),
          person_name: 'Angelina Jolie',
          person_profession:'actor',
          person_movies: ['Mr. & Mrs. Smith','Wanted','Maleficent']
        }
      ]
    }

  },
  methods:{
    toPeopleInfo(){
      this.$router.push('/PeopleInfo');
    }
  }
}
</script>

<style>
.person_name{
  font-size: 30px;
  font-family: Georgia, 'Times New Roman', Times, serif;
}

.blue{
  color: #0066c0;
}

.person_movie{
  font-size: 18px;
}

.person_info{
  padding: 20px 30px;
  float: left;
}

.person_sort{
  margin: 20px;
}

.person .search {
  margin: 30px 480px;
}

.star_container{
  margin:30px 0px;
  text-align: left;
}

.el-col-3 {
  min-height: 100px;
}

.person .el-col-18 {
  min-height: 100px;
  background-color: #ffffff;
}

.el-col-6 {
  min-height: 100px;
}

.starList_con{
  margin: 20px 20px;
  min-height: 100px;
  /*background-color: aquamarine;*/
}

.person_main_right{
  text-align: left;
  margin: 0px 10px;
  background-color: #ffffff;
  min-height: 50px;
  padding: 20px 20px;
}

.prk{
  background-color: #ebeef0;
  min-height: 10px;
  padding: 5px 10px;
  margin-bottom: 30px;
}

a.person_list_load{
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

.keyword_profession_list{
  width: 100px;
}

.keyword_age_list{
  width: 70px;
}

.keyword_area_list{
  width: 70px;
}

a.person_list_load.keyword_gender_list{
  margin-right: 50px;
}

.star_photos{
  height: 200px;
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
  height: 200px;
  width: auto;
  padding: 20px;
}

</style>