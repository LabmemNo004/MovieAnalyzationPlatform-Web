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
                    <h5 >Searching By Movie</h5>

                    <button class="btn btn-primary btn-rounded-f btn-block fs-mini" v-on:click="popData('p1')"
                    >
                        Query all movie products under the movie "P1"
                    </button>
                    <button class="btn btn-primary btn-rounded-f btn-block fs-mini" v-on:click="popData('p2')"
                    >
                        Query the number of movie products released each year
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
