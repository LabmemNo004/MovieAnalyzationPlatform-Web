<template>
    <div class="admin">
        <div class="title">Administrator</div>
        <div class="line"></div>
        <div class="head"><!--头像、昵称区域-->
          <div class="l">
            <img :src="avatar" class="ava_pic" @click="toPersonalInfo()"/>
          </div>
          <div class="l top2">
            <span class="username">{{username}}</span>
          </div>
          <div class="clear"></div>
        </div>
        <div class="upload">
            <div class="title1">Upload<i class="el-icon-upload"/></div>
            <div class="uploadButton">
                <div class="button1">
                    <el-button type="primary" round>Upload New Movie Information</el-button>
                </div>
                <div class="button2">
                    <el-button type="primary" round>Upload New Person Infomation</el-button>
                </div>
                <div class="uploadForm1">
                    <div class="title2">Edit Movie Information</div>
                    <div class="picture l">
                        <div class="label1">Poster</div>
                        <el-upload
                            class="avatar-uploader"
                            action="https://jsonplaceholder.typicode.com/posts/"
                            :show-file-list="false"
                            :on-success="handleAvatarSuccess"
                            :before-upload="beforeAvatarUpload">
                            <img v-if="imageUrl" :src="imageUrl" class="avatar">
                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                        </el-upload>
                    </div>
                    <div class="info l"></div>
                    <div class="clear"></div>
                </div>
                
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data(){
        return{
            avatar:require('../assets/images/avatar0.jpg'),
            username:'nianwuluo',
            imageUrl:''

        }
    },
    methods:{
        toPersonalInfo(){
            this.$router.push('/PersonalInfo');
        },
        handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
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
.admin{
  background-color: #fff;
  border-radius: 20px;
  margin:20px 200px;
  padding: 40px;
}

.admin .title{
    font-size: 40px;
    font-family: 'Times New Roman', Times, serif;
    margin-bottom:30px;
}

.admin .head{
  text-align: left;
  margin-top:40px;
  margin-bottom: 40px;
}

.admin .ava_pic{
  width: 80px;
  height:80px;
  border-radius: 40px;
  cursor: pointer;
}

.admin .username{
  margin: 20px 40px;
  font-size: 25px;
  font-family:Verdana, Geneva, Tahoma, sans-serif;
}

.admin .line{
    height:2px;
    width: 1030px;
    border-style: dashed none dashed none;
}

.admin .upload{
    text-align: left;
}

.admin .title1{
  font-size: 30px;
  font-family:Verdana, Geneva, Tahoma, sans-serif;
}

.admin .title2{
  text-align: center;
  font-size: 25px;
  font-family:Verdana, Geneva, Tahoma, sans-serif;
  padding:40px;
}

.admin .uploadButton{
    margin:30px 0px;
}

.admin .button1{
    margin:20px 0px;
}

.admin .el-button{
   font-size: 20px;
   font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
}

.admin .uploadForm1{
    margin:40px 0px;
    border-radius: 20px;
    background-color: #f8f8f8;
}

.admin .picture{
    padding:30px;
}

.admin .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
.admin .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
.admin .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 150px;
    height: 210px;
    line-height: 210px;
    text-align: center;
  }
.admin .avatar {
    width: 150px;
    height: 210px;
    display: block;
}

.admin .label1{
    font-size: 22px;
    font-family: Arial, Helvetica, sans-serif;
    margin:0px 0px 10px 40px;
}
</style>