<template>
    <div class="tables-basic">
        <Helper @choose="appFilter"/>
        <h2 class="page-title">APP - <span class="fw-semi-bold text-primary" >{{localParam.organization}}</span></h2>
        <b-row>
            <b-col>
                <Widget v-if="queryChoose.p1">
                    <h6 v-if="localParam.sights.sight0">Before customizing and testing , select a query in the
                        <span class='fw-semi-bold'>right sidebar</span>
                    </h6>
                    <h4 v-if="localParam.sights.sight1" class='fw-semi-bold ' style="margin-left: 8px">Query
                        <h5 style="margin-left: 73px">Query all movie products under the movieName  <span class='fw-semi-bold text-primary'>{{localParam.param.title}}</span> .</h5>
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
                <Widget v-if="queryChoose.p3">
                    <h6 v-if="localParam.sights.sight0">Before customizing and testing , select a query in the
                        <span class='fw-semi-bold'>right sidebar</span>
                    </h6>
                    <h4 v-if="localParam.sights.sight1" class='fw-semi-bold ' style="margin-left: 10px">Query
                        <h5 style="margin-left: 73px">Query the basic information of the movie  <span class='fw-semi-bold text-primary'>{{localParam.param.title}}.</span></h5>
                    </h4>
                    <h4 v-if="localParam.sights.sight1 && localParam.sights.sight2" class='fw-semi-bold ' style="margin-left: 8px">Param
                        <b-input v-model="localParam.param.title" style="margin-left: 73px;width:250px"></b-input>
                    </h4>
                    <b-button variant="outline-secondary" text="text" style="float: right" v-if="localParam.sights.sight1" @click="setQuery">{{localParam.queryState}}
                        <b-spinner  small v-if=localParam.sights.state></b-spinner>
                    </b-button>
                </Widget>
                <Widget v-if="queryChoose.p4">
                    <h6 v-if="localParam.sights.sight0">Before customizing and testing , select a query in the
                        <span class='fw-semi-bold'>right sidebar</span>
                    </h6>
                    <h4 v-if="localParam.sights.sight1" class='fw-semi-bold ' style="margin-left: 10px">Query
                        <h5 style="margin-left: 73px">Query the number of movie products released each month in year <span class='fw-semi-bold text-primary'>{{localParam.param.year}}.</span></h5>
                    </h4>
                    <h4 v-if="localParam.sights.sight1 && localParam.sights.sight2" class='fw-semi-bold ' style="margin-left: 8px">Param
                        <b-input v-model="localParam.param.year" style="margin-left: 73px;width:250px"></b-input>
                    </h4>
                    <b-button variant="outline-secondary" text="text" style="float: right" v-if="localParam.sights.sight1" @click="setQuery">{{localParam.queryState}}
                        <b-spinner  small v-if=localParam.sights.state></b-spinner>
                    </b-button>
                </Widget>
                <Widget v-if="queryChoose.p5">
                    <h6 v-if="localParam.sights.sight0">Before customizing and testing , select a query in the
                        <span class='fw-semi-bold'>right sidebar</span>
                    </h6>
                    <h4 v-if="localParam.sights.sight1" class='fw-semi-bold ' style="margin-left: 10px">Query
                        <h5 style="margin-left: 73px">Query the actors of the movie <span class='fw-semi-bold text-primary'>{{localParam.param.title}}.</span></h5>
                    </h4>
                    <h4 v-if="localParam.sights.sight1 && localParam.sights.sight2" class='fw-semi-bold ' style="margin-left: 8px">Param
                        <b-input v-model="localParam.param.title" style="margin-left: 73px;width:250px"></b-input>
                    </h4>
                    <b-button variant="outline-secondary" text="text" style="float: right" v-if="localParam.sights.sight1" @click="setQuery">{{localParam.queryState}}
                        <b-spinner  small v-if=localParam.sights.state></b-spinner>
                    </b-button>
                </Widget>
                <Widget v-if="queryChoose.p6">
                    <h6 v-if="localParam.sights.sight0">Before customizing and testing , select a query in the
                        <span class='fw-semi-bold'>right sidebar</span>
                    </h6>
                    <h4 v-if="localParam.sights.sight1" class='fw-semi-bold ' style="margin-left: 10px">Query
                        <h5 style="margin-left: 73px">Query the directors of the movie <span class='fw-semi-bold text-primary'>{{localParam.param.title}}.</span></h5>
                    </h4>
                    <h4 v-if="localParam.sights.sight1 && localParam.sights.sight2" class='fw-semi-bold ' style="margin-left: 8px">Param
                        <b-input v-model="localParam.param.title" style="margin-left: 73px;width:250px"></b-input>
                    </h4>
                    <b-button variant="outline-secondary" text="text" style="float: right" v-if="localParam.sights.sight1" @click="setQuery">{{localParam.queryState}}
                        <b-spinner  small v-if=localParam.sights.state></b-spinner>
                    </b-button>
                </Widget>
                <Widget v-if="queryChoose.p7">
                    <h6 v-if="localParam.sights.sight0">Before customizing and testing , select a query in the
                        <span class='fw-semi-bold'>right sidebar</span>
                    </h6>
                    <h4 v-if="localParam.sights.sight1" class='fw-semi-bold ' style="margin-left: 10px">Query
                        <h5 style="margin-left: 73px">Query the movies in type <span class='fw-semi-bold text-primary'>{{localParam.param.type}}.</span></h5>
                    </h4>
                    <h4 v-if="localParam.sights.sight1 && localParam.sights.sight2" class='fw-semi-bold ' style="margin-left: 8px">Param
                        <b-input v-model="localParam.param.type" style="margin-left: 73px;width:250px"></b-input>
                    </h4>
                    <b-button variant="outline-secondary" text="text" style="float: right" v-if="localParam.sights.sight1" @click="setQuery">{{localParam.queryState}}
                        <b-spinner  small v-if=localParam.sights.state></b-spinner>
                    </b-button>
                </Widget>
                <Widget v-if="queryChoose.p8">
                    <h6 v-if="localParam.sights.sight0">Before customizing and testing , select a query in the
                        <span class='fw-semi-bold'>right sidebar</span>
                    </h6>
                    <h4 v-if="localParam.sights.sight1" class='fw-semi-bold ' style="margin-left: 10px">Query
                        <h5 style="margin-left: 73px">Query the movies with a score greater than <span class='fw-semi-bold text-primary'>{{localParam.param.scores}}.</span></h5>
                    </h4>
                    <h4 v-if="localParam.sights.sight1 && localParam.sights.sight2" class='fw-semi-bold ' style="margin-left: 8px">Param
                        <b-input v-model="localParam.param.scores" style="margin-left: 73px;width:250px"></b-input>
                    </h4>
                    <b-button variant="outline-secondary" text="text" style="float: right" v-if="localParam.sights.sight1" @click="setQuery">{{localParam.queryState}}
                        <b-spinner  small v-if=localParam.sights.state></b-spinner>
                    </b-button>
                </Widget>
                <Widget v-if="queryChoose.p9">
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
                <Widget v-if="queryChoose.p10">
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
                <Widget v-if="queryChoose.p11">
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
                <Widget v-if="queryChoose.p12">
                    <h6 v-if="localParam.sights.sight0">Before customizing and testing , select a query in the
                        <span class='fw-semi-bold'>right sidebar</span>
                    </h6>
                    <h4 v-if="localParam.sights.sight1" class='fw-semi-bold ' style="margin-left: 10px">Query
                        <h5 style="margin-left: 73px">Query movies  which actor <span class='fw-semi-bold text-primary'>{{localParam.param.name}}</span> act in.</h5>
                    </h4>
                    <h4 v-if="localParam.sights.sight1 && localParam.sights.sight2" class='fw-semi-bold ' style="margin-left: 8px">Param
                        <b-input v-model="localParam.param.name" style="margin-left: 73px;width:250px"></b-input>
                    </h4>
                    <b-button variant="outline-secondary" text="text" style="float: right" v-if="localParam.sights.sight1" @click="setQuery">{{localParam.queryState}}
                        <b-spinner  small v-if=localParam.sights.state></b-spinner>
                    </b-button>
                </Widget>
                <Widget v-if="queryChoose.p13">
                    <h6 v-if="localParam.sights.sight0">Before customizing and testing , select a query in the
                        <span class='fw-semi-bold'>right sidebar</span>
                    </h6>
                    <h4 v-if="localParam.sights.sight1" class='fw-semi-bold ' style="margin-left: 10px">Query
                        <h5 style="margin-left: 73px">Query the actors who have worked with  <span class='fw-semi-bold text-primary'>{{localParam.param.name}}</span> .</h5>
                    </h4>
                    <h4 v-if="localParam.sights.sight1 && localParam.sights.sight2" class='fw-semi-bold ' style="margin-left: 8px">Param
                        <b-input v-model="localParam.param.name" style="margin-left: 73px;width:250px"></b-input>
                    </h4>
                    <b-button variant="outline-secondary" text="text" style="float: right" v-if="localParam.sights.sight1" @click="setQuery">{{localParam.queryState}}
                        <b-spinner  small v-if=localParam.sights.state></b-spinner>
                    </b-button>
                </Widget>
                <Widget v-if="queryChoose.p14">
                    <h6 v-if="localParam.sights.sight0">Before customizing and testing , select a query in the
                        <span class='fw-semi-bold'>right sidebar</span>
                    </h6>
                    <h4 v-if="localParam.sights.sight1" class='fw-semi-bold ' style="margin-left: 10px">Query
                        <h5 style="margin-left: 73px">Query the directors who have worked with  <span class='fw-semi-bold text-primary'>{{localParam.param.name}}</span> .</h5>
                    </h4>
                    <h4 v-if="localParam.sights.sight1 && localParam.sights.sight2" class='fw-semi-bold ' style="margin-left: 8px">Param
                        <b-input v-model="localParam.param.name" style="margin-left: 73px;width:250px"></b-input>
                    </h4>
                    <b-button variant="outline-secondary" text="text" style="float: right" v-if="localParam.sights.sight1" @click="setQuery">{{localParam.queryState}}
                        <b-spinner  small v-if=localParam.sights.state></b-spinner>
                    </b-button>
                </Widget>
                <Widget v-if="queryChoose.p15">
                    <h6 v-if="localParam.sights.sight0">Before customizing and testing , select a query in the
                        <span class='fw-semi-bold'>right sidebar</span>
                    </h6>
                    <h4 v-if="localParam.sights.sight1" class='fw-semi-bold ' style="margin-left: 10px">Query
                        <h5 style="margin-left: 73px">Query the directors and actors who often work together.</h5>
                    </h4>
                    <b-button variant="outline-secondary" text="text" style="float: right" v-if="localParam.sights.sight1" @click="setQuery">{{localParam.queryState}}
                        <b-spinner  small v-if=localParam.sights.state></b-spinner>
                    </b-button>
                </Widget>
                <Widget v-if="queryChoose.p16">
                    <h6 v-if="localParam.sights.sight0">Before customizing and testing , select a query in the
                        <span class='fw-semi-bold'>right sidebar</span>
                    </h6>
                    <h4 v-if="localParam.sights.sight1" class='fw-semi-bold ' style="margin-left: 10px">Query
                        <h5 style="margin-left: 73px">Query the actors who often work together.</h5>
                    </h4>
                    <b-button variant="outline-secondary" text="text" style="float: right" v-if="localParam.sights.sight1" @click="setQuery">{{localParam.queryState}}
                        <b-spinner  small v-if=localParam.sights.state></b-spinner>
                    </b-button>
                </Widget>
                <Widget v-if="queryChoose.p17">
                    <h6 v-if="localParam.sights.sight0">Before customizing and testing , select a query in the
                        <span class='fw-semi-bold'>right sidebar</span>
                    </h6>
                    <h4 v-if="localParam.sights.sight1" class='fw-semi-bold ' style="margin-left: 10px">Query
                        <h5 style="margin-left: 73px">Query the directors who often work together.</h5>
                    </h4>
                    <b-button variant="outline-secondary" text="text" style="float: right" v-if="localParam.sights.sight1" @click="setQuery">{{localParam.queryState}}
                        <b-spinner  small v-if=localParam.sights.state></b-spinner>
                    </b-button>
                </Widget>

                <Widget v-if="queryChoose.p18">
                    <h6 v-if="localParam.sights.sight0">Before customizing and testing , select a query in the
                        <span class='fw-semi-bold'>right sidebar</span>
                    </h6>
                    <h4 v-if="localParam.sights.sight1" class='fw-semi-bold ' style="margin-left: 10px">Query
                        <h5 style="margin-left: 73px">Query movies directed by director <span class='fw-semi-bold text-primary'>{{localParam.param.name}}</span> .</h5>
                    </h4>
                    <h4 v-if="localParam.sights.sight1 && localParam.sights.sight2" class='fw-semi-bold ' style="margin-left: 8px">Param
                        <b-input v-model="localParam.param.name" style="margin-left: 73px;width:250px"></b-input>
                    </h4>
                    <b-button variant="outline-secondary" text="text" style="float: right" v-if="localParam.sights.sight1" @click="setQuery">{{localParam.queryState}}
                        <b-spinner  small v-if=localParam.sights.state></b-spinner>
                    </b-button>
                </Widget>

                <Widget v-if="queryChoose.p19">
                    <h6 v-if="localParam.sights.sight0">Before customizing and testing , select a query in the
                        <span class='fw-semi-bold'>right sidebar</span>
                    </h6>
                    <h4 v-if="localParam.sights.sight1" class='fw-semi-bold ' style="margin-left: 10px">Query
                        <h5 style="margin-left: 73px">Query actors who have act at least <span class='fw-semi-bold text-primary'>{{localParam.param.number}}</span> movies.</h5>
                    </h4>
                    <h4 v-if="localParam.sights.sight1 && localParam.sights.sight2" class='fw-semi-bold ' style="margin-left: 8px">Param
                        <b-input v-model="localParam.param.number" style="margin-left: 73px;width:250px"></b-input>
                    </h4>
                    <b-button variant="outline-secondary" text="text" style="float: right" v-if="localParam.sights.sight1" @click="setQuery">{{localParam.queryState}}
                        <b-spinner  small v-if=localParam.sights.state></b-spinner>
                    </b-button>
                </Widget>

                <Widget v-if="queryChoose.p20">
                    <h6 v-if="localParam.sights.sight0">Before customizing and testing , select a query in the
                        <span class='fw-semi-bold'>right sidebar</span>
                    </h6>
                    <h4 v-if="localParam.sights.sight1" class='fw-semi-bold ' style="margin-left: 10px">Query
                        <h5 style="margin-left: 73px">Query directors who have direct at least <span class='fw-semi-bold text-primary'>{{localParam.param.number}}</span> movies.</h5>
                    </h4>
                    <h4 v-if="localParam.sights.sight1 && localParam.sights.sight2" class='fw-semi-bold ' style="margin-left: 8px">Param
                        <b-input v-model="localParam.param.number" style="margin-left: 73px;width:250px"></b-input>
                    </h4>
                    <b-button variant="outline-secondary" text="text" style="float: right" v-if="localParam.sights.sight1" @click="setQuery">{{localParam.queryState}}
                        <b-spinner  small v-if=localParam.sights.state></b-spinner>
                    </b-button>
                </Widget>

                <Widget v-if="queryChoose.p21">
                    <h6 v-if="localParam.sights.sight0">Before customizing and testing , select a query in the
                        <span class='fw-semi-bold'>right sidebar</span>
                    </h6>
                    <h4 v-if="localParam.sights.sight1" class='fw-semi-bold ' style="margin-left: 10px">Query
                        <h5 style="margin-left: 73px">Query the popular movie types in year <span class='fw-semi-bold text-primary'>{{localParam.param.year}}</span> .</h5>
                    </h4>
                    <h4 v-if="localParam.sights.sight1 && localParam.sights.sight2" class='fw-semi-bold ' style="margin-left: 8px">Param
                        <b-input v-model="localParam.param.year" style="margin-left: 73px;width:250px"></b-input>
                    </h4>
                    <b-button variant="outline-secondary" text="text" style="float: right" v-if="localParam.sights.sight1" @click="setQuery">{{localParam.queryState}}
                        <b-spinner  small v-if=localParam.sights.state></b-spinner>
                    </b-button>
                </Widget>

                <Widget v-if="queryChoose.p22">
                    <h6 v-if="localParam.sights.sight0">Before customizing and testing , select a query in the
                        <span class='fw-semi-bold'>right sidebar</span>
                    </h6>
                    <h4 v-if="localParam.sights.sight1" class='fw-semi-bold ' style="margin-left: 10px">Query
                        <h5 style="margin-left: 73px">Query all <span class='fw-semi-bold text-primary'>{{localParam.param.type}}</span> movies directed by <span class='fw-semi-bold text-primary'>{{localParam.param.name}}</span> in <span class='fw-semi-bold text-primary'>{{localParam.param.year}}</span></h5>
                    </h4>
                    <h4 v-if="localParam.sights.sight1 && localParam.sights.sight2" class='fw-semi-bold ' style="margin-left: 8px">Param
                        <b-input v-model="localParam.param.type" style="margin-left: 73px;width:250px"></b-input>
                        <b-input v-model="localParam.param.name" style="margin-left: 73px;margin-top:30px;width:250px"></b-input>
                        <b-input v-model="localParam.param.year" style="margin-left: 73px;margin-top:30px;width:250px"></b-input>
                    </h4>
                    <b-button variant="outline-secondary" text="text" style="float: right" v-if="localParam.sights.sight1" @click="setQuery">{{localParam.queryState}}
                        <b-spinner  small v-if=localParam.sights.state></b-spinner>
                    </b-button>
                </Widget>




                <Widget v-if="localParam.sights.sight3 ">
                    <div class="d-flex justify-content-between flex-wrap px-4">
                        <h4 class='d-flex align-items-center pb-1 bigStatTitle'>
                            <span :class="`circle bg-${color} mr-sm`" :style="{ fontSize: '6px' }" />
                            Data coming from——<span class='fw-semi-bold text-primary'>{{localParam.dataBase}}</span>

                        </h4>
                        <b-dropdown :text="text" variant="white" size="sm">
                            <b-dropdown-item-button @click="setDatabase('Mysql')" >Mysql</b-dropdown-item-button>
                            <b-dropdown-item-button @click="setDatabase('(HDFS)Hadoop Distributed File System')">HDFS</b-dropdown-item-button>
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

                            <tr v-if="queryChoose.p1" v-for="row in localParam.infoShown.data" :key="row" >
                                <td>{{row.N}}</td>
                                <td><a :href="row.URL" rel="nofollow noopener noreferrer" target="_blank">{{row.URL}}</a></td>
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

                            <tr v-if="queryChoose.p2" v-for="row in localParam.infoShown.data" :key="row">
                                <td>{{row.N}}</td>
                                <td>{{row.Year}}</td>
                                <td>{{row.ReleaseNum}}</td>
                            </tr>

                            <tr v-if="queryChoose.p3" v-for="row in localParam.infoShown.data" :key="row">
                                <td>{{row.N}}</td>
                                <td>{{row.title}}</td>
                                <td>{{row.productNum}}</td>
                                <td>{{row.actorNum}}</td>
                                <td>{{row.directorNum}}</td>
                                <td>{{row.commentNum}}</td>
                                <td>{{row.score}}</td>
                            </tr>

                            <tr v-if="queryChoose.p4" v-for="row in localParam.infoShown.data" :key="row">
                                <td>{{row.N}}</td>
                                <td>{{row.month}}</td>
                                <td>{{row.releaseNum}}</td>
                            </tr>

                            <tr v-if="queryChoose.p5" v-for="row in localParam.infoShown.data" :key="row">
                                <td>{{row.N}}</td>
                                <td>{{row.actor}}</td>
                            </tr>

                            <tr v-if="queryChoose.p6" v-for="row in localParam.infoShown.data" :key="row">
                                <td>{{row.N}}</td>
                                <td>{{row.director}}</td>
                            </tr>

                            <tr v-if="queryChoose.p7" v-for="row in localParam.infoShown.data" :key="row">
                                <td>{{row.N}}</td>
                                <td>{{row.title}}</td>
                                <td>{{row.score}}</td>
                            </tr>

                            <tr v-if="queryChoose.p8" v-for="row in localParam.infoShown.data" :key="row">
                                <td>{{row.N}}</td>
                                <td>{{row.title}}</td>
                                <td>{{row.score}}</td>
                            </tr>

                            <tr v-if="queryChoose.p9" v-for="row in localParam.infoShown.data" :key="row">
                                <td>{{row.N}}</td>
                                <td>{{row.title}}</td>
                                <td>{{row.score}}</td>
                            </tr>

                            <tr v-if="queryChoose.p10" v-for="row in localParam.infoShown.data" :key="row">
                                <td>{{row.N}}</td>
                                <td>{{row.type}}</td>
                                <td>{{row.movieNum}}</td>
                            </tr>

                            <tr v-if="queryChoose.p11" v-for="row in localParam.infoShown.data" :key="row">
                                <td>{{row.N}}</td>
                                <td>{{row.type}}</td>
                                <td>{{row.movieNum}}</td>
                            </tr>

                            <tr v-if="queryChoose.p12" v-for="row in localParam.infoShown.data" :key="row">
                                <td>{{row.N}}</td>
                                <td>{{row.title}}</td>
                                <td>{{row.score}}</td>
                            </tr>

                            <tr v-if="queryChoose.p13" v-for="row in localParam.infoShown.data" :key="row">
                                <td>{{row.N}}</td>
                                <td>{{row.name}}</td>
                                <td>{{row.cooperationNum}}</td>
                            </tr>

                            <tr v-if="queryChoose.p14" v-for="row in localParam.infoShown.data" :key="row">
                                <td>{{row.N}}</td>
                                <td>{{row.name}}</td>
                                <td>{{row.cooperationNum}}</td>
                            </tr>

                            <tr v-if="queryChoose.p15" v-for="row in localParam.infoShown.data" :key="row">
                                <td>{{row.N}}</td>
                                <td>{{row.导演}}</td>
                                <td>{{row.演员}}</td>
                                <td>{{row.合作次数}}</td>
                            </tr>

                            <tr v-if="queryChoose.p16" v-for="row in localParam.infoShown.data" :key="row">
                                <td>{{row.N}}</td>
                                <td>{{row.演员1}}</td>
                                <td>{{row.演员2}}</td>
                                <td>{{row.合作次数}}</td>
                            </tr>

                            <tr v-if="queryChoose.p17" v-for="row in localParam.infoShown.data" :key="row">
                                <td>{{row.N}}</td>
                                <td>{{row.导演1}}</td>
                                <td>{{row.导演2}}</td>
                                <td>{{row.合作次数}}</td>
                            </tr>

                            <tr v-if="queryChoose.p18" v-for="row in localParam.infoShown.data" :key="row">
                                <td>{{row.N}}</td>
                                <td>{{row.名称}}</td>
                                <td>{{row.评分}}</td>
                            </tr>

                            <tr v-if="queryChoose.p19" v-for="row in localParam.infoShown.data" :key="row">
                                <td>{{row.N}}</td>
                                <td>{{row.演员名称}}</td>
                                <td>{{row.参与电影数量}}</td>
                            </tr>

                            <tr v-if="queryChoose.p20" v-for="row in localParam.infoShown.data" :key="row">
                                <td>{{row.N}}</td>
                                <td>{{row.导演名称}}</td>
                                <td>{{row.参与电影数量}}</td>
                            </tr>

                            <tr v-if="queryChoose.p21" v-for="row in localParam.infoShown.data" :key="row">
                                <td>{{row.N}}</td>
                                <td>{{row.电影类别}}</td>
                                <td>{{row.电影数量}}</td>
                            </tr>

                            <tr v-if="queryChoose.p22" v-for="row in localParam.infoShown.data" :key="row">
                                <td>{{row.N}}</td>
                                <td>{{row.电影名称}}</td>
                                <td>{{row.评分}}</td>
                            </tr>



                            </tbody>
                        </table>
                    </div>
                    <h6><span class="fw-normal ml-xs" style="float: right">{{localParam.infoShown.totalNum}} Raws in Total | Cost {{localParam.infoShown.code}} ms</span></h6>
                </Widget>
            </b-col>
        </b-row>
    </div>
</template>

<script>

    import Widget from '@/components/Widget/Widget';
    import Helper from '@/components/Helper/Helper';
    export default {
        name: 'AnalysisBasic',
        components: { Widget,Helper},
        data() {
            return {
                baseURL:"http://localhost",
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
                    p22:false,
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
                    p1:{
                        type:"post",
                        organization:"Searching By Movie",
                        essentials:"Query all movie products under the movieName P1.",
                        pathMysql:":8091/movie/DetailMovieLike",
                        pathHive:":8899/movie/DetailMovie",
                        pathNeo4j:"",
                        param:{
                            title:"Communion"
                        },
                        paramCopy:{
                            title:"Communion"
                        },
                        title:["Url","ASIN","Type","Format","ReleaseTime"],
                        infoShown:[],
                        infoMysql:[],
                        infoHive:[],
                        infoNeo4j:[],
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
                        organization:"Searching By Time",
                        essentials:"Query the number of movie products released each year.",
                        pathMysql:":8091/movie/ListMovie",
                        pathHive:":8899/movie/ListMovie",
                        pathNeo4j:"",
                        param:null,
                        paramCopy:null,
                        title:["Year","ReleaseNum"],
                        infoShown:[],
                        infoMysql:[],
                        infoHive:[],
                        infoNeo4j:[],
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
                    p3:{
                        type:"post",
                        organization:"Searching By Movie",
                        essentials:"Query the basic information of the movie P1",
                        pathMysql:":8091/movie/SimpleMovieLike",
                        pathHive:":8899/movie/SimpleMovie",
                        pathNeo4j:"",
                        param:{
                            title:"Communion"
                        },
                        paramCopy:{
                            title:"Communion"
                        },
                        title:["Title","ProductNum","ActorNum","DirectorNum","CommentNum","Score"],
                        infoShown:[],
                        infoMysql:[],
                        infoHive:[],
                        infoNeo4j:[],
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
                    p4:{
                        type:"post",
                        organization:"Searching By Time",
                        essentials:"Query the number of movie products released each month in ",
                        pathMysql:":8091/movie/SpecificYearMovie",
                        pathHive:":8899/movie/SpecificYearMovie",
                        pathNeo4j:"",
                        param:{
                            year:"2019"
                        },
                        paramCopy:{
                            year:"2019"
                        },
                        title:["Month","ReleaseNum"],
                        infoShown:[],
                        infoMysql:[],
                        infoHive:[],
                        infoNeo4j:[],
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
                    p5:{
                        type:"post",
                        organization:"Searching By Movie",
                        essentials:"Query the actors of the movie P1 ",
                        pathMysql:":8091/movie/getMovieActor",
                        pathHive:":8899/movie/getMovieActor",
                        pathNeo4j:"",
                        param:{
                            title:"Communion"
                        },
                        paramCopy:{
                            title:"Communion"
                        },
                        title:["Actor"],
                        infoShown:[],
                        infoMysql:[],
                        infoHive:[],
                        infoNeo4j:[],
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
                    p6:{
                        type:"post",
                        organization:"Searching By Movie",
                        essentials:"Query the directors of the movie P1 ",
                        pathMysql:":8091/movie/getMovieDirector",
                        pathHive:":8899/movie/getMovieDirector",
                        pathNeo4j:"",
                        param:{
                            title:"Communion"
                        },
                        paramCopy:{
                            title:"Communion"
                        },
                        title:["Director"],
                        infoShown:[],
                        infoMysql:[],
                        infoHive:[],
                        infoNeo4j:[],
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
                    p7:{
                        type:"post",
                        organization:"Searching By Type",
                        essentials:"Query the movies in type P1 ",
                        pathMysql:":8091/score/getMovieByType",
                        pathHive:":8899/score/getMovieByType",
                        pathNeo4j:"",
                        param:{
                            type:"Action"
                        },
                        paramCopy:{
                            type:"Action"
                        },
                        title:["Title","Score"],
                        infoShown:[],
                        infoMysql:[],
                        infoHive:[],
                        infoNeo4j:[],
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
                    p8:{
                        type:"post",
                        organization:"Searching By Score",
                        essentials:"Query the movies with a score greater than P1",
                        pathMysql:":8091/score/getMovieByScore",
                        pathHive:":8899/score/getMovieByScore",
                        pathNeo4j:"",
                        param:{
                            scores:5
                        },
                        paramCopy:{
                            scores:5
                        },
                        title:["Title","Score"],
                        infoShown:[],
                        infoMysql:[],
                        infoHive:[],
                        infoNeo4j:[],
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
                    p9:{
                        type:"post",
                        organization:"Searching By Score",
                        essentials:"Query the movies with at least one positive comment",
                        pathMysql:":8091/score/getPositiveMovie",
                        pathHive:":8899/score/getPositiveMovie",
                        pathNeo4j:"",
                        param:null,
                        paramCopy:null,
                        title:["Title","Score"],
                        infoShown:[],
                        infoMysql:[],
                        infoHive:[],
                        infoNeo4j:[],
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
                    p10:{
                        type:"post",
                        organization:"Searching By Type",
                        essentials:"Query the unpopular movie types",
                        pathMysql:":8091/score/getPopularMovieType",
                        pathHive:":8899/score/getPopularMovieType",
                        pathNeo4j:"",
                        param:{
                            up:0
                        },
                        paramCopy:{
                            up:0
                        },
                        title:["Type","ProductNum"],
                        infoShown:[],
                        infoMysql:[],
                        infoHive:[],
                        infoNeo4j:[],
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
                    p11:{
                        type:"post",
                        organization:"Searching By Type",
                        essentials:"Query the popular movie types",
                        pathMysql:":8091/score/getPopularMovieType",
                        pathHive:":8899/score/getPopularMovieType",
                        pathNeo4j:"",
                        param:{
                            up:1
                        },
                        paramCopy:{
                            up:1
                        },
                        title:["Type","ProductNum"],
                        infoShown:[],
                        infoMysql:[],
                        infoHive:[],
                        infoNeo4j:[],
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
                    p12:{
                        type:"get",
                        organization:"Searching By Actor",
                        essentials:"Query movies  which actor P1 act in",
                        pathMysql:":8091/person/getActorWorks",
                        pathHive:":8899/person/getActorWorks",
                        pathNeo4j:"",
                        param:{
                            name:"Various"
                        },
                        paramCopy:{
                            name:"Various"
                        },
                        title:["Title","Score"],
                        infoShown:[],
                        infoMysql:[],
                        infoHive:[],
                        infoNeo4j:[],
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
                    p13:{
                        type:"get",
                        organization:"Searching By Character Relationship",
                        essentials:"Query the actors who have worked with P1",
                        pathMysql:":8091/person/getCoactor",
                        pathHive:":8899/person/getCoactor",
                        pathNeo4j:"",
                        param:{
                            name:"Various"
                        },
                        paramCopy:{
                            name:"Various"
                        },
                        title:["Name","CooperationNum"],
                        infoShown:[],
                        infoMysql:[],
                        infoHive:[],
                        infoNeo4j:[],
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
                    p14:{
                        type:"get",
                        organization:"Searching By Character Relationship",
                        essentials:"Query the directors who have worked with P1",
                        pathMysql:":8091/person/getCodirector",
                        pathHive:":8899/person/getCodirector",
                        pathNeo4j:"",
                        param:{
                            name:"Harry"
                        },
                        paramCopy:{
                            name:"Harry"
                        },
                        title:["Name","CooperationNum"],
                        infoShown:[],
                        infoMysql:[],
                        infoHive:[],
                        infoNeo4j:[],
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
                    p15:{
                        type:"get",
                        organization:"Searching By Character Relationship",
                        essentials:"Query the directors and actors who often work together",
                        pathMysql:":8091/person/getCooperatorAD",
                        pathHive:":8899/person/getCooperatorAD",
                        pathNeo4j:"",
                        param:null,
                        paramCopy:null,
                        title:["Director","Actor","CooperationNum"],
                        infoShown:[],
                        infoMysql:[],
                        infoHive:[],
                        infoNeo4j:[],
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
                    p16:{
                        type:"get",
                        organization:"Searching By Character Relationship",
                        essentials:"Query the actors who often work together",
                        pathMysql:":8091/person/getCooperatorActor",
                        pathHive:":8899/person/getCooperatorActor",
                        pathNeo4j:"",
                        param:null,
                        paramCopy:null,
                        title:["Actor1","Actor2","CooperationNum"],
                        infoShown:[],
                        infoMysql:[],
                        infoHive:[],
                        infoNeo4j:[],
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
                    p17:{
                        type:"get",
                        organization:"Searching By Character Relationship",
                        essentials:"Query the directors who often work together",
                        pathMysql:":8091/person/getCooperatorDirector",
                        pathHive:":8899/person/getCooperatorDirector",
                        pathNeo4j:"",
                        param:null,
                        paramCopy:null,
                        title:["Director1","Director2","CooperationNum"],
                        infoShown:[],
                        infoMysql:[],
                        infoHive:[],
                        infoNeo4j:[],
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
                    p18:{
                        type:"get",
                        organization:"Searching By Director",
                        essentials:"Query movies directed by director P1",
                        pathMysql:":8091/person/getDirectorWorks",
                        pathHive:":8899/person/getDirectorWorks",
                        pathNeo4j:"",
                        param:{
                        name:"Harry"
                        },
                        paramCopy:{
                            name:"Harry"
                        },
                        title:["Movie","Score"],
                        infoShown:[],
                        infoMysql:[],
                        infoHive:[],
                        infoNeo4j:[],
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
                    p19:{
                        type:"get",
                        organization:"Searching By Actor",
                        essentials:"Query actors who have participated in at least P1 movies",
                        pathMysql:":8091/person/getGreaterActor",
                        pathHive:":8899/person/getGreaterActor",
                        pathNeo4j:"",
                        param:{
                            number:10
                        },
                        paramCopy:{
                            number:10
                        },
                        title:["Actor","MovieNum"],
                        infoShown:[],
                        infoMysql:[],
                        infoHive:[],
                        infoNeo4j:[],
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
                    p20:{
                        type:"get",
                        organization:"Searching By Actor",
                        essentials:"Query directors who have direct at least P1 movies",
                        pathMysql:":8091/person/getGreaterDirector",
                        pathHive:":8899/person/getGreaterDirector",
                        pathNeo4j:"",
                        param:{
                            number:10
                        },
                        paramCopy:{
                            number:10
                        },
                        title:["Director","MovieNum"],
                        infoShown:[],
                        infoMysql:[],
                        infoHive:[],
                        infoNeo4j:[],
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
                    p21:{
                        type:"post",
                        organization:"Combination Searching",
                        essentials:"Query the popular movie types in year P1",
                        pathMysql:":8091/Combination/getTimePopular",
                        pathHive:":8899/Combination/getTimePopular",
                        pathNeo4j:"",
                        param:{
                            year:2019
                        },
                        paramCopy:{
                            year:2019
                        },
                        title:["Type","MovieNum"],
                        infoShown:[],
                        infoMysql:[],
                        infoHive:[],
                        infoNeo4j:[],
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
                    p22:{
                        type:"post",
                        organization:"Combination Searching",
                        essentials:"Query all P1 movies directed by P2 since P3",
                        pathMysql:":8091/Combination/getTimeDirectorType",
                        pathHive:":8899/Combination/getTimeDirectorType",
                        pathNeo4j:"",
                        param:{
                            year:1995,
                            name:"Phillip Borsos",
                            type:"Adventure"
                        },
                        paramCopy:{
                            year:1995,
                            name:"Phillip Borsos",
                            type:"Adventure"
                        },
                        title:["Movie","Score"],
                        infoShown:[],
                        infoMysql:[],
                        infoHive:[],
                        infoNeo4j:[],
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
                this.$http.get(this.baseURL+path ,{params: param,emulateJSON:true,_timeout:120000,
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
                this.$http.post(this.baseURL+path , param,{emulateJSON:true,_timeout:120000,
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
                    //this.postData(this.localParam.pathNeo4j,this.localParam.param,"infoNeo4j");
                    //待添加并发请求
                    //this.localParam.infoShown=this.localParam.infoMysql;


                }
                else if(this.localParam.type==="get")
                {
                    this.getData(this.localParam.pathMysql,this.localParam.param,"infoMysql");
                    //this.getData(this.localParam.pathNeo4j,this.localParam.param,"infoNeo4j");
                    //待添加并发请求
                    this.localParam.infoShown=this.localParam.infoMysql;

                }
                return 1
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
                this.queryChoose["p22"]=false;
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
                else if(str==="(HDFS)Hadoop Distributed File System")
                    this.localParam.infoShown=this.localParam.infoHive;
                else if(str==="Neo4j")
                    this.localParam.infoShown=this.localParam.infoNeo4j;
                console.log(this.localParam.infoShown);
                console.log(this.localParam.dataBase);

            },
            setQuery(){ //切换查询状态
                if(this.localParam.queryState==="Query")
                {
                    this.localParam.sights.sight2=false;
                    this.localParam.sights.state=true;
                    this.localParam.queryState="Querying";
                    var t=this.queryAll();
                    console.log(t);
                    setTimeout(this.dataReady,1000);
                }
                else
                {
                    this.localParam.sights.state=true;
                    this.localParam.queryState="";
                    setTimeout(this.reset,500);
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
