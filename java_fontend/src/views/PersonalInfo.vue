<template>
    <div class="personalinfo">
      <div class="title">Personal Information</div>
      <div class="line"></div>
      <div class="info">
        <el-form ref="form" :model="personalForm" label-width="130px" label-position="right" v-loading="loading">
          <el-form-item label="User Avatar" class="item0">
            <el-upload
              class="avatar-uploader"
              action="https://jsonplaceholder.typicode.com/posts/"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload">
              <img v-if="personalForm.avatar" :src="personalForm.avatar" class="avatar">
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
            <el-date-picker type="date" placeholder="选择日期" v-model="personalForm.birthday" style="width: 100%;"></el-date-picker>
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
export default {
  name: 'PersonalInfo',
  data(){
    return{
      loading:false,
      personalForm:{
         avatar:require('../assets/images/avatar0.jpg'),
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
    async getInfo(){
      this.loading=true;
      axios.get("http://localhost:8070/User/GetInformation",
              {
                params:{
                  userid: this.$store.state.id
                }
               
              },
              { withCredentials: true }
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
              this.loading=false;
            }).catch((error)=>{
              this.$message.error("Loading Failed!");
            })
    },
    saveInfo(){
      var gender=this.personalForm.sex=='男'?0:1;
      var data=qs.stringify({
        userid:this.personalForm.user_id,
        username:this.personalForm.username,
        sex:gender,
        birthday:this.personalForm.birthday,
        phone:this.personalForm.phone,
        email:this.personalForm.email,
        signature:this.personalForm.signature
      });
      /*let params = new FormData();
      params.append('userid', this.personalForm.user_id);
      params.append('username', this.personalForm.username);
      params.append('sex',gender);
      params.append('birthday',this.personalForm.birthday);
      params.append('phone',this.personalForm.phone);
      params.append('email',this.personalForm.email);
      params.append('signature',this.personalForm.signature);*/
      axios.post("http://localhost:8070/User/ModifyInformation",data
            /*{
              
              userid:this.personalForm.user_id,
              username:this.personalForm.username,
              sex:gender,
              birthday:this.personalForm.birthday,
              phone:this.personalForm.phone,
              email:this.personalForm.email,
              signature:this.personalForm.signature
              
            }*/
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
    handleAvatarSuccess(res, file) {
        this.personalForm.avatar = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
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