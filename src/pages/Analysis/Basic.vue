<template>
    <div class="tables-basic">
        <Helper @choose="appFilter"/>
        <h2 class="page-title">APP - <span class="fw-semi-bold">{{localParam.organization}}</span></h2>
        <b-row>
            <b-col>
                <Widget v-if="queryChoose.p1">
                    <h6 v-if="localParam.sights.sight0">Before customizing and testing , select a query in the
                        <span class='fw-semi-bold'>right sidebar</span>
                    </h6>
                    <h4 v-if="localParam.sights.sight1" class='fw-semi-bold ' style="margin-left: 8px">Query
                        <h5 style="margin-left: 73px">Query all movie products under the movieName  <span class='fw-semi-bold'>{{localParam.param.title}}</span> , which sort by ReleaseTime.</h5>
                    </h4>
                    <h4 v-if="localParam.sights.sight1 && localParam.sights.sight2" class='fw-semi-bold ' style="margin-left: 8px">Param
                        <b-input v-model="localParam.param.title" style="margin-left: 73px;width:250px"></b-input>
                    </h4>
                    <b-button variant="outline-secondary" text="text" style="float: right" v-if="localParam.sights.sight1" @click="setQuery">{{localParam.queryState}}
                            <b-spinner  small v-if=localParam.sights.state></b-spinner>
                    </b-button>
                </Widget>
                <Widget v-if="queryChoose.p2">
                    <h6 v-if="localParam.sights.sight0">Before customizing and testing , select a query in the
                        <span class='fw-semi-bold'>right sidebar</span>
                    </h6>
                    <h4 v-if="localParam.sights.sight1" class='fw-semi-bold ' style="margin-left: 10px">Query
                        <h5 style="margin-left: 73px">{{localParam.essentials}}</h5>
                    </h4>
                    <b-button variant="outline-secondary" text="text" style="float: right" v-if="localParam.sights.sight1" @click="setQuery">{{localParam.queryState}}
                        <b-spinner  small v-if=localParam.sights.state></b-spinner>
                    </b-button>
                </Widget>




                <Widget v-if="localParam.sights.sight3 ">
                    <div class="d-flex justify-content-between flex-wrap px-4">
                        <h4 class='d-flex align-items-center pb-1 bigStatTitle'>
                            <span :class="`circle bg-${color} mr-sm`" :style="{ fontSize: '6px' }" />
                            Data coming from——<span class='fw-semi-bold'>{{localParam.dataBase}}</span>
                            <span class="fw-normal ml-xs"></span>
                        </h4>
                        <b-dropdown :text="text" variant="white" size="sm">
                            <b-dropdown-item-button @click="setDatabase('Mysql')" >Mysql</b-dropdown-item-button>
                            <b-dropdown-item-button @click="setDatabase('Hive')">Hive</b-dropdown-item-button>
                            <b-dropdown-item-button @click="setDatabase('Neo4j')">Neo4j</b-dropdown-item-button>
                        </b-dropdown>
                    </div>

                    <div class="table-resposive design2" >
                        <table class="table" style="height: 100px ;overflow: scroll">
                            <thead>
                            <tr>
                                <th class="hidden-sm-down">#</th>
                                <th class="hidden-sm-down" v-for="title in this.localParam.title" :key="title">{{title}}</th>
                            </tr>
                            </thead>
                            <tbody>

                            <tr v-if="queryChoose.p1" v-for="row in localParam.infoShown.data" :key="row">
                                <td>"N"</td>
                                <td>{{row.URL}}</td>
                                <td>{{row.ASIN}}</td>
                                <td>{{row.type}}</td>
                                <td>{{row.format}}</td>
                                <td>
                                    <p class="mb-0">
                                        <small>
                                            <span class="fw-semi-bold">Day:</span>
                                            <span class="text-muted">&nbsp; {{row.ReleaseTime.Day}}</span>
                                        </small>
                                    </p>
                                    <p class="mb-0">
                                        <small>
                                            <span class="fw-semi-bold">Month:</span>
                                            <span class="text-muted">{{row.ReleaseTime.Month}}</span>
                                        </small>
                                    </p>
                                    <p class="mb-0">
                                        <small>
                                            <span class="fw-semi-bold">Year:</span>
                                            <span class="text-muted">{{row.ReleaseTime.Year}}</span>
                                        </small>
                                    </p>
                                </td>
                            </tr>

                            <tr v-if="queryChoose.p2" >
                                <td>"N"</td>

                            </tr>

                            </tbody>
                        </table>
                    </div>
                </Widget>
            </b-col>
        </b-row>
    </div>
</template>

<script>

    import Widget from '@/components/Widget/Widget';
    import Sparklines from '../../components/Sparklines/Sparklines'
    import Helper from '@/components/Helper/Helper';
    export default {
        name: 'AnalysisBasic',
        components: { Widget, Sparklines,Helper},
        data() {
            return {
                baseURL:"http://localhost:8091/",
                queryChoose:{
                    p1:true,
                    p2:false,
                    p3:false,
                    p4:false,
                    p5:false,
                    p6:false,
                    p7:false,
                    p8:false,
                    p9:false,
                    p10:false,
                    p11:false,
                    p12:false,
                    p13:false,
                    p14:false,
                    p15:false,
                    p16:false,
                    p17:false,
                    p18:false,
                    p19:false,
                    p20:false,
                    p21:false,
                },
                localParam:{
                    type:"post",
                    organization:"Default",
                    essentials:"Default",
                    pathMysql:"",
                    pathHive:"",
                    pathNeo4j:"",
                    param:null,
                    paramCopy:null,
                    title:["Key1","Key2","Key3"],
                    infoShown:[],
                    infoMysql:[],
                    infoHive:[],
                    infoNeo4j:[],
                    dataBase:"Mysql",
                    queryState:"Query",
                    sights:{
                        state:false,
                        sight0:true,
                        sight1:false,
                        sight2:false,
                        sight3:false,
                    }
                },
                params:{
                    p0:{
                        type:"post",
                        organization:"Default",
                        essentials:"",
                        pathMysql:"",
                        pathHive:"",
                        pathNeo4j:"",
                        param:null,
                        paramCopy:null,
                        title:["Key1","Key2","Key3"],
                        infoShown:null,
                        infoMysql:null,
                        infoHive:null,
                        infoNeo4j:null,
                        dataBase:"Mysql",
                        queryState:"Query",
                        sights:{
                            state:false,
                            sight0:true,
                            sight1:false,
                            sight2:false,
                            sight3:false,
                        }

                    },
                    p1:{
                        type:"post",
                        organization:"Searching By Movie",
                        essentials:"Query all movie products under the movieName ,which sort by ReleaseTime.",
                        pathMysql:"movie/DetailMovie",
                        pathHive:"",
                        pathNeo4j:"",
                        param:{
                            title:"P1"
                        },
                        paramCopy:{
                            title:"P1"
                        },
                        title:["Url","ASIN","Type","Format","ReleaseTime"],
                        infoShown:null,
                        infoMysql:null,
                        infoHive:null,
                        infoNeo4j:null,
                        dataBase:"Mysql",
                        queryState:"Query",
                        sights:{
                            state:false,
                            sight0:false,
                            sight1:true,
                            sight2:true,
                            sight3:false,
                        }

                    },
                    p2:{
                        type:"post",
                        organization:"Searching By Movie",
                        essentials:"Query the number of movie products released each year,which sort by ReleaseNum。",
                        pathMysql:"movie/ListMovie",
                        pathHive:"",
                        pathNeo4j:"",
                        param:null,
                        paramCopy:null,
                        title:["Year","ReleaseNum"],
                        infoShown:null,
                        infoMysql:null,
                        infoHive:null,
                        infoNeo4j:null,
                        dataBase:"Mysql",
                        queryState:"Query",
                        sights:{
                            state:false,
                            sight0:false,
                            sight1:true,
                            sight2:true,
                            sight3:false,
                        }

                    }
                },

            };
        },
        methods: {

            getData(path,param,log){
                this.$http.get(this.baseURL+path ,{params: param,emulateJSON:true,_timeout:3000,
                    onTimeout: () => {
                        alert("Get TimeOut")
                    }})
                    .then(function (response) {
                        console.log(response.data);
                        this.localParam[log]=response.data;
                    }).catch(function () {
                        alert("Get Error")
                });
            },

            postData(path,param,log){
                this.$http.post(this.baseURL+path , param,{emulateJSON:true,_timeout:3000,
                    onTimeout: () => {
                        alert("Post TimeOut")
                    }})
                    .then(function (response) {
                        console.log(response.data);
                        this.localParam[log]=response.data;
                    }).catch(function () {
                        alert("Post Error")
                })
            },

            queryAll(){
                if(this.localParam.type==="post")
                {
                    this.postData(this.localParam.pathMysql,this.localParam.param,"infoMysql");
                    //this.postData(this.localParam.pathHive,this.localParam.param,"infoHive");
                    //this.postData(this.localParam.pathNeo4j,this.localParam.param,"infoNeo4j");
                    //待添加并发请求
                    //this.localParam.infoShown=this.localParam.infoMysql;


                }
                else if(this.localParam.type==="get")
                {
                    this.getData(this.localParam.pathMysql,this.localParam.param,"infoMysql");
                    //this.getData(this.localParam.pathHive,this.localParam.param,"infoHive");
                    //this.getData(this.localParam.pathNeo4j,this.localParam.param,"infoNeo4j");
                    //待添加并发请求
                    this.localParam.infoShown=this.localParam.infoMysql;

                }
            },

            appFilter(num){//选择新的query
                console.log("appFilter"+num)
                this.localParam=this.params[num];//移动local指针
                this.queryChoose["p1"]=false;//设置head显示
                this.queryChoose["p2"]=false;
                this.queryChoose["p3"]=false;
                this.queryChoose["p4"]=false;
                this.queryChoose["p5"]=false;
                this.queryChoose["p6"]=false;
                this.queryChoose["p7"]=false;
                this.queryChoose["p8"]=false;
                this.queryChoose["p9"]=false;
                this.queryChoose["p10"]=false;
                this.queryChoose["p11"]=false;
                this.queryChoose["p12"]=false;
                this.queryChoose["p13"]=false;
                this.queryChoose["p14"]=false;
                this.queryChoose["p15"]=false;
                this.queryChoose["p16"]=false;
                this.queryChoose["p17"]=false;
                this.queryChoose["p18"]=false;
                this.queryChoose["p19"]=false;
                this.queryChoose["p20"]=false;
                this.queryChoose["p21"]=false;
                this.queryChoose[num]=true;
            },
            reset(){ //重置界面
                this.localParam.queryState="Query";
                this.localParam.sights.state=false;
                this.localParam.sights.sight0=false;
                this.localParam.sights.sight1=true;
                this.localParam.sights.sight2=true;
                this.localParam.sights.sight3=false;
                this.localParam.dataBase="Mysql";
                this.localParam.infoHive=[];
                this.localParam.infoMysql=[];
                this.localParam.infoNeo4j=[];
                this.localParam.param=JSON.parse(JSON.stringify(this.localParam.paramCopy));

            },
            setDatabase(str){
                this.localParam.dataBase=str;
                if(str==="Mysql")
                    this.localParam.infoShown=this.localParam.infoMysql;
                else if(str==="Hive")
                    this.localParam.infoShown=this.localParam.infoHive;
                else if(str==="Neo4j")
                    this.localParam.infoShown=this.localParam.infoNeo4j;
                console.log(this.localParam.infoShown)
            },
            setQuery(){ //切换查询状态
                if(this.localParam.queryState==="Query")
                {
                    this.localParam.sights.sight2=false;
                    this.localParam.sights.state=true;
                    this.localParam.queryState="Querying";
                    this.queryAll();
                    setTimeout(this.dataReady,2000);
                }
                else
                {
                    this.localParam.sights.state=true;
                    this.localParam.queryState="";
                    setTimeout(this.reset,1000);
                }
            },

            dataReady(){ //获取到数据后进行渲染
                this.localParam.queryState="Reset";
                this.localParam.sights.state=false;
                this.localParam.sights.sight3=true;
                this.localParam.infoShown=this.localParam.infoMysql;
            },
        },


    };
</script>

<style src="./Basic.scss" lang="scss" scoped />
