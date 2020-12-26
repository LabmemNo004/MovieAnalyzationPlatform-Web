import Vue from 'vue'
import VueRouter from 'vue-router'
import Register from '../views/Register.vue'
import Login from '../views/Login.vue'
import Home from '../views/Home.vue'
import Movies from '../views/Movies.vue'
import Main from '../components/Main.vue'
import People from '../views/People.vue'
import PersonalHome from '../views/PersonalHome.vue'
import PersonalInfo from '../views/PersonalInfo.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/Login',
  },
  {
    path: '/Login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/Register',
    name: 'Register',
    component: Register
  },
  {
    path: '/Main',
    name: 'Main',
    component: Main,
    redirect: '/Home',
    children: [
      {
        path: '/Home',
        name: 'Home',
        component: Home
      },
      {
        path: '/Movies',
        name: 'Movies',
        component: Movies
      },
      {
        path: '/People',
        name: 'People',
        component: People
      },
      {
        path: '/PersonalHome',
        name: 'PersonalHome',
        component: PersonalHome
      },
      {
        path: '/PersonalInfo',
        name: 'PersonalInfo',
        component: PersonalInfo
      }
    ]
  }
]

const router = new VueRouter({
  routes
})

export default router
