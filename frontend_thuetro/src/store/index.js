import { createStore } from "vuex";

import core from "./modules/core";

export default new  createStore({
  state: {
    layout: "app",
    router : null
  },
  mutations: {
    setLayout(state, payload) {
      state.layout = payload;
    },
  },
  getters: {
    layout(state) {
      return state.layout;
    },
  },
  actions: {},
  modules: { core },
});
