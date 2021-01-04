<template>
  <div class="register">
     <div class="register_box">
        <div class="box_header">
         <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" background-color="#fffed9"
           text-color="#e3e3df" active-text-color="#ffa801" :router="useRouter">
           <el-menu-item index="Login" class="menu_item1">LOG IN</el-menu-item>
           <el-menu-item index="Register" class="menu_item2">REGISTER</el-menu-item>
          </el-menu>
        </div>
        <div class="box_info">
          <el-form label-width="70px" :model="RegisterForm" :label-position="labelPosition"  :rules="RegisterFormRules" ref="RegisterForm">
            <el-form-item prop="phone" label="Phone" class="form_item3">
              <el-input prefix-icon="el-icon-mobile-phone" v-model="RegisterForm.phone" placeholder="Set registered mobile phone number." style="width:350px"  clearable></el-input>
            </el-form-item>
            <el-form-item prop="username" label="Name" class="form_item4">
              <el-input prefix-icon="el-icon-user" v-model="RegisterForm.username" placeholder="Set registered user name." type="password" style="width:350px"  clearable show-password></el-input>
            </el-form-item>
            <el-form-item prop="password" label="Password" class="form_item4">
              <el-input prefix-icon="el-icon-lock" v-model="RegisterForm.password" placeholder="Set registered password." type="password" style="width:350px"  clearable show-password></el-input>
            </el-form-item>
            <el-button type="primary" class="register_button" @click="Register('RegisterForm')">REGISTER</el-button>
          </el-form>
          <div class="policy">
            <!--<el-checkbox-group>-->
               <el-checkbox v-model="checked" v-bind:class="checked ? 'check_box1' : 'check_box2'">I have read and agree to the privacy policy.</el-checkbox>
            <!--</el-checkbox-group>-->
          </div>
        </div>
      </div>
  </div>

</template>

<script>
const phoneValidate=function(rule, value, callback){
        var regPhone = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
        if(value!=''&&!regPhone.test(value)){
          callback(new Error('Phone number format is not correct'));
        }
        else{
          callback();
        }
}
export default {
  name: 'Register',
  data () {
    return {
      labelPosition: 'right',
      activeIndex: 'Register',
      useRouter: true,
      checked: true,
      validPass:false,
      RegisterForm: {
        phone: '',
        username:'',
        password: ''
      },
      RegisterFormRules: {
        phone: [{ required: true, message: 'Please input phone number.', trigger: 'blur' },
          { required: true,validator:phoneValidate, trigger: 'blur' }],
        username: [{ required: true, message: 'Please input user name.', trigger: 'blur' },
          { min: 3, max: 10, message: 'The length of user name is between 3 and 10 letters.', trigger: 'blur' }],
        password: [{ required: true, message: 'Please input password.', trigger: 'blur' },
          { min: 6, max: 10, message: 'The length of password is between 6 and 10 letters.', trigger: 'blur' }],
        
      }

    }
  },
  methods:{
    async Register(form){
      console.log(this.RegisterForm);
      this.$router.push("/");
    }
  }
}
</script>

<style>
.register {
  background-image: url(../assets/images/login_bg.png);
  position: absolute;
  background-size: cover;
  width: 100%;
  height: 100%;
  top: 0px;
  left:0px;

}

.register_box{
  width: 450px;
  height: 400px;
  position: absolute;
  left:35%;
  background-color: #fffff0;
  border: #d3dce6 solid;
  border-radius:5%;
  margin-top:150px;
}

.box_header{
  font-weight: bold;
}

.box_header .el-menu-demo{
  border-radius: 20px 20px 0 0;
}

.box_header li{
  font-size:25px;
  font-family:'Times New Roman', Times, serif;
  /*letter-spacing: 2px;*/
  padding: 0px 59.5px;
}

.box_header .menu_item1{
  border-radius: 20px 0 0 0;
}

.box_header .menu_item2{
  border-radius: 0 20px 0 0 ;
}

.box_info .register_button{
  width: 300px;
  height: 50px;
  font-size: 22px;
  letter-spacing: 2px;
  margin: 10px 0px;
  /*padding: 0 0 0 20px;*/
  background-color: #fde06a;
  border-color: #ffba00;
  border-width: 2px;
  border-radius: 10px;
  font-family:Georgia, 'Times New Roman', Times, serif;
}

.box_info .register_button:hover{
  background-color: #ffa200;
  border-color: #ff8400;
}

.box_info .form_item3{
  margin: 35px 0px 10px 0px;
  padding: 10px 0px;

}

.box_info .form_item4{
  margin: 10px 0px;
  padding: 10px 0px;

}

.box_info .el-form-item__error{
  padding-left: 15px;
}



.policy .check_box1{
  font-size: 10px;
  font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
  /*letter-spacing: 1.5px;*/

}

.policy .check_box2{
  font-size: 10px;
  /*letter-spacing: 1.5px;*/
  color:#e0d8c6;
  font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;

}

</style>
