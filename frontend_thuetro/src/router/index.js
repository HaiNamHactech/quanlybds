import { createRouter, createWebHistory } from "vue-router";
import store from "../store";
import core from "./core";
import deposit from "./deposit";
const routes = [...deposit, ...core];

const router = new createRouter({
  history: createWebHistory(),
  linkExactActiveClass: "active",
  routes,
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition;
    } else {
      return { top: 0 };
    }
  },
});

router.beforeEach((to, from, next) => {
  // if (to.meta && to.meta.requiresAuth && !localStorage.token) {
  //   store.commit("setLayout", "auth");
   
  //     next({ name: 'login' });
  // }
   

  if (to.meta && to.meta.layout && to.meta.layout == "auth") {
    store.commit("setLayout", "auth");
  }
  if (to.meta && to.meta.layout && to.meta.layout == "deposit") {
    // if (localStorage.token) {
    //   next({ name: "login" });
    // } else {
      store.commit('setBeforePage', {name : 'nap-tien'});
      store.commit("setLayout", "deposit");
    // }
  }
  if (to.meta && to.meta.layout && to.meta.layout == "full") {
    store.commit("setLayout", "app");
  }
  next(true);
});

export default router;
