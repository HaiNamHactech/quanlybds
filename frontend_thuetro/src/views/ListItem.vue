<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div>
    <NavbarFilter
      @columnFilter="filter"
      
      :priceFilter="this.serverParams.filter.columnFilters.price"
      :areaFilter="this.serverParams.filter.columnFilters.acreage"
    />
    <div class="inner-banner has-base-color-overlay text-center mt-3">
      <div class="container">
        <div class="box">
          <h3 :class="this.zoom ? 'zoom-in-zoom-out' : ''">
            <template v-if="this.menu"> {{ this.menu.name }}</template>
          </h3>
        </div>
        <!-- /.box -->
      </div>
      <!-- /.container -->
    </div>
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
        <div class="row">
          <div class="col-8">
            <div class="row" v-if="rooms && rooms.content">
              <div
                :class="
                  showRooms
                    ? 'col-md-6 col-sm-6 col-xs-12'
                    : 'col-md-12 col-sm-12 col-xs-12'
                "
                v-for="room in rooms.content"
                :key="room.ID"
              >
                <ItemRoom :item="room" :showType="this.showRooms"></ItemRoom>
              </div>
            </div>
          </div>
          <div class="col-4">
            <div class="list-price-filter">
              <div class="sec-title mb-4">
                <h2 style="font-size: 18px">Xem <span> theo giá</span></h2>
              </div>
              <div class="row" v-if="prices">
                <div class="col-6" v-for="price in prices" :key="price.ID">
                  <p
                    class="item-filter d-flex align-items-center"
                    @click="
                      filterByPrice(
                        price.id + '-' + price.priceForm + '-' + price.priceTo
                      )
                    "
                  >
                  <vue-feather type="chevron-right" class="me-1" stroke="gray" stroke-width="1" size="16"></vue-feather> {{ price.title }}
                  </p>
                </div>
              </div>
            </div>
            <div class="list-price-filter mt-4">
              <div class="sec-title mb-4">
                <h2 style="font-size: 18px">
                  Xem <span> theo diện tích</span>
                </h2>
              </div>
              <div class="row" v-if="acreages">
                <div
                  class="col-6"
                  v-for="acreage in acreages"
                  :key="acreage.ID"
                >
                  <p
                    class="item-filter d-flex align-items-center"
                    @click="
                      filterByArea(
                        acreage.id +
                          '-' +
                          acreage.areaFrom +
                          '-' +
                          acreage.areaTo
                      )
                    "
                  >
                  <vue-feather type="chevron-right" class="me-1" stroke="gray" stroke-width="1" size="16"></vue-feather> {{ acreage.title }}
                  </p>
                </div>
              </div>
            </div>
            <div class="list-price-filter mt-4">
              <div class="sec-title mb-4">
                <h2 style="font-size: 18px">Tin<span> Mới Đăng</span></h2>
              </div>
              <div class="row" v-if="roomLater && roomLater.content">
                <div
                  class="col-12"
                  v-for="room in roomLater.content"
                  :key="room.ID"
                >
                <ItemRoomExtra :item="room"></ItemRoomExtra>
                </div>
              </div>
            </div>
            <div class="list-price-filter mt-4">
              <div class="sec-title mb-4">
                <h2 style="font-size: 18px">Bài Viết<span> Mới</span></h2>
              </div>
              <div class="row" v-if="blogs && blogs.content">
                <div
                  class="col-12"
                  v-for="blog in blogs.content"
                  :key="blog.ID"
                >
                  <!-- <div class="single-item list single-item-extra d-flex mb-3">
                    <div class="img-box" style="width: 30%">
                      <img
                        src="../../src/assets/images/service/service-1.jpg"
                        alt="awesome-image"
                      />
                    </div>
                    <div
                      class="service-content pt-0 pb-0 ps-2 pe-2"
                      style="width: 70%"
                    >
                      <div class="title-post d-flex">
                        <p
                          data-toggle="tooltip"
                          data-placement="top"
                          :title="blog.title"
                        >
                          {{ blog.shortDescription }}
                        </p>
                      </div>
                      <div class="text" style="width: 100%">
                        <div class="row">
                          <div class="col-7 price_room">
                            <div class="link-btn5 mt-0">
                              <a
                                class="btn-style-eight hvr-icon-wobble-horizontal"
                                >Read More</a
                              >
                            </div>
                          </div>
                          <div
                            class="col-5 time_submit_room"
                            style="text-align: right"
                          >
                            {{ blog.createAt }}
                          </div>
                        </div>
                      </div>
                    </div>
                  </div> -->
                  <ItemBlogExtra :item="blog"></ItemBlogExtra>
                </div>
              </div>
            </div>
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
    <div class="sec-title center">
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
import NavbarFilter from "@/views/components/NavbarFilter.vue";
import ItemRoom from "@/views/components/ItemRoom.vue";
import ItemRoomExtra from "@/views/components/ItemRoomExtra.vue";
import ItemBlogExtra from "@/views/components/ItemBlogExtra.vue";
import NavSort from "@/views/components/SortRoom.vue";
import Paginate from "vuejs-paginate-next";
import { Waypoint } from "vue-waypoint";
import { nextTick } from "vue";

export default {
  components: {
    NavbarFilter,
    ItemRoom,
    NavSort,
    Paginate,
    Waypoint,
    ItemRoomExtra,
    ItemBlogExtra
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
      this.getRoom();
    },
  },

  mounted() {
    this.getRoom();
    this.getBlog();
    this.getRoomLater();
  },
  computed: {
    rooms() {
      return this.$store.getters.getRooms;
    },
    blogs() {
      return this.$store.getters.getBlog;
    },
    category() {
      return this.$store.getters.getCategory;
    },
    prices() {
      var prices = this.$store.getters.getPriceOrArea;
      var prices_render = null;
      if (prices && prices.content) {
        prices_render = prices.content.filter((m) => m.types == "PRICE");
      }
      return prices_render;
    },
    acreages() {
      var acreages = this.$store.getters.getPriceOrArea;
      var acreages_render = null;
      if (acreages && acreages.content) {
        acreages_render = acreages.content.filter((m) => m.types == "AREA");
      }
      return acreages_render;
    },
    roomLater() {
      return this.$store.getters.getRoomLater;
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
      this.getRoom();
    },
    on_per_page_change(param) {
      this.updateParams({ pageSize: param });
      this.$store.dispatch("fetchRooms", this.serverParams);
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
    animationZoom() {
      setTimeout(() => {
        this.zoom = false;
      }, 1800);
      this.zoom = true;
    },
    filterByPrice(val) {
      this.serverParams.filter.columnFilters.price = val;
      this.getRoom();
    },
    filterByArea(val) {
      this.serverParams.filter.columnFilters.acreage = val;
      this.getRoom();
    },
    getRoom() {
      this.serverParams.filter.columnFilters.categoryId = this.menu.id;
      this.$store.dispatch("fetchRooms", this.serverParams);
    },
    getBlog() {
      this.$store.dispatch("fetchBlogs", {
        filter: {
          page: 0,
          pageSize: 4,
          columnFilters: {},
          sort: [{ field: "createAt", type: "desc" }],
        },
      });
    },
    getRoomLater() {
      this.$store.dispatch("fetchRoomLater", {
        filter: {
          page: 0,
          pageSize: 3,
          columnFilters: {},
          sort: [{ field: "dateSubmited", type: "desc" }],
        },
      });
    },
  },

  data() {
    return {
      showRooms: true,
      sortRoomType: "default",
      animationCountNumber: false,
      menu: null,
      zoom: false,
      serverParams: {
        filter: { page: 0, pageSize: 6, columnFilters: {}, sort: [] },
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

</style>
