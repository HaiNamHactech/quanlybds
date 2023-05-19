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
    <Breadcrumb :titlePage="'Bảng giá'"></Breadcrumb>
    <section class="services-section mt-5">
      <div class="container">
        <div class="outer-box">
          <ul class="ps-0">
            <li v-if="introPackageNormal">
              <div class="sec-title center">
                <h2
                  style="font-size: 25px"
                  v-html="introPackageNormal[0].title"
                ></h2>
              </div>
              <div
                class="main-content"
                v-html="introPackageNormal[0].description"
              ></div>
            </li>
          </ul>
        </div>
      </div>
    </section>
    <section class="services-section mt-5">
      <div class="sec-title center">
        <div class="container">
          <div
            class="outer-box"
            style="background-color: transparent"
            v-if="introPackageNormal && introPackageNormal[1]"
          >
            <h2
              style="font-size: 25px"
              v-html="introPackageNormal[1].title"
            ></h2>
            <ul
              class="instro-price-list ps-0 mt-3"
              v-html="introPackageNormal[1].description"
            ></ul>
          </div>
        </div>
        <div class="container-fluid">
          <table class="table table-light" v-if="priceList">
            <tbody>
              <tr v-for="val in fields" :key="val.field">
                <th style="font-size: 14px; font-weight: 700">
                  {{ val.label }}
                </th>
                <td
                  v-for="itemPrice in priceList.content"
                  :key="itemPrice.id"
                  :style="{
                    'background-color': itemPrice[val.fieldColor],
                    width: '17%',
                    fontWeight: val.fontWeight,
                    color: val.colorText,
                  }"
                >
                  <template v-if="val.type == 'check'">
                    <span v-if="itemPrice[val.field] == 1">
                      <vue-feather type="check" stroke="#4ad395"></vue-feather>
                    </span>
                    <span v-else>
                      <vue-feather type="x" stroke="#f05e5b"></vue-feather>
                    </span>
                  </template>
                  <template v-if="val.type == 'number'">
                    {{
                      new Intl.NumberFormat("vi-VN", {
                        currency: "VND",
                      }).format(itemPrice[val.field])
                    }}
                    đ
                  </template>
                  <template v-if="val.type == 'button'">
                    <div
                      @click="scrollToElement(itemPrice[val.field])"
                      class="button"
                    >
                      <a
                        class="btn-style-five pt-2 pb-2"
                        style="cursor: pointer"
                        >Xem Demo</a
                      >
                    </div>
                  </template>
                  <template v-if="val.type == 'normal'">
                    <p
                      class="mb-0"
                      :style="{
                        color: itemPrice[val.fieldTextColor],
                        fontWeight: val.fontWeight,
                      }"
                    >
                      {{ itemPrice[val.field] }}
                    </p>
                    <ul class="rating ps-0 d-flex" v-if="val.fieldRate">
                      <i
                        v-for="i in itemPrice[val.fieldRate]"
                        :key="i"
                        style="color: #ffd454"
                        class="fa fa-star"
                      ></i>
                    </ul>
                  </template>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="container" v-if="introPackageDemo">
          <h2 style="font-size: 25px">Minh họa <span>đăng tin</span></h2>
          <div
            v-for="item in introPackageDemo"
            :key="item.id"
            :ref="item.tagContent"
            class="mb-5 mt-5"
          >
            <h5 style="text-align: left">{{ item.title }}</h5>
            <ul class="rating ps-0 d-flex mb-5">
              <i
                v-for="i in item.rate"
                :key="i"
                style="color: #ffd454"
                class="fa fa-star"
              ></i>
            </ul>
            <p style="text-align: left">
              {{ item.description }}
            </p>
            <img class="img-demo" :src="item.image" />
          </div>
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
    <div v-if="priceList"></div>
  </div>
</template>

<script>
import { Waypoint } from "vue-waypoint";
import Breadcrumb from "@/views/components/breadcrumb.vue";
// import { nextTick } from "vue";

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
    this.$store.dispatch("fetchPriceList", this.serverParams);
    this.$store.dispatch("fetchIntros", {
      filter: {
        page: 0,
        pageSize: 10,
        columnFilters: { types: "PACKEGE-SERVICE" },
        sort: [],
      },
    });
  },
  computed: {
    category() {
      return this.$store.getters.getCategory;
    },
    priceList() {
      var priceList = this.$store.getters.getPriceList;
      if (priceList && priceList.content) {
        return priceList;
      }
      return null;
    },
    introPackageDemo() {
      var instroPackageDemo = this.$store.getters.getIntro;
      if (instroPackageDemo && instroPackageDemo.content) {
        return instroPackageDemo.content.filter((x) => x.tagContent != null);
      }
      return null;
    },
    introPackageNormal() {
      var instroPackageNormal = this.$store.getters.getIntro;
      if (instroPackageNormal && instroPackageNormal.content) {
        return instroPackageNormal.content.filter((x) => x.tagContent == null);
      }
      return null;
    },
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

    scrollToElement(val) {
      this.$refs[val][0].scrollIntoView({ behavior: "smooth" });
    },
  },

  data() {
    return {
      animationCountNumber: false,
      menu: null,
      serverParams: {
        filter: { page: 0, pageSize: 10, columnFilters: {}, sort: [] },
      },
      fields: [
        {
          field: "title",
          label: "Tên gói",
          fieldColor: "colorTitle",
          fieldRate: "rate",
          colorText: "#fff",
          fontWeight: "600",
          showField: false,
          type: "normal",
        },
        {
          field: "advantage",
          label: "Ưu điểm",
          type: "normal",
        },
        {
          field: "fit",
          label: "Phù hợp",
          type: "normal",
        },
        {
          field: "dayPrice",
          label: "Giá ngày",
          type: "number",
        },
        {
          field: "weekPrice",
          label: "Giá tuần (7 ngày)",
          type: "number",
        },
        {
          field: "monthPrice",
          label: "Giá tháng (30 ngày)",
          type: "number",
        },
        {
          field: "priceUpNew",
          label: "Giá đẩy tin",
          type: "number",
        },
        {
          field: "typeTextPackge",
          label: "Màu sắc tiêu đề",
          fieldTextColor: "colorTitle",
          fontWeight: "600",
          type: "normal",
        },
        {
          field: "autoBrowsing",
          label: "Tự động duyệt tin",
          type: "check",
        },
        {
          field: "tagDemo",
          label: "Xem Demo",
          type: "button",
        },
      ],
    };
  },
};
</script>

<style scoped>
.container-fluid {
  width: 90% !important;
}
.table td {
  margin: 0px 10px !important;
  border-left: 10px solid #fff;
  padding: 5px 0px 5px 15px !important;
}
.table td,
th {
  text-align: left;
}
.list-price-filter {
  background: #f5f5f5;
  padding: 15px;
  border-radius: 8px;
  border: 1px solid #dedede;
}
.img-demo {
  height: 300px;
  width: 100%;
  object-fit: cover;
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
.col-advantage {
  height: 300px;
}
.col-null {
  height: 40px;
}
</style>
