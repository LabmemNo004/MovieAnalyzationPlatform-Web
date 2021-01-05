<template>
  <div class="login">
      <div class="login_box">
        <div class="box_header">
         <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" background-color="#fffed9"
           text-color="#e3e3df" active-text-color="#ffa801" :router="useRouter">
           <el-menu-item index="Login" class="menu_item1">LOG IN</el-menu-item>
           <el-menu-item index="Register" class="menu_item2">REGISTER</el-menu-item>
          </el-menu>
        </div>
        <div class="box_info">
          <el-form label-width="70px" :model="LoginForm" :label-position="labelPosition" :rules="LoginFormRules" ref="LoginForm">
            <el-form-item prop="username" label="Name" class="form_item1">
              <el-input prefix-icon="el-icon-mobile-phone" v-model="LoginForm.username" placeholder="Please input registered user name." style="width:350px"  clearable></el-input>
            </el-form-item>
            <el-form-item prop="password" label="Password" class="form_item2">
              <el-input prefix-icon="el-icon-unlock" v-model="LoginForm.password" placeholder="Please input registered password." type="password" style="width:350px"  clearable></el-input>
            </el-form-item>
            <el-button type="primary" class="login_button" @click="Login('LoginForm')">LOG IN</el-button>
            </el-form>
        </div>
      </div>
    
  </div>

</template>

<script>
import axios from "axios";
const phoneValidate=function(rule, value, callback){
        var regPhone = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
        if(value!=''&&!regPhone.test(value)){
          callback(new Error('Phone number format is not correct.'));
        }
        else{
          callback();
        }
}
export default {
  name: 'Login',
  data () {
    return {
      labelPosition: 'right',
      activeIndex: 'Login',
      useRouter: true,
      validPass:false,
      LoginForm: {
        phone: '',
        password: ''
      },
      LoginFormRules: {
        username: [{ required: true, message: 'Please input user name.', trigger: 'blur' },
          { min: 3, max: 10, message: 'The length of user name is between 3 and 10 letters.', trigger: 'blur' }],
        password: [{ required: true, message: 'Please input password.', trigger: 'blur' },
          { min: 6, max: 10, message: 'The length of password is between 6 and 10 letters.', trigger: 'blur' }]
      }
    }
  },
  methods:{
    async Login(form){
      this.$refs[form].validate((valid) => {
          if (valid) {
            this.validPass=true;
          }
          else {
            this.validPass=false;
            this.$message.error('The input data format is incorrect!');
            this.$refs[form].resetFields();
            return false;
          }
      });
      if(this.validPass){
         axios.get("http://localhost:8070/User/login",
              {
                params:{
                  username: this.LoginForm.username,
                  password: this.LoginForm.password
                }
               
              },
              { withCredentials: true }
            ).then((response)=>{
              console.log(response);
              this.$store.commit("Login", response.data.data.role);
              this.$store.commit("Setname",response.data.data.username);
              this.$store.commit("Setid",response.data.data.userID);
              //将vuex里的信息保存到sessionStorage里
              sessionStorage.setItem("user", JSON.stringify(this.$store.state));
              this.$message.success("Login Success!");
              this.$router.push("/Home");
            }).catch((error)=>{
              this.$message.error("The phone number or password is entered incorrectly!");
              this.$refs['LoginForm'].resetFields();
            })
      }


    }
  }
}
</script>

<style>
.login {
  background-image: url(../assets/images/login_bg.png);
  position: absolute;
  background-size: cover;
  width: 100%;
  height: 100%;
  top: 0px;
  left:0px;

}

.login_box{
  width: 450px;
  height: 400px;
  position: absolute;
  left:35%;
  background-color: #fffff0;
  border: #d3dce6 solid;
  border-radius:5%;
  margin-top:150px;
}

.box_info .form_item1{
  margin: 45px 0px 30px 0px;
  padding: 10px 0px;

}

.box_info .form_item2{
  margin: 30px 0px;
  padding: 10px 0px;

}

.box_info label{
  font-size:17px;
  font-family:'Times New Roman', Times, serif;
  /*letter-spacing: 3px;*/
}
.box_header{
  font-weight: bold;
}

.box_header .el-menu-demo{
  border-radius: 20px 20px 0 0;
}

.box_header li{
  font-size:25px;
  font-family: '微雅软黑', Courier, monospace;
  /*letter-spacing: 2px;*/
  padding: 0px 59.5px;
}

.box_header .menu_item1{
  border-radius: 20px 0 0 0;
}

.box_header .menu_item2{
  border-radius: 0 20px 0 0 ;
}

.box_info .login_button{
  width: 300px;
  height: 50px;
  font-size: 22px;
  /*letter-spacing: 50px;*/
  margin: 10px 0px;
  /*padding: 0 0 0 40px;*/
  background-color: #fde06a;
  border-color: #ffba00;
  border-width: 2px;
  border-radius: 10px;
  font-family:Georgia, 'Times New Roman', Times, serif;
}

.box_info .login_button:hover{
  background-color: #ffa200;
  border-color: #ff8400;
}

</style>