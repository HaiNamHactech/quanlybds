<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div>
    <div class="inner-banner has-base-color-overlay text-center mt-3">
      <div class="container">
        <div class="box">
          <h3 :class="this.zoom ? 'zoom-in-zoom-out' : ''">
            <template v-if="this.menu"> {{ this.menu.name }}</template>
          </h3>
        </div>
      </div>
    </div>
    <Breadcrumb :titlePage="'Hướng dẫn'"></Breadcrumb>
    <section class="services-section mt-5">
      <div class="container">
        <div class="outer-box">
          <ul class="ps-0" v-if="instruct && instruct.content">
            <li class="mt-5" v-for="instruct in instruct.content" :key="instruct.ID">
              <div class="sec-title">
                <h2 style="font-size: 22px" v-html="instruct.title">
                  
                </h2>
              </div>
              <div class="main-content" v-html="instruct.description">
              </div>
            </li>
          </ul>
        </div>
      </div>
    </section>

    <div class="sec-title center mt-5">
      <h2>Thành tựu <span> đạt được</span></h2>
    </div>
    <section class="fact-counter mb-5" style="">
      <div class="container">
        <div class="row clearfix">
          <Waypoint @change="run_anmition_count_number">
            <div class="counter-outer clearfix d-flex">
              <!--Column-->
              <article
                class="column counter-column col-md-3 col-sm-6 col-xs-12 wow fadeIn"
                data-wow-duration="0ms"
              >
                <div class="item">
                  <div class="icon"><i class="flaticon-happy"></i></div>
                  <div class="count-outer">
                    <number
                      v-if="this.animationCountNumber"
                      :from="10"
                      :to="1382"
                      :duration="2"
                      easing="Power1.easeOut"
                    />+
                  </div>
                  <h4 class="counter-title">Thành Viên</h4>
                </div>
              </article>

              <!--Column-->
              <article
                class="column counter-column col-md-3 col-sm-6 col-xs-12 wow fadeIn"
                data-wow-duration="0ms"
              >
                <div class="item">
                  <div class="icon"><i class="flaticon-competition"></i></div>
                  <div class="count-outer">
                    <number
                      v-if="this.animationCountNumber"
                      :from="10"
                      :to="1000"
                      :duration="2"
                      easing="Power1.easeOut"
                    />+
                  </div>
                  <h4 class="counter-title">Tin Đăng</h4>
                </div>
              </article>

              <!--Column-->
              <article
                class="column counter-column col-md-3 col-sm-6 col-xs-12 wow fadeIn"
                data-wow-duration="0ms"
              >
                <div class="item">
                  <div class="icon"><i class="flaticon-network"></i></div>
                  <div class="count-outer">
                    <number
                      v-if="this.animationCountNumber"
                      :from="10"
                      :to="13400"
                      :duration="2"
                      easing="Power1.easeOut"
                    />+
                  </div>
                  <h4 class="counter-title">Lượt truy cập/tháng</h4>
                </div>
              </article>

              <!--Column-->
              <article
                class="column counter-column col-md-3 col-sm-6 col-xs-12 wow fadeIn"
                data-wow-duration="0ms"
              >
                <div class="item">
                  <div class="icon"><i class="flaticon-coffee-cup"></i></div>
                  <div class="count-outer">
                    <number
                      v-if="this.animationCountNumber"
                      :from="10"
                      :to="123400"
                      :duration="2"
                      easing="Power1.easeOut"
                    />+
                  </div>
                  <h4 class="counter-title">Lượt xem/tháng</h4>
                </div>
              </article>
            </div>
          </Waypoint>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import { Waypoint } from "vue-waypoint";
import Breadcrumb from "@/views/components/breadcrumb.vue";

export default {
  components: {
    Waypoint,
    Breadcrumb,
  },

  created() {
    this.animationZoom();
    this.menu = this.category.content.filter(
      (x) => x.slug == this.$route.name
    )[0];
  },

  watch: {
    $route: function () {
      this.animationZoom();
      this.menu = this.category.content.filter(
        (x) => x.slug == this.$route.name
      )[0];
    },
  },

  mounted() {
    this.getInstruct();
  },
  computed: {
    category() {
      return this.$store.getters.getCategory;
    },
    instruct(){
      return this.$store.getters.getIntro;
    }
  },
  methods: {
    run_anmition_count_number(waypointState) {
      if (waypointState.going == "IN") {
        this.animationCountNumber = true;
      } else {
        this.animationCountNumber = false;
      }
    },

    animationZoom() {
      setTimeout(() => {
        this.zoom = false;
      }, 1800);
      this.zoom = true;
    },
    getInstruct() {
      this.$store.dispatch("fetchIntros", {
        filter: {
          page: 0,
          pageSize: 15,
          columnFilters: { types: "INSTRUCT" },
          sort: [{ field: "rate", type: "asc" }],
        },
      });
    },
  },

  data() {
    return {
      animationCountNumber: false,
      menu: null,
      serverParams: {
        filter: { page: 0, pageSize: 3, columnFilters: {}, sort: [] },
      },
    };
  },
};
</script>

<style scoped>
.list-price-filter {
  background: #f5f5f5;
  padding: 15px;
  border-radius: 8px;
  border: 1px solid #dedede;
}
.inner-banner {
  background: url(../../src/assets/images/background/1.jpg);
}
.zoom-in-zoom-out {
  animation: zoom-in-zoom-out 1.5s ease-out;
}

@keyframes zoom-in-zoom-out {
  0% {
    transform: scale(0, 0);
  }
  100% {
    transform: scale(1, 1);
  }
}

.single-item-extra .img-box img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.price_room {
  color: #77ba00;
  font-weight: bold;
}
.service-section .single-item:hover .price_room {
  color: #fff !important;
}
.title-post p {
  display: -webkit-box !important;
  -webkit-line-clamp: 2 !important;
  -webkit-box-orient: vertical !important;
  overflow: hidden !important;
  overflow-wrap: break-word;
}
.list-price-filter .item-filter span {
  font-size: 15px;
  color: gray;
  margin-right: 5px;
}
.list-price-filter .item-filter:hover {
  cursor: pointer;
  transition: all 0.5s ease;
  color: #ff6600;
}
.single-item-extra:hover .link-btn5 a {
  color: #fff !important;
  cursor: pointer;
}
.fact-counter {
  background-image: url("../../src/assets/images/background/2.jpg");
}
.text-category a {
  font-size: 15px;
  cursor: pointer;
  color: #000;
  display: flex;
  align-items: center;
}
.text-category a:hover {
  color: #ff6600;
}
.sidebar-padd {
  padding: 20px;
  border: 1px solid #dedede;
  background: #f5f5f5;
  border-radius: 8px;
}
.sidebar_search button {
  width: 38px !important;
  height: 38px !important;
  position: absolute;
  border-radius: 8px !important;
  transition: all 0.5s ease;
}
.sidebar_search button:hover {
  cursor: pointer;
  background: #000;
}
.sidebar_search input {
  height: 38px !important;
  color: #000;
  border-radius: 8px 0px 0px 8px !important;
}
.outer-box {
  border-radius: 8px;
  border: 1px solid dedede;
  background: #f5f5f5;
  padding: 20px;
}
.outer-box ul li {
  line-height: 30px;
}
</style>
