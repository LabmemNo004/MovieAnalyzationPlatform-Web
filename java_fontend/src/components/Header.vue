<template>
    <div class="header">
        <el-row class="r">
            <el-col :span="3" class="col1">Movies</el-col>
            <el-col :span="6.5" :offset="6">
                <el-menu class="el-menu-demo" mode="horizontal" background-color="#0d1323" text-color="#f4f5f8" active-text-color="#ffd200" router>
                    <el-menu-item index="/Home">Home</el-menu-item>
                    <el-menu-item index="/Movies">Movies</el-menu-item>
                    <el-menu-item index="/People">People</el-menu-item>
                    <el-menu-item index="/Rank">Rank</el-menu-item>
                </el-menu>
            </el-col>
            <el-col :span="3.5" :offset="5" class="col2">
                <el-menu class="el-menu-demo" mode="horizontal" background-color="#0d1323" text-color="#f4f5f8" active-text-color="#ffd200" router>
                    <el-submenu index="2">
                        <template slot="title">
                            <img class="avatar" src="../assets/images/avatar0.jpg"/><!--此处为头像-->
                            <span class="name">nianwuluo</span><!--此处填入用户名-->
                        </template>
                        <el-menu-item index="PersonalHome">Personal Home</el-menu-item>
                        <el-menu-item index="PersonalInfo">Personal Information</el-menu-item>
                        <el-menu-item index="2-3">Log Out</el-menu-item>
                </el-submenu>
                </el-menu>
                
            </el-col>
        
        </el-row>

    </div>
</template>

<script>
  export default {
    data() {
      return {
      };
    },
    methods: {
    }
  }
</script> 

<style>
.header .r{
    margin:0px 20px;
    text-align: left;
}
.header .col1{
    font-size:50px;
    font-family: 'Times New Roman', Times, serif;
}
.header .col2{
    text-align: center;
}
.header .el-menu-demo li{
    font-size:20px;
    font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
}
.header .avatar{
    width:36px;
    height:36px;
    border-radius: 18px;
}
.header .name{
    margin-left:10px ;
    font-size:16px;
}

</style>