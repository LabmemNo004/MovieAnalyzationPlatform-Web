<template>
  <div class="login">
      <div class="login_box">
        <div class="box_header">
         <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" background-color="#fffed9"
           text-color="#e3e3df" active-text-color="#ffa801" :router="useRouter">
           <el-menu-item index="Login" class="menu_item1">登录</el-menu-item>
           <el-menu-item index="Register" class="menu_item2">注册</el-menu-item>
          </el-menu>
        </div>
        <div class="box_info">
          <el-form label-width="70px" :model="LoginForm" :label-position="labelPosition" :rules="LoginFormRules">
            <el-form-item prop="phone" label="手机" class="form_item1">
              <el-input prefix-icon="el-icon-mobile-phone" v-model="LoginForm.phone" placeholder="请输入注册手机号" style="width:350px"  clearable></el-input>
            </el-form-item>
            <el-form-item prop="password" label="密码" class="form_item2">
              <el-input prefix-icon="el-icon-unlock" v-model="LoginForm.password" placeholder="请输入密码" type="password" style="width:350px"  clearable></el-input>
            </el-form-item>
            <el-button type="primary" class="login_button" @click="Login()">登录</el-button>
            </el-form>
        </div>
      </div>
    
  </div>

</template>

<script>
const phoneValidate=function(rule, value, callback){
        var regPhone = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
        if(value!=''&&!regPhone.test(value)){
          callback(new Error('手机号格式不正确'));
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
      LoginForm: {
        phone: '',
        password: ''
      },
      LoginFormRules: {
        phone: [{ required: true, message: '请输入手机号', trigger: 'blur' },
          {required: true, validator:phoneValidate, trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 10, message: '密码长度为 6 到 10 个字符', trigger: 'blur' }]
      }
    }
  },
  methods:{
    async Login(){
      this.$router.push("/Home");

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

.box_info input{
  letter-spacing: 2px;
}
.box_info label{
  font-size:17px;
  letter-spacing: 3px;
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
  letter-spacing: 10px;
  padding: 0px 77px;
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
  letter-spacing: 50px;
  margin: 10px 0px;
  padding: 0 0 0 40px;
  background-color: #fde06a;
  border-color: #ffba00;
  border-width: 2px;
  border-radius: 10px;
}

.box_info .login_button:hover{
  background-color: #ffa200;
  border-color: #ff8400;
}

</style>