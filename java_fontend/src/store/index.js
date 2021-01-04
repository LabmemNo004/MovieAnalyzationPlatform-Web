import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isLoggedIn: false,
    role: -1,
    username:'',
    id:-1,
  },
  mutations: {
    Login(state,role){
      state.isLoggedIn=true;
      state.role=role;
      console.log(1);
    },
    Logout(state){
        state.isLoggedIn=false;
        state.role=-1;
        state.username='null';
        console.log(2);
    },
    Setname(state,username){
      state.username=username;
      console.log(3);
    },
    Setid(state,id){
      state.id=id;
      console.log(4);
    },
  },
  actions: {
  },
  modules: {
  }
})
