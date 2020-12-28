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
          <el-button slot="append" icon="el-icon-search"></el-button>
        </el-input>
      </div>
      <div class="movie1">
        <div class="title">---HollyWood Movies of 2019---</div>
        <div class=movieList1>
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
      headPictures: [
        { id: 0, source: require('../assets/images/header1.png') },
        { id: 1, source: require('../assets/images/header2.png') },
        { id: 2, source: require('../assets/images/header3.png') },
        { id: 3, source: require('../assets/images/header4.png') },
        { id: 4, source: require('../assets/images/header5.png') }
      ]
    }
    
  }
}
</script>

<style>
.home{
  padding: 20px;
}
.head_pic{
  margin:20px 150px;
}

.search{
  margin: 30px 480px;
}

.search .el-input{
  font-size: 22px;
}

.movie1{
  margin: 50px 35px;
}
.title{
  font-size:25px;
  font-family: Verdana, Geneva, Tahoma, sans-serif;
  text-align: left;

}
</style>