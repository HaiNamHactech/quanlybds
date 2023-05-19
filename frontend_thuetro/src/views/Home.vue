<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div>
    <NavbarFilter @columnFilter="filter" :categorySS="'dddtrang-chu'"  />
    <section class="rev_slider_wrapper mt-3">
      <swiper
        :effect="'cube'"
        :grabCursor="true"
        :cubeEffect="{
          shadow: true,
          slideShadows: true,
          shadowOffset: 20,
          shadowScale: 0.94,
        }"
        :pagination="true"
        :modules="modules"
        navigation
        :autoplay="{
          delay: 7000,
          disableOnInteraction: false,
        }"
        class="mySwiper"
        @slideChange="onSlideChange"
      >
        <swiper-slide>
          <img src="../../src/assets/images/slider/4.jpg" />
          <div class="text-slider">
            <sequential-entrance v-show="this.showTextInslider">
              <div class="box" v-for="index in 1" :key="index">
                <h2>
                  Kênh thông tin Phòng Trọ <br />
                  số 1 Việt Nam
                </h2>
                <marquee-text :duration="30">
                  Kênh thông tin Phòng Trọ số 1 Việt Nam - Website đăng tin cho
                  thuê phòng trọ, nhà nguyên căn, căn hộ, ở ghép nhanh, hiệu quả
                  với 100.000+ tin đăng và 2.500.000 lượt xem mỗi tháng.
                </marquee-text>
                <div class="d-flex mt-2">
                  <div v-animate-onscroll.repeat="{ down: 'animated flip' }">
                    <div class="slide-content-box me-2">
                      <div class="button">
                        <router-link
                          class="btn-style-six"
                          :to="{
                            name: 'huong-dan',
                          }"
                        >
                          Giới thiệu
                        </router-link>
                      </div>
                    </div>
                  </div>
                  <div v-animate-onscroll.repeat="{ down: 'animated flip' }">
                    <div class="slide-content-box">
                      <div class="button">
                        <router-link
                          class="btn-style-six"
                          :to="{
                            name: 'lien-he',
                          }"
                        >
                          Liên hệ
                        </router-link>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </sequential-entrance>
            <!-- slow duration -->
          </div> </swiper-slide
        ><swiper-slide
          ><img src="../../src/assets/images/slider/6.jpg" />
          <div class="text-slider">
            <!-- slow duration -->
            <sequential-entrance v-show="this.showTextInslider">
              <div class="box" v-for="index in 1" :key="index">
                <h2>
                  Kênh thông tin Phòng Trọ <br />
                  số 1 Việt Nam
                </h2>
                <marquee-text :duration="30">
                  Kênh thông tin Phòng Trọ số 1 Việt Nam - Website đăng tin cho
                  thuê phòng trọ, nhà nguyên căn, căn hộ, ở ghép nhanh, hiệu quả
                  với 100.000+ tin đăng và 2.500.000 lượt xem mỗi tháng.
                </marquee-text>
                <div class="d-flex mt-2">
                  <div v-animate-onscroll.repeat="{ down: 'animated flip' }">
                    <div class="slide-content-box me-2">
                      <div class="button">
                        <router-link
                          class="btn-style-six"
                          :to="{
                            name: 'huong-dan',
                          }"
                        >
                          Giới thiệu
                        </router-link>
                      </div>
                    </div>
                  </div>
                  <div v-animate-onscroll.repeat="{ down: 'animated flip' }">
                    <div class="slide-content-box">
                      <div class="button">
                        <router-link
                          class="btn-style-six"
                          :to="{
                            name: 'lien-he',
                          }"
                        >
                          Liên hệ
                        </router-link>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </sequential-entrance>
          </div></swiper-slide
        ><swiper-slide
          ><img src="../../src/assets/images/slider/5.jpg" />
          <div class="text-slider">
            <!-- slow duration -->
            <sequential-entrance v-show="this.showTextInslider">
              <div class="box" v-for="index in 1" :key="index">
                <h2>
                  Kênh thông tin Phòng Trọ <br />
                  số 1 Việt Nam
                </h2>
                <marquee-text :duration="30">
                  Kênh thông tin Phòng Trọ số 1 Việt Nam - Website đăng tin cho
                  thuê phòng trọ, nhà nguyên căn, căn hộ, ở ghép nhanh, hiệu quả
                  với 100.000+ tin đăng và 2.500.000 lượt xem mỗi tháng.
                </marquee-text>
                <div class="d-flex mt-2">
                  <div v-animate-onscroll.repeat="{ down: 'animated flip' }">
                    <div class="slide-content-box me-2">
                      <div class="button">
                        <router-link
                          class="btn-style-six"
                          :to="{
                            name: 'huong-dan',
                          }"
                        >
                          Giới thiệu
                        </router-link>
                      </div>
                    </div>
                  </div>
                  <div v-animate-onscroll.repeat="{ down: 'animated flip' }">
                    <div class="slide-content-box">
                      <div class="button">
                        <router-link
                          class="btn-style-six"
                          :to="{
                            name: 'lien-he',
                          }"
                        >
                          Liên hệ
                        </router-link>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </sequential-entrance>
          </div></swiper-slide
        ><swiper-slide
          ><img src="../../src/assets/images/slider/3.jpg" />
          <div class="text-slider">
            <sequential-entrance v-show="this.showTextInslider">
              <div class="box" v-for="index in 1" :key="index">
                <h2>
                  Kênh thông tin Phòng Trọ <br />
                  số 1 Việt Nam
                </h2>
                <marquee-text :duration="30">
                  Kênh thông tin Phòng Trọ số 1 Việt Nam - Website đăng tin cho
                  thuê phòng trọ, nhà nguyên căn, căn hộ, ở ghép nhanh, hiệu quả
                  với 100.000+ tin đăng và 2.500.000 lượt xem mỗi tháng.
                </marquee-text>
                <div class="d-flex mt-2">
                  <div v-animate-onscroll.repeat="{ down: 'animated flip' }">
                    <div class="slide-content-box me-2">
                      <div class="button">
                        <router-link
                          class="btn-style-six"
                          :to="{
                            name: 'huong-dan',
                          }"
                        >
                          Giới thiệu
                        </router-link>
                      </div>
                    </div>
                  </div>
                  <div v-animate-onscroll.repeat="{ down: 'animated flip' }">
                    <div class="slide-content-box">
                      <div class="button">
                        <router-link
                          class="btn-style-six"
                          :to="{
                            name: 'lien-he',
                          }"
                        >
                          Liên hệ
                        </router-link>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </sequential-entrance>
          </div></swiper-slide
        >
      </swiper>
    </section>
    <section class="service-section padd-1">
      <div class="container">
        <div class="sec-title center">
          <h2>Danh sách <span> tin đăng</span></h2>
        </div>
        <NavSort
          @showType="show_type"
          @sortType="sort_type"
          @onPerPageChange="on_per_page_change"
          :page="this.page"
        ></NavSort>
        <div class="row" v-if="rooms && rooms.content">
          <div
            :class="
              showRooms
                ? 'col-md-4 col-sm-6 col-xs-12'
                : 'col-md-6 col-sm-6 col-xs-12'
            "
            v-for="room in rooms.content"
            :key="room.ID"
          >
            <ItemRoom :item="room" :showType="this.showRooms"></ItemRoom>
          </div>
        </div>
      </div>
      <div class="d-flex justify-content-center">
        <paginate
          v-if="rooms"
          :page-count="rooms.totalPages"
          :click-handler="onPageChange"
          :prev-text="'<'"
          :page-range="2"
          :next-text="'>'"
          :container-class="'pagination'"
        >
        </paginate>
      </div>
    </section>
    <section class="about-style-one mb-5">
      <div class="outer-box clearfix">
        <!--Content Column-->
        <div class="content-column clearfix">
          <div class="content-box" v-if="introHome && introHome.content">
            <div v-animate-onscroll.repeat="{ down: 'animated flip' }">
              <div>
                <!--Section Title-->
                <div class="sec-title">
                  <h2 v-html="introHome.content[0].title"></h2>
                </div>
                <div
                  class="text"
                  v-html="introHome.content[0].description"
                ></div>
                <div class="link-btn">
                  <a class="btn-style-two"
                    >Đăng Tin Ngay <span class="fa fa-long-arrow-right"></span
                  ></a>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!--Image Column-->
        <div class="image-column" style="">
          <!-- <div class="image"> -->
          <img
            style="height: 100%; object-fit: cover"
            src="../../src/assets/images/resource/about.jpg"
            alt=""
          />

          <!-- </div> -->
          <a
            href="https://www.youtube.com/watch?v=MKJEtkV_dhM"
            class="overlay-link video-fancybox"
            ><span class="icon fa fa-play"></span
          ></a>
        </div>
      </div>
    </section>
    <div class="sec-title center">
      <h2>Thành tựu <span> đạt được</span></h2>
    </div>
    <section class="fact-counter mt-5" style="">
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
    <Waypoint @change="run_sequential_entrance">
      <sequential-entrance
        fromBottom
        delay="2000"
        v-show="this.show_latter_news"
      >
        <section class="our-news padd-1">
          <div class="container">
            <div class="sec-title center">
              <h2>Bài viết <span>Mới nhất</span></h2>
            </div>

            <div class="row" v-if="blogs && blogs.content">
              <div
                class="col-md-4 col-sm-6"
                v-for="blog in blogs.content"
                :key="blog.ID"
              >
                <ItemBlog :item="blog"></ItemBlog>
              </div>
            </div>
          </div>
        </section>
      </sequential-entrance>
    </Waypoint>
  </div>
</template>

<script>
import NavbarFilter from "@/views/components/NavbarFilter.vue";
import ItemBlog from "@/views/components/ItemBlog.vue";
import ItemRoom from "@/views/components/ItemRoom.vue";
import NavSort from "@/views/components/SortRoom.vue";
import { EffectCube, Pagination, Autoplay, Navigation } from "swiper";
import { Swiper, SwiperSlide } from "swiper/vue";
import MarqueeText from "vue-marquee-text-component";
import Paginate from "vuejs-paginate-next";
import { Waypoint } from "vue-waypoint";
import { nextTick } from "vue";
import "swiper/css";

import "swiper/css/effect-cube";
import "swiper/css/pagination";
import "swiper/css/autoplay";
import "swiper/css/navigation";

export default {
  components: {
    NavbarFilter,
    ItemRoom,
    NavSort,
    Swiper,
    SwiperSlide,
    MarqueeText,
    Paginate,
    Waypoint,
    ItemBlog,
  },

  mounted() {
    this.$store.dispatch("fetchRooms", this.serverParams);
    this.$store.dispatch("fetchIntros", {
      filter: {
        page: 0,
        pageSize: 1,
        columnFilters: { types: "INTRO-HOME" },
        sort: [],
      },
    });
    this.$store.dispatch("fetchBlogs", {
      filter: {
        page: 0,
        pageSize: 3,
        columnFilters: {},
        sort: [{ field: "createAt", type: "desc" }],
      },
    });
  },

  computed: {
    rooms() {
      return this.$store.getters.getRooms;
    },
    introHome() {
      return this.$store.getters.getIntro;
    },
    blogs() {
      return this.$store.getters.getBlog;
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
    run_sequential_entrance(waypointState) {
      if (waypointState.going == "IN") {
        this.show_latter_news = true;
      } else {
        this.show_latter_news = false;
      }
    },
    filter(param) {
      this.serverParams.filter.columnFilters = {
        ...this.serverParams.filter.columnFilters,
        ...param,
      };
      nextTick(() => {
        this.$store.dispatch("fetchRooms", this.serverParams);
      });
    },
    sort_type(param) {
      if (param == "video") {
        this.serverParams.filter.sort = [];
        this.serverParams.filter.columnFilters.videoLink = "v";
      } else if (param == "latest") {
        this.serverParams.filter.columnFilters.videoLink = "";
        this.serverParams.filter.sort = [];
        this.updateParams({ sort: [{ field: "dateSubmited", type: "desc" }] });
      } else {
        this.serverParams.filter.columnFilters.videoLink = "";
        this.serverParams.filter.sort = [];
      }
      this.$store.dispatch("fetchRooms", this.serverParams);
    },
    on_per_page_change(param) {
      this.updateParams({ pageSize: param });
      this.$store.dispatch("fetchRooms", this.serverParams);
    },
    onSlideChange() {
      this.showTextInslider = false;
      setTimeout(() => {
        this.showTextInslider = true;
      }, 500);
    },
    show_type(e) {
      this.showRooms = e;
    },
    onPageChange(params) {
      this.updateParams({ page: params - 1 });
      this.$store.dispatch("fetchRooms", this.serverParams);
    },
    updateParams(newProps) {
      this.serverParams.filter = Object.assign(
        {},
        this.serverParams.filter,
        newProps
      );
    },
    triggerFade() {
      this.triggerFades = !this.triggerFades;
    },
  },

  data() {
    return {
      modules: [EffectCube, Pagination, Autoplay, Navigation],
      showRooms: true,
      sortRoomType: "default",
      animationCountNumber: false,
      show_latter_news: false,
      showTextInslider: true,
      serverParams: {
        filter: { page: 0, pageSize: 6, columnFilters: {}, sort: [] },
      },
    };
  },
};
</script>

<style scoped>
.fact-counter {
  background-image: url("../../src/assets/images/background/2.jpg");
}
.swiper {
  width: 100%;
  height: 550px;
}

.swiper-slide {
  background-position: center;
  background-size: cover;
  width: 100%;
  position: relative;
}
.text-slider {
  position: absolute;
  top: 30%;
  left: 10%;
  width: 50%;
  color: #fff;
}
.text-slider h2 {
  transition: none 0s ease 0s;
  line-height: 62px;
  border-width: 0px;
  margin: 14px 0px 9px;
  padding: 0px;
  letter-spacing: 0px;
  font-weight: 00;
  font-size: 45px;
}
.swiper-slide::before {
  position: absolute !important;
  content: "" !important;
  left: 0% !important;
  right: 0% !important;
  top: 0% !important;
  bottom: 0% !important;
  background: rgba(0, 0, 0, 0.28);
}

.swiper-slide img {
  display: block;
  width: 100%;
  height: 550px;
  object-fit: cover;
}
img {
  max-width: 100% !important;
}
</style>
