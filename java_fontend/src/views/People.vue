<template>
  <div class="person">
    <div class="star_container">
      <el-row>
        <el-col span="2"></el-col>
        <el-col span="14">
          <div class="starList_con">
            <div class="starList_info" v-for="person in personList" :key="person.person_id" @click="toPeopleInfo()">
              <div class="star_photos">
                <a href="#">
                  <img :src="person.person_pic" :alt="person.person_name"/>
                </a>
              </div>
              <div class="star_content">
                <p class="star_name">Name: <a href="#" class="blue">{{person.person_name}}</a></p>
                <p class="star_profession">Profession:
                  <span>{{person.person_profession.join(" | ")}}</span>
                </p>
                <p class="star_name">Nickname: <a href="#">{{person.person_name}}</a></p>
                <p class="star_name">Star in:
                  <span>{{person.person_movies.join(", ")}}</span>
                </p>
              </div>
              <div class="star_hd"></div>
            </div>
          </div>
        </el-col>
        <el-col span="6">
          <div class="person_main_right">
            <div class="pr_keywords">
              <div class="pr_keywords_profession prk">
                <h3>Profession</h3>
                <div class="prk_list1 right_tag">
                  <a href="#" class="blue person_list_load keyword_profession_list">all</a>
                  <a href="#" class=" person_list_load keyword_profession_list">director</a>
                  <a href="#" class=" person_list_load keyword_profession_list">actor</a>
                  <a href="#" class=" person_list_load keyword_profession_list">singer</a>
                  <a href="#" class=" person_list_load keyword_profession_list">Producer</a>
                  <a href="#" class=" person_list_load keyword_profession_list">Screenwriter</a>
                  <a href="#" class=" person_list_load keyword_profession_list">Editor</a>
                  <a href="#" class=" person_list_load keyword_profession_list">Cinematographer</a>
                  <a href="#" class=" person_list_load keyword_profession_list">Composer</a>
                  <a href="#" class=" person_list_load keyword_profession_list">more...</a>
                </div>
              </div>
              <div class="pr_keywords_gender prk">
                <h3>Gender</h3>
                <div class="prk_list2 right_tag">
                  <a href="#" class="blue person_list_load keyword_gender_list">all</a>
                  <a href="#" class=" person_list_load keyword_gender_list">male</a>
                  <a href="#" class=" person_list_load keyword_gender_list">female</a>
                </div>
              </div>
              <div class="pr_keywords_area prk">
                <h3>Area</h3>
                <div class="prk_list3 right_tag">
                  <a href="#" class="blue person_list_load keyword_area_list">all</a>
                  <a href="#" class=" person_list_load keyword_area_list">China</a>
                  <a href="#" class=" person_list_load keyword_area_list">Korea</a>
                  <a href="#" class=" person_list_load keyword_area_list">Japan</a>
                  <a href="#" class=" person_list_load keyword_area_list">America</a>
                  <a href="#" class=" person_list_load keyword_area_list">Australia</a>
                  <a href="#" class=" person_list_load keyword_area_list">Europe</a>
                  <a href="#" class=" person_list_load keyword_area_list">India</a>
                  <a href="#" class=" person_list_load keyword_area_list">Russia</a>
                  <a href="#" class=" person_list_load keyword_area_list">more...</a>
                </div>
              </div>
              <div class="pr_keywords_age prk">
                <h3>Age</h3>
                <div class="prk_list4 right_tag">
                  <a href="#" class="blue person_list_load keyword_age_list">all</a>
                  <a href="#" class=" person_list_load keyword_age_list">0-8</a>
                  <a href="#" class=" person_list_load keyword_age_list">9-13</a>
                  <a href="#" class=" person_list_load keyword_age_list">14-19</a>
                  <a href="#" class=" person_list_load keyword_age_list">20-34</a>
                  <a href="#" class=" person_list_load keyword_age_list">35-59</a>
                  <a href="#" class=" person_list_load keyword_age_list">60+</a>
                </div>
              </div>
            </div>
          </div>
        </el-col>
        <el-col span="2"></el-col>
      </el-row>
    </div>
  </div>
</template>
<script>
export default {
  name: 'person',
  data() {
    return {
      personList: [
        {
          person_id: 1,
          person_pic: require('../assets/images/person1.jpg'),
          person_name: 'Gal Gadot',
          person_profession:['actor','producer'],
          person_movies:['Batman v Superman: Dawn of Justice','Wonder Woman','Justice League']
        },
        {
          person_id: 2,
          person_pic: require('../assets/images/person2.jpg'),
          person_name: 'Chris Evans',
          person_profession:['actor','producer'],
          person_movies: ['The Avengers','Avengers: Infinity War','Avengers: Age of Ultron']
        },
        {
          person_id: 3,
          person_pic: require('../assets/images/person3.jpg'),
          person_name: 'Angelina Jolie',
          person_profession:['actor','producer'],
          person_movies: ['Mr. & Mrs. Smith','Wanted','Maleficent']
        },
        {
          person_id: 4,
          person_pic: require('../assets/images/person1.jpg'),
          person_name: 'Gal Gadotr',
          person_profession:['actor','producer'],
          person_movies:['Batman v Superman: Dawn of Justice','Wonder Woman','Justice League']
        },
        {
          person_id: 5,
          person_pic: require('../assets/images/person2.jpg'),
          person_name: 'Chris Evans',
          person_profession:['actor','producer'],
          person_movies: ['The Avengers','Avengers: Infinity War','Avengers: Age of Ultron']
        },
        {
          person_id: 6,
          person_pic: require('../assets/images/person3.jpg'),
          person_name: 'Angelina Jolie',
          person_profession:['actor','producer'],
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
.star_container{
  margin:30px 0px;
}

.el-col-2 {
  min-height: 100px;
}

.person .el-col-14 {
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

.starList_info{
  height: 180px;
  margin: 30px 0px;
  border: 3px solid #d7d7d7;
}

.star_photos{
  height: 100%;
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

</style>