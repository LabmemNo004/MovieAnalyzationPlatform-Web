<template>
    <div :class="{ 'theme-helper': true, 'theme-helper-opened': opened }">
        <div class="theme-helper-toggler" @click="toggle">
            <div class="theme-helper-spinner bg-primary text-white">
                <i class="la la-cog"></i>
                <i class="la la-cog fs-smaller"></i>
            </div>
        </div>
      <section class="widget theme-helper-content">
            <div class="widget-body mt-3">
                <h4 class='fw-semi-bold'>CUSTOMIZE YOUR QUERY</h4>
                <div class="mt-4 design">
                    <h5 >Searching By Time</h5>
                    <button class="btn btn-primary btn-rounded-f btn-block fs-mini" v-on:click="popData('p2')"
                    >
                        Query the number of movie products released each year
                    </button>
                    <button class="btn btn-primary btn-rounded-f btn-block fs-mini" v-on:click="popData('p4')"
                    >
                        Query the number of movie products released each month in "P1"
                    </button>


                    <h5 style="margin-top: 30px">Searching By Movie</h5>
                    <button class="btn btn-primary btn-rounded-f btn-block fs-mini" v-on:click="popData('p1')"
                    >
                        Query all the movie products of the movie "P1"
                    </button>
                    <button class="btn btn-primary btn-rounded-f btn-block fs-mini" v-on:click="popData('p3')"
                    >
                        Query the basic information of the movie "P1"
                    </button>
                    <button class="btn btn-primary btn-rounded-f btn-block fs-mini" v-on:click="popData('p5')"
                    >
                        Query the actors of the movie "P1"
                    </button>
                    <button class="btn btn-primary btn-rounded-f btn-block fs-mini" v-on:click="popData('p6')"
                    >
                        Query the directors of the movie "P1"
                    </button>


                    <h5 style="margin-top: 30px">Searching By Actor</h5>
                    <button class="btn btn-primary btn-rounded-f btn-block fs-mini" v-on:click="popData('p12')"
                    >
                        Query the movies which actor "P1" act in
                    </button>
                    <button class="btn btn-primary btn-rounded-f btn-block fs-mini" v-on:click="popData('p19')"
                    >
                        Query actors who have act at least "P1" movies
                    </button>


                    <h5 style="margin-top: 30px">Searching By Director</h5>
                    <button class="btn btn-primary btn-rounded-f btn-block fs-mini" v-on:click="popData('p18')"
                    >
                        Query movies directed by director "P1"
                    </button>
                    <button class="btn btn-primary btn-rounded-f btn-block fs-mini" v-on:click="popData('p20')"
                    >
                        Query directors who have direct at least "P1" movies
                    </button>


                    <h5 style="margin-top: 30px">Searching By Character Relationship</h5>
                    <button class="btn btn-primary btn-rounded-f btn-block fs-mini" v-on:click="popData('p13')"
                    >
                        Query the actors who have worked with "P1"
                    </button>
                    <button class="btn btn-primary btn-rounded-f btn-block fs-mini" v-on:click="popData('p14')"
                    >
                        Query the directors who have worked with "P1"
                    </button>
                    <button class="btn btn-primary btn-rounded-f btn-block fs-mini" v-on:click="popData('p15')"
                    >
                        Query the directors and actors who often work together
                    </button>
                    <button class="btn btn-primary btn-rounded-f btn-block fs-mini" v-on:click="popData('p16')"
                    >
                        Query the actors who often work together
                    </button>
                    <button class="btn btn-primary btn-rounded-f btn-block fs-mini" v-on:click="popData('p17')"
                    >
                        Query the directors who often work together
                    </button>


                    <h5 style="margin-top: 30px">Searching By Type</h5>
                    <button class="btn btn-primary btn-rounded-f btn-block fs-mini" v-on:click="popData('p7')"
                    >
                        Query the movies in type "P1"
                    </button>
                    <button class="btn btn-primary btn-rounded-f btn-block fs-mini" v-on:click="popData('p10')"
                    >
                        Query the unpopular movie types
                    </button>
                    <button class="btn btn-primary btn-rounded-f btn-block fs-mini" v-on:click="popData('p11')"
                    >
                        Query the popular movie types
                    </button>


                    <h5 style="margin-top: 30px">Searching By Score</h5>
                    <button class="btn btn-primary btn-rounded-f btn-block fs-mini" v-on:click="popData('p8')"
                    >
                        Query the movies with a score greater than "P1"
                    </button>
                    <button class="btn btn-primary btn-rounded-f btn-block fs-mini" v-on:click="popData('p9')"
                    >
                        Query the movies with at least one positive comment
                    </button>


                    <h5 style="margin-top: 30px">Combination Searching</h5>
                    <button class="btn btn-primary btn-rounded-f btn-block fs-mini" v-on:click="popData('p21')"
                    >
                        Query the popular movie types in year "P1"
                    </button>
                    <button class="btn btn-primary btn-rounded-f btn-block fs-mini" v-on:click="popData('p22')"
                    >
                        Query all "P1" movies directed by "P2" in "P3"
                    </button>

                </div>


                <!--
                <div class="d-flex justify-content-between mt-lg">
                    <a role="button"
                    target="_blank"
                    href="https://flatlogic.com/contact"
                    class="btn btn-outline-default btn-rounded-f fs-mini text-muted d-flex align-items-center">
                        <i class="glyphicon glyphicon-headphones mr-xs"></i>
                        Support
                    </a>
                    <a role="button"
                    target="_blank"
                    href="https://github.com/flatlogic/sing-app-vue-dashboard"
                    class="btn btn-outline-default btn-rounded-f fs-mini text-muted d-flex align-items-center">
                        <i class="fa fa-github mr-xs"></i>
                        Github
                    </a>
                </div>-->

            </div>
        </section>
    </div>
</template>

<script>
export default {
    name: 'Helper',
    data() {
        return {
            opened: false,
        }
    },
    methods: {
        toggle() {
            this.opened = !this.opened;
        },
        popData(num){
            this.$emit("choose",num);
            console.log("popData"+num)
        }
    }
};
</script>

<style src="./Helper.scss" lang="scss"></style>
