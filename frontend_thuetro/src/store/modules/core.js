/* eslint-disable no-unused-vars */
/* eslint-disable no-self-assign */
import { $themeConfig } from "../../../theme.config";
import axios from "axios";

axios.interceptors.request.use(function (config) {
  if (config.url != $themeConfig.urlAddress + "?depth=3") {
    const token = localStorage.token;
    config.headers.Authorization = "Bearer " + token;
  }
  document.getElementById("loading_id").style.display = "block";
  return config;
});

export default {
  state: {
    isLoading: false,
    beforePage: null,
    category: null,
    address: null,
    priceOrArea: null,
    rooms: null,
    blog: null,
    introduces: null,
    roomSubmitDateLater: null,
    roomOutstanding: null,
    roomRelation: null,
    priceList: null,
    function: null,
    mds: null,
    singleFile: null,
    users: null,
    balance: null,
  },

  mutations: {
    SET_USER_INFO(state, users) {
      localStorage.userData = JSON.stringify(users);
      if (users.code) {
        localStorage.token = users.code;
      }
    },
    SET_BALANCE(state, payload) {
      state.balance = payload;
    },
    setBeforePage(state, payload) {
      state.beforePage = payload;
    },
    SET_STATE(state, payload) {
      state.isLoading = payload;
    },
    SET_SINGLE_FILE(state, payload) {
      state.singleFile = payload;
    },
    SET_FUNCTION(state, payload) {
      state.function = payload;
    },
    SET_CATEGORY(state, payload) {
      state.category = payload;
    },
    SET_ROOM_OUTSTANDING(state, payload) {
      state.roomOutstanding = payload;
    },
    SET_ROOM_RELATION(state, payload) {
      state.roomRelation = payload;
    },
    SET_MDS(state, payload) {
      state.mds = payload;
    },
    SET_PRICE_LIST(state, payload) {
      state.priceList = payload;
    },
    SET_ADDRESS(state, payload) {
      state.address = payload;
    },
    SET_PRICE_OR_AREA(state, payload) {
      state.priceOrArea = payload;
    },
    SET_ROOM(state, payload) {
      state.rooms = payload;
    },
    SET_ROOM_LATER(state, payload) {
      state.roomSubmitDateLater = payload;
    },
    SET_INTRO(state, payload) {
      state.introduces = payload;
    },
    SET_BLOG(state, payload) {
      state.blog = payload;
    },
  },
  getters: {
    getUserInfo() {
      return localStorage.userData ? JSON.parse(localStorage.userData) : null;
    },
    getBeforePage(state) {
      return state.beforePage;
    },
    getBalance(state) {
      return state.balance;
    },
    getState(state) {
      return state.isLoading;
    },
    getSingleFile(state) {
      return state.singleFile;
    },
    getFunction(state) {
      return state.function;
    },
    getPriceList(state) {
      return state.priceList;
    },
    getPriceOrArea(state) {
      return state.priceOrArea;
    },
    getCategory(state) {
      return state.category;
    },
    getMds(state) {
      return state.mds;
    },
    getAddress(state) {
      return state.address;
    },
    getRooms(state) {
      return state.rooms;
    },
    getRoomLater(state) {
      return state.roomSubmitDateLater;
    },
    getRoomOutstanding(state) {
      return state.roomOutstanding;
    },
    getRoomRelation(state) {
      return state.roomRelation;
    },
    getIntro(state) {
      return state.introduces;
    },
    getBlog(state) {
      return state.blog;
    },
  },
  actions: {
    // Upload file
    async uploadFile(context, file) {
      axios
        .post($themeConfig.url + "ghost/filemanager/upload", file, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((response) => {
          context.commit("SET_SINGLE_FILE", response.data);
        })
        .catch((error) => {
          context.commit("SET_SINGLE_FILE", error.response.data);
        });
    },

    async fetchUserInfo({ commit }, p) {
      try {
        console.log(commit);
        const data = await axios.post($themeConfig.url + "user/login", p.body);
        console.log(data);
        commit("SET_USER_INFO", data.data);
        p.vm.loginSuccess();
      } catch (error) {
        p.vm.loginFailure();
        console.log(error);
      }
    },

    async updateUser({ commit }, p) {
      axios
        .put($themeConfig.url + "user/" + p.params.id, p.params)
        .then((response) => {
          p.vm.$msg(p.mess_sucess);
          p.vm.loadBalance();
          if (p.vm.checkDeposit) {
            p.vm.historyDeposit();
          } else {
            if (!p.vm.checkPay) {
              p.vm.saveNews();
            } else {
              p.vm.payment();
            }
          }
        })
        .catch((error) => {
          p.vm.$msg(p.mess_error, "error");
        });
    },
    async updateAccountUser({ commit }, p) {
      axios
        .put($themeConfig.url + "user/" + p.params.id, p.params)
        .then((response) => {
          commit("SET_USER_INFO", response.data);
          p.vm.handleEmitDataUser();
          p.vm.$msg("Cập nhật thông tin người dùng thành công");
        })
        .catch((error) => {
          p.vm.$msg("Cập nhật thông tin người dùng thất bại", "error");
        });
    },
    async returnMoney({ commit }, p) {
      axios
        .put($themeConfig.url + "user/" + p.params.id, p.params)
        .then((response) => {
          p.vm.$msg(p.mess_sucess);
          p.vm.loadBalance();
        })
        .catch((error) => {
          p.vm.$msg(p.mess_error, "error");
        });
    },

    async newUser({ commit }, p) {
      console.log(commit);
      axios
        .post($themeConfig.url + "ghost/room", p.params)
        .then((res) => {
          p.vm.loadMasterData();
          p.vm.editModal.hide();
          p.vm.historyPayment(res.data.id);
          p.vm.$msg("Thêm mới thành công!");
        })
        .catch((error) => {
          p.vm.$msg("Lỗi thêm mới bài viết ", "error");
          setTimeout(() => {
            p.vm.returnMoney();
          }, 2000);
        });
    },
    async newBalances({ commit }, params) {
      console.log(commit);
      axios
        .post($themeConfig.url + "user/balances", params)
        .then(() => {})
        .catch((error) => {});
    },

    async newDeposit({ commit }, p) {
      console.log(commit);
      axios.post($themeConfig.url + "user/deposit", p.params)
        .then(() => {
          p.vm.showModal.hide();
        })
        .catch((error) => {});
    },

    async editMd({ commit }, p) {
      axios
        .put($themeConfig.url + "ghost/room/" + p.params.id, p.params)
        .then((response) => {
          p.vm.loadMasterData();
          p.vm.editModal.hide();
          p.vm.$msg(p.mess_sucess);
          p.vm.historyPayment(response.data.id);
        })
        .catch((error) => {
          console.log(error);
          p.vm.$msg(p.mess_error, "error");
        });
    },
    async hidenMd({ commit }, p) {
      axios
        .put($themeConfig.url + "ghost/room/hiden/" + p.params.id, p.params)
        .then((response) => {
          p.vm.loadMasterData();
          p.vm.editModal.hide();
          p.vm.$msg(p.mess_sucess);
        })
        .catch((error) => {
          console.log(error);
          p.vm.$msg(p.mess_error, "error");
        });
    },

    //Master Data
    async fetchMd({ commit }, p) {
      axios
        .get($themeConfig.url + p.type + "/paginations/", {
          params: p.params,
        })
        .then((response) => {
          commit("SET_MDS", response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    async fetchFunction({ commit }, p) {
      axios
        .get($themeConfig.url + "user/function/paginations/", {
          params: p.param,
        })
        .then((response) => {
          console.log(response);
          commit("SET_FUNCTION", response.data);
        })
        .catch((error) => {
          console.log(error);
          if (error.response.status == 403) {
            console.log(p.vm.$router.push({ name: "login" }));
          }
        });
    },
    async fetchPriceList({ commit }, params) {
      axios
        .get($themeConfig.url + "ghost/servicePackage/paginations/", {
          params: params,
        })
        .then((response) => {
          commit("SET_PRICE_LIST", response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    async fetchBalance({ commit }) {
      axios
        .get($themeConfig.url + "user/balance")
        .then((response) => {
          commit("SET_BALANCE", response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    async fetchRooms({ commit }, params) {
      axios
        .get($themeConfig.url + "ghost/room/paginations/", { params: params })
        .then((response) => {
          commit("SET_ROOM", response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    async fetchRoomLater({ commit }, params) {
      axios
        .get($themeConfig.url + "ghost/room/paginations/", { params: params })
        .then((response) => {
          commit("SET_ROOM_LATER", response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    async fetchRoomOutstanding({ commit }, params) {
      axios
        .get($themeConfig.url + "ghost/room/paginations/", { params: params })
        .then((response) => {
          commit("SET_ROOM_OUTSTANDING", response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async fetchRoomRelation({ commit }, params) {
      axios
        .get($themeConfig.url + "ghost/room/paginations/", { params: params })
        .then((response) => {
          commit("SET_ROOM_RELATION", response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    async fetchBlogs({ commit }, params) {
      axios
        .get($themeConfig.url + "ghost/blog/paginations/", { params: params })
        .then((response) => {
          commit("SET_BLOG", response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    async fetchIntros({ commit }, params) {
      axios
        .get($themeConfig.url + "ghost/instruct/paginations/", {
          params: params,
        })
        .then((response) => {
          commit("SET_INTRO", response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    async fetchPriceOrArea({ commit }, params) {
      axios
        .get($themeConfig.url + "ghost/priceArea/paginations/", {
          params: params,
        })
        .then((response) => {
          commit("SET_PRICE_OR_AREA", response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    async fetchCategory({ commit }, p) {
      axios
        .get($themeConfig.url + "ghost/category/paginations/", {
          params: p.param,
        })
        .then((response) => {
          commit("SET_CATEGORY", response.data);
        })
        .catch((error) => {
          console.log(error.response.status);
        });
    },

    async fetchAddress({ commit }) {
      axios
        .get($themeConfig.urlAddress + "?depth=3")
        .then((response) => {
          commit("SET_ADDRESS", response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },

  modules: {},
};
