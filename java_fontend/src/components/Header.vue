<template>
    <div class="header">
        <el-row class="r">
            <el-col :span="3" class="col1">Movies</el-col>
            <el-col :span="7" :offset="6">
                <el-menu class="el-menu-demo" mode="horizontal" background-color="#0d1323" text-color="#f4f5f8" active-text-color="#ffd200" :default-active="$route.path" router>
                    <el-menu-item index="/Home">Home</el-menu-item>
                    <el-menu-item index="/Movies">Movies</el-menu-item>
                    <el-menu-item index="/People">People</el-menu-item>
                    <el-menu-item index="/Rank">Rank</el-menu-item>
                </el-menu>
            </el-col>
            <el-col :span="4" :offset="4" class="col2">
                <!--<el-menu class="el-menu-demo" mode="horizontal" background-color="#0d1323" text-color="#f4f5f8" active-text-color="#ffd200" router>
                    <el-submenu index="2">
                        <template slot="title">
                            <img class="avatar" src="../assets/images/avatar0.jpg"/>
                            <span class="name">nianwuluo</span>
                        </template>
                        <el-menu-item index="PersonalHome">Personal Home</el-menu-item>
                        <el-menu-item index="PersonalInfo">Personal Information</el-menu-item>
                        <el-menu-item index="2-3">Log Out</el-menu-item>
                </el-submenu>
                </el-menu>-->
                <el-dropdown>
                    <div class="l top1">
                        <img class="avatar" :src="getAvatar()" @error="def()"/>
                    </div>
                    <div class="l top2">
                        <span class="name">{{this.$store.state.username}}
                            <i class="el-icon-arrow-down el-icon--right"></i>
                        </span>
                    </div>
                    <div class="clear"></div>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item>
                            <div @click="toPersonalHome()">Personal Home</div>
                        </el-dropdown-item>
                        <el-dropdown-item>
                            <div @click="toPersonalInfo()">Personal Information</div>
                        </el-dropdown-item>
                        <el-dropdown-item>
                            <div @click="Logout()">Log Out</div>
                        </el-dropdown-item>
                    </el-dropdown-menu>
                    
                </el-dropdown>
            </el-col>
        
        </el-row>

    </div>
</template>

<script>
import axios from "axios";
  export default {
    data() {
      return {
          defaultImg:require('../assets/images/avatar.png'),
      };
    },
    created(){
       this.getInformation();
    },
    methods: {
        async getInformation(){
            axios.post("http://localhost:8070/User/GetInformation?userid="+this.$store.state.id
            ).then((response)=>{
                this.$store.state.avatar=response.data.data.avatar;
                sessionStorage.setItem("user", JSON.stringify(this.$store.state));
            }).catch((error)=>{
                this.$message.error("Get Information Failed!");
            });
        },
        def(){
           let img = event.srcElement;   
           img.src = this.defaultImg;   
           img.onerror = null; //防止闪图
        },
        toPersonalHome(){
            this.$router.push("/PersonalHome");
        },
        toPersonalInfo(){
            this.$router.push("PersonalInfo");
        },
        Logout(){
           this.$store.commit("Logout");
          //将vuex里的信息保存到sessionStorage里
          sessionStorage.setItem("user", {});
          this.$message.success("Exit Success!");
          this.$router.push("/");
        },
        getAvatar(){
            var url=this.$store.state.avatar;
            if(url==null||url==''){
                return require('../assets/images/avatar.png');
            }
            return require('../assets/images/'+url);
        }
        
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
    font-size:18px;
    color: #ffffff;
}
.l{
    float:left;
}
.top1{
    margin-top:10px;
}
.top2{
    margin-top: 20px;
}
.clear{
    clear: both;
}
</style>