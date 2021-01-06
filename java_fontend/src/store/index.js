import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isLoggedIn: false,
    role: -1,
    username:'',
    id:-1,
    avatar:''
  },
  mutations: {
    Login(state,role){
      state.isLoggedIn=true;
      state.role=role;
    },
    Logout(state){
        state.isLoggedIn=false;
        state.role=-1;
        state.username='null';
    },
    Setname(state,username){
      state.username=username;
    },
    Setid(state,id){
      state.id=id;
    },
    Setavatar(state,avatar){
      state.avatar=avatar;
    }
  },
  actions: {
  },
  modules: {
  }
})
