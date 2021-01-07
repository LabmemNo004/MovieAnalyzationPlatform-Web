<template>
    <div class="personalinfo">
      <div class="title">Personal Information</div>
      <div class="line"></div>
      <div class="info">
        <el-form ref="form" :model="personalForm" label-width="130px" label-position="right" v-loading="loading">
          <el-form-item label="User Avatar" class="item0">
            <el-upload
              class="avatar-uploader"
              :multiple="false"
              action
              :show-file-list="false"
              :http-request="upload"
              :before-upload="beforeAvatarUpload">
              <img v-if="personalForm.avatar" :src="getAvatar()" class="avatar" @error="def()">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
          <el-form-item label="User ID" class="item1">
            <el-input v-model="personalForm.user_id" disabled></el-input>
          </el-form-item>
          <el-form-item label="User Name" class="item2" >
            <el-input v-model="personalForm.username"></el-input>
          </el-form-item>
          <el-form-item label="Sex" class="item3">
            <el-radio-group v-model="personalForm.sex">
              <el-radio label="男"></el-radio>
              <el-radio label="女"></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="Birthday" class="item2">
            <el-date-picker type="date" placeholder="Select Date" v-model="personalForm.birthday" style="width: 100%;"></el-date-picker>
          </el-form-item>
          <el-form-item label="Phone Number" class="item3">
            <el-input v-model="personalForm.phone" disabled></el-input>
          </el-form-item>
          <el-form-item label="Email Address" class="item4">
            <el-input v-model="personalForm.email"></el-input>
          </el-form-item>
          <el-form-item label="Signature" class="item5">
            <el-input type="textarea" v-model="personalForm.signature" :rows="4" maxlength="120" show-word-limit></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="saveInfo()" round>Save</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
</template>

<script>
import axios from "axios";
import qs from "qs";
const dateFormat=function(t){
    let year=new Date(t).getFullYear();
    let month=new Date(t).getMonth() + 1 < 10? "0" + (new Date(t).getMonth() + 1): new Date(t).getMonth() + 1;
    let date=new Date(t).getDate() < 10? "0" + new Date(t).getDate(): new Date(t).getDate();
    return year+"-"+month+"-"+date;
}

export default {
  name: 'PersonalInfo',
  data(){
    return{
      loading:false,
      defaultImg:require('../assets/images/avatar.png'),
      personalForm:{
         avatar:'',
         user_id:this.$store.state.id,
         username:this.$store.state.username,
         sex:" ",
         birthday:" ",
         phone:" ",
         email:" ",
         signature:" "

      }
    }
    
  },
  created(){
    this.getInfo();
  },
  methods:{
    def(){
           let img = event.srcElement;   
           img.src = this.defaultImg;   
           img.onerror = null; //防止闪图
    },
    async getInfo(){
      this.loading=true;
      axios.post("http://localhost:8070/User/GetInformation?userid="+this.$store.state.id
            ).then((response)=>{
              console.log(response);
              var data=response.data.data;
              this.personalForm.user_id=data.userID;
              this.personalForm.username=data.username;
              this.personalForm.sex=data.sex==0?'男':'女';
              this.personalForm.birthday=data.birthday;
              this.personalForm.phone=data.phone;
              this.personalForm.email=data.email;
              this.personalForm.signature=data.signature;
              if(data.avatar==null||data.avatar==''){
                this.personalForm.avatar=require('../assets/images/avatar.png');
              }
              else{
                this.personalForm.avatar=data.avatar;
              }
              
              this.loading=false;
            }).catch((error)=>{
              this.$message.error("Loading Failed!");
            })
    },
    saveInfo(){
      var gender=this.personalForm.sex=='男'?0:1;
      var birth=dateFormat(this.personalForm.birthday);
      axios.post("http://localhost:8070/User/ModifyInformation?userid="+this.personalForm.user_id+"&username="+this.personalForm.username+"&sex="+gender+"&birthday="+birth+"&phone="+this.personalForm.phone+"&email="+this.personalForm.email+"&signature="+this.personalForm.signature
            ).then((response)=>{
              console.log(response);
              this.$store.state.username=this.personalForm.username;
              sessionStorage.setItem("user", JSON.stringify(this.$store.state));
              this.getInfo();
              this.$message.success("Modification Success!")
            }).catch((error)=>{
              this.$message.error("Modification Failed!");
            });

    },
    upload(obj) {
      console.log(obj);       
      let fd = new FormData();
      fd.append('userid',this.$store.state.id);       
      fd.append('file',obj.file);//传文件 
      axios.post("http://localhost:8070/User/ModifyAvatar",fd).then((response)=>{
        console.log(response);
        this.$message.success("Upload Success!");
      }).catch((error)=>{
        this.$message.error("Upload Failed!");
      })
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isJPG2 = file.type === "image/jpg";
      const isPNG = file.type === "image/png";
      const isLt5M = file.size / 1024 / 1024 < 5;
      if (!isJPG && !isJPG2 && !isPNG) {
        this.$message.error("Only jpg or png!");
      }
      if (!isLt5M) {
        this.$message.warning("请上传5MB以内的图片!");
      }
      return (isJPG || isJPG2 || isPNG) && isLt5M;
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
.personalinfo{
  background-color: #fff;
  border-radius: 20px;
  margin:20px 200px;
  padding: 40px;
}

.personalinfo .title{
  font-size: 30px;
  font-family:Verdana, Geneva, Tahoma, sans-serif;
}

.personalinfo .line{
  /*width:1020px;*/
  height: 2px;
  border-style: dashed none dashed none;
  border-color: #000;
  margin:30px 0px;
}

.personalinfo .info{
  margin-right: 20px;
}

.info .el-form-item__label{
  font-size: 18px;
  font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
}

.info .el-input{
  font-size: 18px;
}

.info .el-input__inner{
  font-family:Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
}

.info .el-textarea{
  font-size: 18px;
}

.info .el-textarea__inner{
  font-family:Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
}
.info .item0{
  width:300px;
  margin-bottom: 40px;
}
.info .item1{
  width: 250px;
  margin-bottom: 40px;
}

.info .item2{
  width: 350px;
  margin-bottom: 40px;
}

.info .item3{
  width:300px;
  margin-bottom: 40px;
}

.info .item4{
  width: 400px;
  margin-bottom: 40px;
}

.info .item5{
  width: 600px;
  margin-bottom: 40px;
}

.info .el-button{
  font-size: 20px;
  font-family:'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif
}

.personalinfo .avatar-uploader .el-upload {
    border: 1px solid #d9d9d9;
    border-radius: 60px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}
.personalinfo .avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.personalinfo .avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 120px;
  height: 120px;
  line-height: 120px;
  text-align: center;
}
.personalinfo .avatar {
  width: 120px;
  height: 120px;
  display: block;
}

</style>