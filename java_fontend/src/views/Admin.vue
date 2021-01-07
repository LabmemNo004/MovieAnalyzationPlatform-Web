<template>
    <div class="admin">
        <div class="title">Administrator</div>
        <div class="line"></div>
        <div class="head"><!--头像、昵称区域-->
          <div class="l">
            <img :src="getAvatar()" class="ava_pic" @click="toPersonalInfo()"/>
          </div>
          <div class="l top2">
            <span class="username">{{this.$store.state.username}}</span>
          </div>
          <div class="clear"></div>
        </div>
        <div class="upload">
            <div class="title1">Upload<i class="el-icon-upload"/></div>
            <div class="uploadButton">
                <div class="uploadForm1">
                    <div class="title2">Edit Movie Information</div>
                    <div class="picture l">
                        <div class="label1">Poster</div>
                        <el-upload
                            class="avatar-uploader"
                            action
                            :on-change="uploadMovie"
                            :auto-upload="false"
                            :before-upload="beforeAvatarUpload">
                            <img v-if="imageUrl1" :src="imageUrl1" class="avatar">
                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                        </el-upload>
                    </div>
                    <div class="info l">
                        <el-form :model="movieForm" label-width="120px" label-position="left">
                            <el-form-item label="Movie Name">
                                <el-input v-model="movieForm.movie_name" style="width:450px" placeholder="Title"></el-input>
                            </el-form-item>
                            <el-form-item label="Director">
                                <el-input v-model="movieForm.director" style="width:450px" placeholder="Director of Movie"></el-input>
                            </el-form-item>
                            <el-form-item label="Type">
                                <el-select v-model="movieForm.type" placeholder="To Select" style="width:450px">
                                    <el-option
                                    v-for="item in options1"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item label="Area">
                                <el-input v-model="movieForm.area" style="width:450px" placeholder="Nation and Area"></el-input>
                            </el-form-item>
                            <el-form-item label="Publish Time">
                                <el-date-picker
                                    v-model="movieForm.time"
                                    type="date"
                                    placeholder="Select Date">
                                </el-date-picker>
                            </el-form-item>
                            <el-form-item label="Duration">
                                <el-input v-model="movieForm.duration" style="width:450px" placeholder="Minutes"></el-input>
                            </el-form-item>
                            <el-form-item label="Introduction">
                                <el-input type="textarea" v-model="movieForm.introduction" :rows="5" maxlength="500" show-word-limit></el-input>
                            </el-form-item>
                        </el-form>
                    </div>
                    <div class="clear"></div>
                    <div class=submitButton1>
                          <el-button type="success" @click="submit1()">Submit</el-button>
                    </div>
                </div>
                <div class="uploadForm2">
                    <div class="title2">Edit Relative Person Information</div>
                    <div class="picture l">
                        <div class="label1">Picture</div>
                        <el-upload
                            class="avatar-uploader"
                            action
                            :auto-upload="false"
                            :on-change="uploadPicture"
                            :before-upload="beforeAvatarUpload">
                            <img v-if="imageUrl2" :src="imageUrl2" class="avatar">
                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                        </el-upload>
                    </div>
                    <div class="info l">
                        <el-form :model="personForm" label-width="130px" label-position="left">
                            <el-form-item label="Person Name">
                                <el-input v-model="personForm.person_name" style="width:450px" placeholder="Name"></el-input>
                            </el-form-item>
                            <el-form-item label="Gender">
                                <el-radio v-model="personForm.sex" label="0">male</el-radio>
                                <el-radio v-model="personForm.sex" label="1">female</el-radio>
                            </el-form-item>
                            <el-form-item label="Birthday">
                                <el-date-picker
                                    v-model="personForm.birthday"
                                    type="date"
                                    placeholder="Select Date">
                                </el-date-picker>
                            </el-form-item>
                            <el-form-item label="Area">
                                <el-input v-model="personForm.area" style="width:450px" placeholder="Nation and Area"></el-input>
                            </el-form-item>
                           
                            <el-form-item label="Profession">
                                <el-select v-model="personForm.profession" placeholder="To Select" style="width:450px">
                                    <el-option
                                    v-for="item in options2"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item label="Introduction">
                                <el-input type="textarea" v-model="personForm.introduction" :rows="5" maxlength="500" show-word-limit></el-input>
                            </el-form-item>
                        </el-form>
                    </div>
                    <div class="clear"></div>
                    <div class=submitButton2>
                          <el-button type="success" @click="submit2()">Submit</el-button>
                    </div>
                </div>
                
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";
const dateFormat1=function(t){
    let year=new Date(t).getFullYear();
    let month=new Date(t).getMonth() + 1 < 10? "0" + (new Date(t).getMonth() + 1): new Date(t).getMonth() + 1;
    let date=new Date(t).getDate() < 10? "0" + new Date(t).getDate(): new Date(t).getDate();
    return year+"-"+month+"-"+date;
}
export default {
    data(){
        return{
            avatar:require('../assets/images/avatar0.jpg'),
            username:'nianwuluo',
            imageUrl1:'',
            imageUrl2:'',
            movieUpload:true,
            personUpload:false,
            movie_pic:'',
            person_pic:'',
            movieForm:{
                movie_name:'',
                director:'',
                type:'',
                area:'',
                time:'',
                duration:'',
                introduction:''

            },
            personForm:{
                person_name:'',
                sex:'',
                birthday:'',
                area:'',
                profession:'',
                movies:'',
                introduction:''

            },
            options1:[
                {
                    value:'动作片',
                    label:'动作片'
                },
                {
                    value:'剧情',
                    label:'剧情'
                },
                {
                    value:'犯罪',
                    label:'犯罪'
                },
                {
                    value:'喜剧',
                    label:'喜剧'
                },
                {
                    value:'科幻片',
                    label:'科幻片'
                }
            ],
            options2:[
                {
                    value:'演员',
                    label:'演员'
                },
                {
                    value:'导演',
                    label:'导演'
                },
            ]

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
        movie(){
            if(this.movieUpload==true){
                this.movieUpload=false;
            }
            else{
                this.movieUpload=true;
            }
        },
        person(){
            if(this.personUpload==true){
                this.personUpload=false;
            }
            else{
                this.personUpload=true;
            }
        },
        getAvatar(){
            var url=this.$store.state.avatar;
            if(url==null||url==''){
                return require('../assets/images/avatar.png');
            }
            return require('../assets/images/'+url);
        },
        uploadMovie(file){
           console.log(file);
           this.movie_pic=file.raw;
           this.imageUrl1 = URL.createObjectURL(file.raw);
        },
        uploadPicture(file){
           console.log(file);
           this.person_pic=file.raw;
           this.imageUrl2 = URL.createObjectURL(file.raw);
        },
        async submit1(){
            let fd = new FormData();
            fd.append('movie_pic',this.movie_pic);
            fd.append('movie_name',this.movieForm.movie_name);
            fd.append('type',this.movieForm.type);
            fd.append('area',this.movieForm.area);
            fd.append('directors',this.movieForm.director);
            fd.append('duration',this.movieForm.duration);
            fd.append('publish_time',dateFormat1(this.movieForm.time));
            fd.append('Introduction',this.movieForm.introduction);
            axios.post("http://localhost:8070/Admin/UploadMovie",fd).then((response)=>{
                console.log(response);
                this.$message.success("Upload Movie Success!");
            }).catch((error)=>{
                this.$message.error("Upload Movie Failed!");
            })
        },
        async submit2(){
            let fd = new FormData();
            fd.append('')

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
    margin:40px 20px;
    padding-bottom:30px;
    border-radius: 20px;
    background-color: #f8f8f8;
}

.admin .uploadForm2{
    margin:40px 20px;
    padding-bottom:30px;
    border-radius: 20px;
    background-color: #f8f8f8;
}

.admin .picture{
    padding:30px 30px 0px 50px;
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
    width: 200px;
    height: 280px;
    line-height: 280px;
    text-align: center;
  }
.admin .avatar {
    width: 200px;
    height: 280px;
    display: block;
}

.admin .label1{
    font-size: 22px;
    font-family: Arial, Helvetica, sans-serif;
    margin:0px 0px 10px 70px;
}

.admin .info{
    margin:15px 20px 20px 40px;
}

.admin .el-form-item__label{
    font-size: 22ps;
    font-family: Arial, Helvetica, sans-serif;
}

.admin .submitButton1{
    margin:10px;
    text-align: center;
}
.admin .submitButton2{
    margin:10px;
    text-align: center;
}

.admin .el-radio__label{
    font-size: 20px;
    font-family: 'Times New Roman', Times, serif;
}

.admin .el-radio__inner{
    width:20px;
    height:20px;
}
</style>