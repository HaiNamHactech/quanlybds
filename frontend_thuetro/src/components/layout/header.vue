<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div>
    <header class="top-bar">
      <div class="container">
        <div class="row d-flex justify-content-between align-items-center">
          <div class="col-md-2">
            <div class="main-logo">
              <a href="index-2.html"
                ><img src="../../../src/assets/images/logo/logo.png" alt=""
              /></a>
            </div>
          </div>
          <div class="col-md-9">
            <div class="row justify-content-end">
              <div
                class="col-md-5 d-flex justify-content-center"
                v-if="this.userData"
              >
                <img class="img-avatar" :src="this.userData.avatar" alt="" />
                <div class="info-customer">
                  <ul class="ps-2 mt-1">
                    <li>
                      Xin chào,
                      <span style="font-weight: 500">{{
                        this.userData.name
                      }}</span>
                    </li>
                    <li style="font-size: 13px" v-if="this.balance >= 0">
                      <span style="font-size: 12px">Mã Tài Khoản :</span> 117654
                      - <span style="font-size: 12px">Số dư :</span>
                      {{
                        new Intl.NumberFormat("vi-VN", {
                          currency: "VND",
                        }).format(this.balance)
                      }}
                      VNĐ
                    </li>
                  </ul>
                </div>
              </div>
              <div
                class="col-md-2 d-flex align-items-center"
                v-if="this.userData"
              >
                <div style="position: relative; top: 3px; cursor: pointer">
                  <vue-feather
                    type="heart"
                    size="18"
                    stroke-width="2"
                    class="me-2"
                  >
                  </vue-feather>
                  <div class="info-number-heart">2</div>
                </div>

                <span style="font-weight: 400; cursor: pointer">Yêu thích</span>
              </div>
              <div
                class="col-md-3 d-flex align-items-center"
                v-if="this.userData && functions && functions.content"
              >
                <vue-feather
                  type="grid"
                  size="18"
                  stroke-width="2"
                  class="me-2"
                >
                </vue-feather>
                <div class="dropdown">
                  <button
                    class="dropdown-toggle"
                    id="dropdownMenuButton1"
                    data-bs-toggle="dropdown"
                    aria-expanded="false"
                  >
                    <span style="font-weight: 400; cursor: pointer"
                      >Quản lý tài khoản</span
                    >
                  </button>
                  <ul
                    class="dropdown-menu"
                    aria-labelledby="dropdownMenuButton1"
                  >
                    <li
                      v-for="f in functions.content"
                      :key="f.Id"
                      style="line-height: 25px"
                      class="nav-item menu-items"
                    >
                      <router-link
                        :to="{
                          name: f.url,
                        }"
                      >
                        <a class="dropdown-item">
                          <span class="menu-icon me-2">
                            <i :class="f.icon"></i>
                          </span>
                          <span class="menu-title">{{
                            f.functionName
                          }}</span></a
                        >
                      </router-link>
                    </li>
                    <li
                      class="nav-item menu-items"
                      style="line-height: 25px"
                      @click="logout()"
                    >
                      <a class="dropdown-item">
                        <span class="menu-icon me-2">
                          <i class="mdi mdi-export"></i>
                        </span>
                        <span class="menu-title">Thoát</span></a
                      >
                    </li>
                  </ul>
                </div>
              </div>
              <div
                class="col-md-4 d-flex align-items-center justify-content-end"
                v-if="!this.userData"
              >
                <span
                  @click="loginRouter()"
                  class="login me-4 d-flex align-items-center"
                  ><vue-feather
                    class="me-1"
                    size="18"
                    type="user-plus"
                    stroke-width="1"
                  ></vue-feather>
                  Đăng nhập</span
                >
                <span class="login d-flex align-items-center"
                  ><vue-feather
                    class="me-1"
                    size="18"
                    type="external-link"
                    stroke-width="1"
                  ></vue-feather>
                  Đăng ký
                </span>
              </div>

              <div class="col-md-2 d-flex align-items-center">
                <button
                  type="button"
                  class="ps-2 pe-1 btn btn-add d-flex align-items-center"
                >
                  <span style="font-weight: 400; font-size: 15px"
                    >Đăng tin mới</span
                  >
                  <vue-feather
                    type="plus-circle"
                    size="14"
                    stroke-width="2"
                    class="ms-1"
                  >
                  </vue-feather>
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </header>

    <section
      :class="this.navbarfixed ? 'sticky' : ''"
      class="mainmenu-area stricky"
    >
      <div class="container">
        <div class="row d-flex justify-content-center">
          <div class="col-md-12 menu-column">
            <nav
              class="navbar navbar-expand-lg navbar-light bg-light pt-0 pb-0"
            >
              <div class="container-fluid ps-0 pe-0">
                <div
                  class="collapse navbar-collapse justify-content-center"
                  id="navbarNav"
                  v-if="menus"
                >
                  <ul class="navbar-nav" v-for="m in menus" :key="m.ID">
                    <li class="nav-item">
                      <router-link
                        :class="
                          this.slugPageBefore == m.slug
                            ? 'router-link-active active'
                            : ''
                        "
                        :to="{
                          name: m.slug,
                        }"
                      >
                        <a class="nav-link" aria-current="page">{{ m.name }}</a>
                      </router-link>
                    </li>
                  </ul>
                </div>
              </div>
            </nav>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import { nextTick } from "vue";

export default {
  components: {},
  mounted() {
    window.addEventListener("scroll", this.handleScroll);
    this.userData = localStorage.userData
      ? JSON.parse(localStorage.userData)
      : null;
    this.getFunction();
  },
  unmounted() {
    window.removeEventListener("scroll", this.handleScroll);
  },
  watch: {
    $route: function () {
      nextTick(() => {
        if (this.$route.params.slugPageBefore) {
          this.slugPageBefore = this.$route.params.slugPageBefore;
        } else {
          this.slugPageBefore = this.$route.meta.pathMapActive;
        }
      });
    },
  },
  computed: {
    functions() {
      return this.$store.getters.getFunction;
    },
    balance() {
      return this.$store.getters.getBalance;
    },
    prices() {
      var prices = this.$store.getters.getPriceOrArea;
      if (prices && prices.content) {
        return prices;
      }
      return null;
    },
    menus() {
      var x = this.$store.getters.getCategory;
      var y = [];
      if (x && x.content) {
        y = x.content.filter((m) => m.showMenu === 1);
      }
      return y;
    },
  },
  methods: {
    handleScroll() {
      if (window.scrollY > 180) {
        this.navbarfixed = true;
      } else {
        this.navbarfixed = false;
      }
    },
    loginRouter() {
      this.$router.push({ name: "login" });
    },
    getFunction() {
      if (this.userData) {
        this.$store.dispatch("fetchFunction", {
          vm: this,
          param: this.serverParams,
        });
        this.$store.dispatch("fetchBalance");
      }
    },
    logout() {
      localStorage.removeItem("token");
      localStorage.removeItem("userData");
      this.$router.go({ name: "trang-chu" });
    },
  },
  data() {
    return {
      navbarfixed: false,
      slugPageBefore: null,
      slugBlog: null,
      userData: null,
      serverParams: {
        filter: { page: 0, pageSize: 50, columnFilters: {}, sort: [] },
      },
    };
  },
};
</script>

<style scoped>
.router-link-active {
  background: #373737;
}
.router-link-active .nav-link {
  color: #ffff;
}

.sticky {
  position: fixed;
  top: 0px;
  width: 100%;
  display: block !important;
  animation-name: navbar;
  animation-duration: 1s;
  z-index: 150;
  box-shadow: 0px 5px 10px #5a4a3b;
  animation: scrollStickyBar 0.75s;
}
@keyframes scrollStickyBar {
  from {
    top: -20px;
    opacity: 0;
  }
  to {
    top: 0px;
    opacity: 1;
  }
}
.dropdown-menu.show li:hover {
  cursor: pointer;
  background: #77ba00 !important;
  color: #fff !important;
}
.dropdown-menu.show li a:hover {
  background-color: transparent !important;
}
.login:hover {
  cursor: pointer;
  text-decoration: underline;
}
</style>
