<template>
    <div class="main">
        <el-container style="height: 100%">
            <el-header>
            <!-- 导航栏 -->
                <Header></Header>
            </el-header>
            <el-main>
            <!-- 路由跳转 -->
            <router-view></router-view>
            </el-main>
            <el-footer>
                <Footer></Footer>
            </el-footer>
        </el-container>
    </div>
</template>

<script>
import Header from "../components/Header.vue";
import Footer from "../components/Footer.vue"
export default {
  name: "Main",
  components: {
    Header,
    Footer,
  }
};
</script>

<style>
.main .el-header{
    padding:0px;
    margin:0px;
    background-color: #0d1323;
    color:#f4f5f8;
}
.main .el-main{
    padding:0px;
    margin:0px;
    background-color: #ebeef0;
}
.main .el-footer{
    padding:0px;
    margin: 0px;
    background-color:#18202c;
    color: #eaebec;
    text-align: center;
}
</style>

