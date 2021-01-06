<template>
  <div class="sidebar-wrapper">
    <nav
        :class="{sidebar: true, sidebarStatic, sidebarOpened}"
        @mouseenter="sidebarMouseEnter"
        @mouseleave="sidebarMouseLeave"
    >
      <header class="logo">
        <router-link to="/app">
          <span class="navTitle">{{Title}}</span>
          </router-link>
      </header>
      <ul class="nav">


        <NavLink
                :activeItem="activeItem"
                header="Analysis"
                link="/app/analysis"
                iconName="fi flaticon-network"
                index="analysis"
                isHeader

        />


      </ul>
      <p>
      <h5 class="navTitle">
      Collaborators
      </h5>
      <ul class="sidebarLabels">
        <li>
          <a href="https://github.com/LabmemNo004" target="_blank">
            <i class="fa fa-circle text-danger mr-2"/>
            <span class="labelName">LabmemNo004</span>
          </a>
        </li>
        <li>
          <a href="https://github.com/ComposeC">
            <i class="fa fa-circle text-primary mr-2"/>
            <span class="labelName">ComposeC</span>
          </a>
        </li>
        <li>
          <a href="https://github.com/745184533">
            <i class="fa fa-circle text-info mr-2"/>
            <span class="labelName">Dark Soul</span>
          </a>
        </li>
        <li>
          <a href="https://github.com/sophiedoremi">
            <i class="fa fa-circle mr-2"/>
            <span class="labelName">Sophiedoremi</span>
          </a>
        </li>
      </ul>
      <h5 class="navTitle">
        DataBase
      </h5>
      <div class="sidebarAlerts">
        <b-alert
            v-for="alert in alerts"
            :key="alert.id"
            class="sidebarAlert" variant="transparent"
            show dismissible
        >
          <span>{{alert.title}}</span><br/>
          <b-progress class="sidebarProgress progress-xs mt-1"
                      :variant="alert.color" :value="alert.value" :max="100"/>
          <small>Storage in use . . . {{alert.value}} GB</small>
        </b-alert>
      </div>
    </nav>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import isScreen from '@/core/screenHelper';
import NavLink from './NavLink/NavLink';

export default {
  name: 'Sidebar',
  components: { NavLink },
  data() {
    return {
      alerts: [
        {
          id: 0,
          title: 'Hive',
          value: 15,
          color: 'danger',
        },
        {
          id: 1,
          title: 'MySQL',
          value: 20,
          color: 'primary',
        },
        {
          id: 2,
          title: 'Neo4j',
          value: 25,
          color: 'info',
        },
      ],
      Title:"AM Data Warehouse",
    };
  },
  methods: {
    ...mapActions('layout', ['changeSidebarActive', 'switchSidebar']),
    setActiveByRoute() {
      const paths = this.$route.fullPath.split('/');
      paths.pop();
      this.changeSidebarActive(paths.join('/'));
    },
    sidebarMouseEnter() {
      if (!this.sidebarStatic && (isScreen('lg') || isScreen('xl'))) {
        this.switchSidebar(false);
        this.setActiveByRoute();
        //this.Title="Data Warehouse";
      }
    },
    sidebarMouseLeave() {
      if (!this.sidebarStatic && (isScreen('lg') || isScreen('xl'))) {
        this.switchSidebar(true);
        this.changeSidebarActive(null);
        //this.Title="DW";
      }
    },
  },
  created() {
    this.setActiveByRoute();
  },
  computed: {
    ...mapState('layout', {
      sidebarStatic: state => state.sidebarStatic,
      sidebarOpened: state => !state.sidebarClose,
      activeItem: state => state.sidebarActiveElement,
    }),
  },
};
</script>

<!-- Sidebar styles should be scoped -->
<style src="./Sidebar.scss" lang="scss" scoped/>
