<template>
  <div class="peoplecollection">
    <div class="collection_container">
      <el-row>
        <el-col span="2"></el-col>
        <el-col span="14">
          <div class="cl_op">
            <span class="sonListTitle_l">Collection({{personList.length}})</span>

          </div>
<!--          <div class="listinfo">-->
<!--            <div class="list_title clearfix">-->
<!--              <div class="list_title_tab">-->
<!--                <a href="#" :class="{'tab_active':seen_person&seen_movie}" @click="click_all()">All</a>-->
<!--                <a href="#" :class="{'tab_active':!seen_person&seen_movie}" @click="click_movie">Movie</a>-->
<!--                <a href="#" :class="{'tab_active':seen_person&!seen_movie}" @click="click_person">Person</a>-->
<!--              </div>-->
<!--            </div>-->
<!--          </div>-->
          <div class="collection_con">
            <div class="ps_cl">

              <div class="ps_info">
                <el-row>
                  <el-col :span="6" class="ps_cl_list col3"  v-for="person in personList" :key="person.person_id">
                    <div class="mv_cl_card">
                      <el-card :body-style="{ padding: '0px' }">
                        <img :src="person.person_pic"/>
                        <div class="info">
                          <div class="info2">{{ person.person_name }}</div>
                        </div>
                      </el-card>
                    </div>
                  </el-col>
                </el-row>
              </div>
              <!--分页start-->
              <el-pagination
                  background
                  layout="prev, pager, next"
                  :total="people_collect_num">
              </el-pagination>
              <!--分页end-->
            </div>
          </div>
        </el-col>
        <el-col span="6">
          <div class="clr cl_navi">
            <div class="cl_navi_tt">Navigate</div>
            <div class="cl_navi_content">
              <ul>
                <li><i></i><a href="/#/PersonalHome">Personal Home</a></li>
                <li><i></i><a href="/#/PersonalHome">My Comment</a></li>
                <li><i></i><a href="#">History</a></li>
                <li><i></i><a href="/#/PersonalInfo">My Information</a></li>
                <li><i></i><a href="#">My message</a></li>
              </ul>
            </div>
          </div>
          <div class="clr listRight">
            <p>add my favorite movies, persons to the collections.</p>
            <ul>
              <a href="/#/movies/">add new movies >></a>
              <a href="/#/People/">add new persons >></a>
              <a href="javascript:;" id="list_back" onclick="window.history.go(-1)">return >></a>
            </ul>
          </div>
          <div class="clr user0">
            <div class="userOCon">
              <a href="#"><i class="el-icon-message"></i>
                <p class="userOTit">Feedback</p></a>
            </div>
            <div class="userOConLine"></div>
            <div class="userOCon">
              <a href="#"><i class="el-icon-question"></i>
                <p class="userOTit">FAQ</p></a>
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
  name: 'peoplecollection',
  data() {
    return {

      people_collect_num:3,
      people_per_page:10,

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
        }
      ],
    }
  },
  methods:{
    click_all(){
      this.seen_movie=true;
      this.seen_person=true;
    },
    click_movie(){
      this.seen_movie=true;
      this.seen_person=false;
    },
    click_person(){
      this.seen_movie=false;
      this.seen_person=true;
    }
  }
}
</script>

<style>
.collection_container {
  margin: 30px 0px;
  text-align: left;
}

.collection_container>div>div.el-col-14{
  background-color: #ffffff;
}
.cl_op {
  background-color: #ffba00;
  padding:20px 20px;
  min-height: 20px;
}

.sonListTitle_l{
  /*margin: 5px;*/
  float: left;
}

.sonListTitle_r{
  float: right;
}


.sonListTitle_r>i{
  margin: 0px 10px;
}

.listinfo {
  font-size: 16px;
  color: #000000;
  padding: 0 28px;
  /*background-color: coral;*/
}

.listinfo .list_title {
  height: 50px;
  line-height: 50px;
  position: relative;
}

.list_title_tab {
  width: 100%;
  overflow: hidden;
}

.listinfo .list_title .actionsL {
  position: absolute;
  left: 0;
}

.list_title_tab a.tab_active, .list_title_tab a:hover {
  color: #FFAC2D;
  border: 1px solid #FFAC2D;
}

.list_title_tab a {
  border: 1px solid #999999;
  height: 22px;
  line-height: 22px;
  padding: 0 10px;
  border-radius: 10px;
  margin-right: 20px;
  font-size: 14px;
  color: #2c3e50;
  text-decoration: none;
}

.cl_ex_dt{
  padding: 10px 25px;
  margin-top: 30px;
}

.collection_con{
  padding: 0px 20px;
}

.collection_con>div>div>.el-row>.el-col{
  padding:10px;
}

.collection_con>div>div>.el-row>.el-col>div>div>div>img{
  width: 100%;
  height: auto;
}

.clr{
  background-color: #ffffff;
  border-radius: 4px;
  margin: 0 0 20px 20px;
  overflow: hidden;
}

.cl_navi_tt{
  padding: 20px 30px;
  font-size: 25px;
  color: #ffba00;
}

.cl_navi_content{
  margin-left: -10px;
  margin-top: -20px;
  margin-bottom: 20px;
}

.cl_navi>.cl_navi_content>ul{
  list-style-image: none;
  list-style-type: none;
}

.cl_navi>.cl_navi_content>ul>li {
  width: 50%;
  float: left;
  line-height: 38px;
}

.cl_navi>.cl_navi_content>ul>li>i {
  background-color: #FFAC2D;
  border-radius: 50%;
  width: 6px;
  height: 6px;
  margin: 0 5px 2px 0;
  display: inline-block;
}

.cl_navi>.cl_navi_content>ul>li>a {
  color: #000000;
  text-decoration: none;
}

.listRight{
  padding: 10px 20px;
  text-align: left;
}

.listRight>p {
  font-size: 16px;
  color: #000000;
}

.listRight ul {
  margin-top: 10px;
}

.listRight ul a {
  display: block;
  color: #0066c0;
  line-height: 40px;
  text-decoration: none;
}

.userOCon {
  float: left;
  width: 40%;
  text-align: center;
  margin: 0 5%;
}

.userOCon a {
  display: block;
  color: #000000;
  padding: 15px 0;
  text-decoration: none;
}

.userOTit {
  margin: 10px 0 0 0;
}

.peoplecollection .el-col-2{
  min-height: 100px;
}

.peoplecollection .el-pagination{
  text-align: center;
  margin: 50px 0px;
}

.peoplecollection .el-pagination.is-background .el-pager li:not(.disabled).active{
  background-color: #0066c0;
  color: white;
}
</style>