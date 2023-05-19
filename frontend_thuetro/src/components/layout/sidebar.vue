<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div style="width: 16%">
    <nav class="sidebar sidebar-offcanvas" id="sidebar">
      <div
        class="sidebar-brand-wrapper d-none d-lg-flex align-items-center justify-content-center fixed-top"
      >
        <a class="sidebar-brand brand-logo"
          ><img src="@/assets/admin/images/logo.svg" alt="logo"
        /></a>
        <a class="sidebar-brand brand-logo-mini"
          ><img src="@/assets/admin/images/logo-mini.svg" alt="logo"
        /></a>
      </div>
      <ul class="nav">
        <li class="nav-item profile" v-if="this.userData">
          <div class="profile-desc">
            <div class="profile-pic">
              <div class="count-indicator">
                <img
                  class="img-xs rounded-circle"
                  :src="this.userData.avatar"
                  alt=""
                />
                <span class="count bg-success"></span>
              </div>
              <div class="profile-name">
                <h5 class="mb-0 font-weight-normal">
                  {{ this.userData.name }}
                </h5>
                <span style="font-size: 13px">{{ this.userData.phone }}</span>
              </div>
            </div>
            <a href="#" id="profile-dropdown" data-toggle="dropdown"
              ><i class="mdi mdi-dots-vertical"></i
            ></a>
            <div
              class="dropdown-menu dropdown-menu-right sidebar-dropdown preview-list"
              aria-labelledby="profile-dropdown"
            >
              <a href="#" class="dropdown-item preview-item">
                <div class="preview-thumbnail">
                  <div class="preview-icon bg-dark rounded-circle">
                    <i class="mdi mdi-settings text-primary"></i>
                  </div>
                </div>
                <div class="preview-item-content">
                  <p class="preview-subject ellipsis mb-1 text-small">
                    Account settings
                  </p>
                </div>
              </a>
              <div class="dropdown-divider"></div>
              <a href="#" class="dropdown-item preview-item">
                <div class="preview-thumbnail">
                  <div class="preview-icon bg-dark rounded-circle">
                    <i class="mdi mdi-onepassword text-info"></i>
                  </div>
                </div>
                <div class="preview-item-content">
                  <p class="preview-subject ellipsis mb-1 text-small">
                    Change Password
                  </p>
                </div>
              </a>
              <div class="dropdown-divider"></div>
              <a href="#" class="dropdown-item preview-item">
                <div class="preview-thumbnail">
                  <div class="preview-icon bg-dark rounded-circle">
                    <i class="mdi mdi-calendar-today text-success"></i>
                  </div>
                </div>
                <div class="preview-item-content">
                  <p class="preview-subject ellipsis mb-1 text-small">
                    To-do list
                  </p>
                </div>
              </a>
            </div>
          </div>
        </li>
        <li
          class="nav-item nav-category pt-0 pb-0"
          style="color: #fff"
          v-if="this.userData"
        >
          <p class="mb-2">
            Mã Thành Viên : <span class="font-weight-normal"> CD201189</span>
          </p>
          <p v-if="balance >= 0">
            TKC :
            {{
              new Intl.NumberFormat("vi-VN", {
                currency: "VND",
              }).format(this.balance)
            }}
            VNĐ
          </p>
        </li>
        <li class="nav-item nav-category d-flex">
          <button type="button" class="btn btn-warning btn-fw me-3">
            Nạp tiền
          </button>
          <button type="button" class="btn btn-danger btn-fw">Đăng tin</button>
        </li>

        <template v-if="functions && functions.content">
          <div v-for="f in functions.content" :key="f.Id">
            <li class="nav-item menu-items">
              <router-link
                :to="{
                  name: f.url,
                }"
              >
                <a class="nav-link">
                  <span class="menu-icon">
                    <i :class="f.icon"></i>
                  </span>
                  <span class="menu-title">{{ f.functionName }}</span></a
                >
              </router-link>
            </li>
          </div>
          <li class="nav-item menu-items" @click="logout()">
            <a class="nav-link">
              <span class="menu-icon">
                <i class="mdi mdi-export"></i>
              </span>
              <span class="menu-title">Thoát</span></a
            >
          </li>
        </template>
      </ul>
    </nav>
  </div>
</template>

<script>
export default {
  props: {
    reloadDataUser: null,
  },
  mounted() {
    this.$store.dispatch("fetchFunction", {
      vm: this,
      param: this.serverParams,
    });
    this.userData = localStorage.userData
      ? JSON.parse(localStorage.userData)
      : null;
  },
  watch: {
    reloadDataUser: function () {
      this.userData = localStorage.userData
        ? JSON.parse(localStorage.userData)
        : null;
    },
    userData: function () {
      this.userData = localStorage.userData
        ? JSON.parse(localStorage.userData)
        : null;
    },
    autoGetRoom: function (data) {
      var totalNumberSeconds = 24 * 3600 * 1000;
      setTimeout(() => {
        this.$store.dispatch("fetchMd", {
          type: "ghost/room",
          params: {
            filter: { page: 0, pageSize: 10, columnFilters: {}, sort: [] },
          },
        });
        this.autoGetRoom = !data;
      }, totalNumberSeconds);
    },
  },

  computed: {
    functions() {
      return this.$store.getters.getFunction;
    },
    balance() {
      return this.$store.getters.getBalance;
    },
  },
  methods: {
    logout() {
      localStorage.removeItem("token");
      localStorage.removeItem("userData");
      this.$router.push({ name: "trang-chu" });
    },
  },
  data() {
    return {
      userData: null,
      autoGetRoom: false,
      serverParams: {
        filter: {
          page: 0,
          pageSize: 20,
          columnFilters: { systems: "CLIENT" },
          sort: [],
        },
      },
    };
  },
};
</script>

<style src="@/assets/admin/css/style.css" scoped>
.profile-name h5 {
  color: #fff !important;
}
</style>
<style>
.router-link-active.active .nav-link {
  color: #fff !important;
}
</style>
