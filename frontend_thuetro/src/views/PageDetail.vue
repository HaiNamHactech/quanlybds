<template>
  <div>
    <section
      class="page-detail mainmenu-area"
      v-if="this.navbarfixed"
      :class="this.navbarfixed ? 'sticky' : ''"
    >
      <div class="container" style="width: 74%" v-if="room">
        <div class="row justify-content-between align-items-cnter">
          <div class="col-5">
            <div class="d-flex align-items-cnter">
              <span class="price-news"
                >{{
                  new Intl.NumberFormat("vi-VN", {
                    currency: "VND",
                  }).format(room.price)
                }}/tháng</span
              >
              <span class="detail-info-room ms-1 me-1"
                ><vue-feather
                  class="ms-3 me-1"
                  size="16"
                  stroke="gray"
                  :type="'maximize'"
                ></vue-feather
                >{{ room.area }}m²</span
              >
            </div>
            <p class="mb-0" style="font-size: 14px">
              <span>{{ room.wardName }}</span
              ><span>, {{ room.districtName }}</span>
              <span>, {{ room.consciousName }}.</span>
            </p>
          </div>
          <div class="col-7 d-flex justify-content-end align-items-center">
            <div class="button ms-2">
              <a class="btn-style-five btn-extra btn-nav">
                <vue-feather
                  class="me-2"
                  :type="'heart'"
                  :size="16"
                ></vue-feather>
                Yêu thích</a
              >
            </div>
            <div class="button ms-2">
              <a class="btn-style-five btn-extra btn-nav">
                <!-- <img
                  class="me-2"
                  style="width: 8%"
                  src="../../src/assets/images/icon-zalo.png"
                  alt=""
                /> -->
                nhắn zalo
              </a>
            </div>
            <div class="button ms-2">
              <a class="btn-style-five btn-nav-phone">
                <vue-feather class="me-2" :type="'phone-call'"></vue-feather>
                {{ room.phoneNumber }}</a
              >
            </div>
          </div>
        </div>
      </div>
    </section>
    <section class="service-single mt-4">
      <div class="container">
        <div class="service-details">
          <div class="row" v-if="room">
            <div class="col-md-8 col-sm-8">
              <div class="slider-img-detail">
                <swiper
                  style="height: 80%"
                  :style="{
                    '--swiper-navigation-color': '#fff',
                    '--swiper-pagination-color': '#fff',
                  }"
                  :loop="true"
                  :spaceBetween="10"
                  :navigation="true"
                  :thumbs="{ swiper: thumbsSwiper }"
                  class="mySwiper2"
                >
                  <swiper-slide><img :src="room.avatar" /></swiper-slide>
                  <swiper-slide v-for="img in room.listImgExtra" :key="img">
                    <img :src="img" />
                  </swiper-slide>
                </swiper>
                <swiper
                  style="height: 20%"
                  @swiper="setThumbsSwiper"
                  :loop="true"
                  :spaceBetween="10"
                  :slidesPerView="4"
                  :freeMode="true"
                  :watchSlidesProgress="true"
                  class="mySwiper pt-2"
                >
                  <swiper-slide><img :src="room.avatar" /></swiper-slide>
                  <swiper-slide v-for="img in room.listImgExtra" :key="img">
                    <img :src="img" />
                  </swiper-slide>
                </swiper>
              </div>
              <div class="content-news">
                <div class="content-one">
                  <div class="title-news d-flex mt-3">
                    <ul class="rating ps-0 d-flex">
                      <li v-for="i in room.rateService" :key="i">
                        <i class="fa fa-star"></i>
                      </li>
                    </ul>
                    <p :style="{ color: room.colorTilteService }">
                      {{ room.title }}
                    </p>
                  </div>
                  <ul class="ps-0 list-info-news">
                    <li class="category-news">
                      Chuyên mục:
                      <span
                        class="ms-1"
                        @click="movePage()"
                        :style="{ color: room.colorTilteService }"
                      >
                        {{ room.categoryName }}</span
                      >
                    </li>
                    <li>
                      <vue-feather
                        class="me-1"
                        stroke-width="2"
                        size="16"
                        stroke="gray"
                        :type="'map-pin'"
                      ></vue-feather>
                      <span class="me-1">Địa chỉ: </span>
                      <span> {{ room.specificAddress }} </span>
                      <span>, {{ room.wardName }}</span
                      ><span>, {{ room.districtName }}</span>
                      <span>, {{ room.consciousName }}.</span>
                    </li>
                    <li>
                      <vue-feather
                        class="me-1"
                        size="16"
                        stroke="gray"
                        :type="'tag'"
                      ></vue-feather>
                      <span class="price-news"
                        >{{
                          new Intl.NumberFormat("vi-VN", {
                            currency: "VND",
                          }).format(room.price)
                        }}/tháng</span
                      >
                      <span class="detail-info-room"
                        ><vue-feather
                          class="me-2 ms-5"
                          size="16"
                          stroke="gray"
                          :type="'maximize'"
                        ></vue-feather
                        >{{ room.area }}m²</span
                      >
                      <span class="detail-info-room"
                        ><vue-feather
                          class="me-2"
                          size="16"
                          stroke="gray"
                          :type="'clock'"
                        ></vue-feather>
                        {{ room.dateSubmited }}</span
                      >
                    </li>
                  </ul>
                </div>
                <div class="content-two mt-4">
                  <div class="inner-title">
                    <h3 class="title-content">Thông tin mô tả</h3>
                  </div>
                  <div class="description-detail">
                    <ul class="ps-0" v-html="room.description"></ul>
                  </div>
                </div>
                <div class="content-three mt-4">
                  <div class="inner-title">
                    <h3 class="title-content">Đặc điểm tin đăng</h3>
                  </div>
                  <table class="table table-striped">
                    <tbody>
                      <tr>
                        <td style="width: 25%">Khu vực :</td>
                        <td>
                          <span>{{ room.wardName }}</span
                          ><span>, {{ room.districtName }}</span>
                          <span>, {{ room.consciousName }}.</span>
                        </td>
                      </tr>
                      <tr>
                        <td style="width: 25%">Loại tin rao :</td>
                        <td>{{ room.categoryName }}</td>
                      </tr>
                      <tr>
                        <td style="width: 25%">Đối tượng thuê :</td>
                        <td>{{ room.subjectId }}</td>
                      </tr>
                      <tr>
                        <td style="width: 25%">Gói tin :</td>
                        <td
                          :style="{
                            color: room.colorTilteService,
                            'font-weight': 600,
                            'text-transform': uppercase,
                          }"
                        >
                          {{ room.titleService }}
                        </td>
                      </tr>
                      <tr>
                        <td style="width: 25%">Ngày đăng :</td>
                        <td>{{ room.dateSubmited }}</td>
                      </tr>
                      <tr>
                        <td style="width: 25%">Ngày hết hạn :</td>
                        <td>{{ room.dateExpiration }}</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="content-three mt-4">
                  <div class="inner-title">
                    <h3 class="title-content">Thông tin liên hệ</h3>
                  </div>
                  <table class="table table-striped">
                    <tbody>
                      <tr>
                        <td style="width: 25%">Liên hệ :</td>
                        <td>{{ room.createBy }}</td>
                      </tr>
                      <tr>
                        <td style="width: 25%">Zalo :</td>
                        <td>{{ room.phoneNumber }}</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="content-three mt-4 mb-5">
                  <div class="inner-title">
                    <h3 class="title-content">Bản đồ</h3>
                  </div>
                  <span
                    >Địa chỉ: <span> {{ room.specificAddress }} </span>
                    <span>, {{ room.wardName }}</span
                    ><span>, {{ room.districtName }}</span>
                    <span>, {{ room.consciousName }}.</span></span
                  >
                  <div class="map mt-3 mb-1" v-html="room.map">     
                  </div>
                  <p>
                    Mọi thông tin liên quan đến tin đăng này chỉ mang tính chất
                    tham khảo. Nếu bạn có phản hồi với tin đăng này (báo xấu,
                    tin đã cho thuê, không liên lạc được,...), vui lòng thông
                    báo để PhòngTrọ123 có thể xử lý.
                  </p>
                  <button
                    type="button"
                    class="btn btn-outline-primary mt-2 d-flex align-items-center"
                  >
                    <vue-feather
                      class="me-2"
                      stroke-width="1"
                      size="20"
                      type="send"
                      stroke="#007bff"
                    ></vue-feather
                    ><span
                      ><router-link
                        :to="{
                          name: 'lien-he',
                        }"
                        >Gửi phản hồi</router-link
                      ></span
                    >
                  </button>
                </div>
              </div>
              <div
                class="list-news-relative service-section mt-3"
                v-if="roomRelation"
              >
                <div class="inner-title">
                  <h3 class="title-content">
                    Cho thuê phòng trọ Quận Gò Vấp, Hồ Chí Minh
                  </h3>
                </div>
                <div class="mt-3" v-for="room in roomRelation" :key="room.ID">
                  <ItemRoom :item="room" :showType="false"></ItemRoom>
                </div>
              </div>
            </div>

            <div class="col-md-4 col-sm-4 service-section">
              <div class="info-person-news">
                <div>
                  <center>
                    <img class="img-person" :src="room.avatarUser" alt="" />
                    <h4
                      class="mt-3"
                      style="font-weight: 500; text-transform: uppercase"
                    >
                      {{ room.createBy }}
                    </h4>
                    <div
                      class="d-flex justify-content-center align-items-center"
                    >
                      <span
                        class="dot-active"
                        style="background-color: #16c784"
                      ></span>
                      <p class="mb-0 ms-2">Đang hoạt động</p>
                    </div>
                  </center>
                </div>
                <div class="list-contact">
                  <div class="button mb-3">
                    <a class="btn-style-five">
                      <vue-feather
                        class="me-2"
                        :type="'phone-call'"
                      ></vue-feather>
                      {{ room.phoneNumber }}</a
                    >
                  </div>
                  <div class="button mb-3">
                    <a class="btn-style-five btn-extra">
                      <img
                        class="me-2"
                        style="width: 8%"
                        src="../../src/assets/images/icon-zalo.png"
                        alt=""
                      />
                      Nhắn Zalo</a
                    >
                  </div>
                  <div class="button mb-3">
                    <a class="btn-style-five btn-extra">
                      <vue-feather class="me-2" :type="'heart'"></vue-feather>
                      Yêu thích</a
                    >
                  </div>
                </div>
              </div>
              <div
                class="col-news-extra mt-3"
                v-if="roomRoomOutstanding && roomRoomOutstanding.content"
              >
                <div data-v-8a883aea="" class="sec-title mb-4">
                  <h2 data-v-8a883aea="" style="font-size: 18px">
                    Tin<span data-v-8a883aea=""> Nổi Bật</span>
                  </h2>
                </div>
                <div v-for="room in roomRoomOutstanding.content" :key="room.ID">
                  <ItemRoomExtra :item="room"></ItemRoomExtra>
                </div>
              </div>
              <div
                class="col-news-extra mt-3"
                v-if="roomLater && roomLater.content"
              >
                <div data-v-8a883aea="" class="sec-title mb-4">
                  <h2 data-v-8a883aea="" style="font-size: 18px">
                    Tin<span data-v-8a883aea=""> Mới đăng</span>
                  </h2>
                </div>

                <div v-for="room in roomLater.content" :key="room.ID">
                  <ItemRoomExtra :item="room"></ItemRoomExtra>
                </div>
              </div>
              <div class="col-news-extra mt-3">
                <div data-v-8a883aea="" class="sec-title mb-4">
                  <h2 data-v-8a883aea="" style="font-size: 18px">
                    Bài viết<span data-v-8a883aea=""> Mới</span>
                  </h2>
                </div>

                <div class="row" v-if="blogs && blogs.content">
                  <div
                    class="col-12"
                    v-for="blog in blogs.content"
                    :key="blog.ID"
                  >
                    <ItemBlogExtra :item="blog"></ItemBlogExtra>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>
<script>
import { Swiper, SwiperSlide } from "swiper/vue";
import ItemRoomExtra from "@/views/components/ItemRoomExtra.vue";
import ItemBlogExtra from "@/views/components/ItemBlogExtra.vue";
import "swiper/css";
import "swiper/css/free-mode";
import "swiper/css/navigation";
import "swiper/css/thumbs";
import ItemRoom from "@/views/components/ItemRoom.vue";
import SwiperCore, { FreeMode, Navigation, Thumbs } from "swiper";
SwiperCore.use([FreeMode, Navigation, Thumbs]);
// import { nextTick } from "vue";

export default {
  components: {
    Swiper,
    SwiperSlide,
    ItemRoomExtra,
    ItemBlogExtra,
    ItemRoom,
  },

  watch: {
    $route: function () {
      this.getRoom();
    },
  },
  mounted() {
    window.addEventListener("scroll", this.handleScroll);
    this.getRoom();
    this.getRoomLater();
    this.getRoomOutstanding();
    this.getBlog();
  },
  unmounted() {
    window.removeEventListener("scroll", this.handleScroll);
  },
  computed: {
    room() {
      var room = this.$store.getters.getRooms;
      if (room && room.content) {
        if (room.content[0].listImgExtra) {
          room.content[0].listImgExtra =
            room.content[0].listImgExtra.split("!");
        }
        this.getRoomRelation(room.content[0].categoryId);
        return room.content[0];
      }
      return null;
    },
    roomLater() {
      return this.$store.getters.getRoomLater;
    },
    roomRoomOutstanding() {
      return this.$store.getters.getRoomOutstanding;
    },
    roomRelation() {
      var roomRelations = this.$store.getters.getRoomRelation;
      if (roomRelations && roomRelations.content) {
        console.log(roomRelations.content.filter((x) => x.id != this.room.id));
        return roomRelations.content.filter((x) => x.id != this.room.id);
      }
      return null;
    },
    blogs() {
      return this.$store.getters.getBlog;
    },
  },
  methods: {
    setThumbsSwiper(swiper) {
      this.thumbsSwiper = swiper;
    },
    getRoom() {
      var slugRoom = this.$route.params.slugPageAfter.split("-");
      if (slugRoom.length > 0) {
        this.serverParams.filter.columnFilters.id =
          slugRoom[slugRoom.length - 1];
        this.$store.dispatch("fetchRooms", this.serverParams);
      }
    },
    movePage() {
      this.$route.params.slugPageBefore;
      this.$router.push({
        name: this.$route.params.slugPageBefore,
      });
    },
    getRoomLater() {
      this.$store.dispatch("fetchRoomLater", {
        filter: {
          page: 0,
          pageSize: 4,
          columnFilters: {},
          sort: [{ field: "dateSubmited", type: "desc" }],
        },
      });
    },
    getRoomOutstanding() {
      this.$store.dispatch("fetchRoomOutstanding", {
        filter: {
          page: 0,
          pageSize: 5,
          columnFilters: { rateServiceNot: 0 },
          sort: [{ field: "rateService", type: "desc" }],
        },
      });
    },
    getRoomRelation(categoryId) {
      this.$store.dispatch("fetchRoomRelation", {
        filter: {
          page: 0,
          pageSize: 0,
          columnFilters: { categoryId: categoryId },
          sort: [],
        },
      });
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
    handleScroll() {
      if (window.scrollY > 180) {
        this.navbarfixed = true;
      } else {
        this.navbarfixed = false;
      }
    },
  },

  data() {
    return {
      navbarfixed: false,
      thumbsSwiper: null,
      serverParams: {
        filter: { page: 0, pageSize: 1, columnFilters: {}, sort: [] },
      },
    };
  },
};
</script>

<style scoped>
.list-news-relative {
  width: 100%;
  background: #fff;
  padding: 15px;
  border-radius: 8px;
  border: 1px solid #dedede;
}
.slider-img-detail,
.info-person-news {
  height: 400px !important;
}
.info-person-news {
  width: 100%;
  background: #febb02;
  border-radius: 8px;
  padding: 20px 15px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-direction: column;
}
.img-person {
  width: 80px;
  height: 80px;
  border-radius: 50%;
}
.dot-active {
  width: 7px;
  height: 7px;
  border-radius: 50%;
}

img {
  max-width: 100% !important;
  object-fit: cover;
}
.swiper-slide.swiper-slide-visible.swiper-slide-active.swiper-slide-thumb-active::after,
.swiper-slide.swiper-slide-visible.swiper-slide-thumb-active::after {
  position: absolute !important;
  content: "" !important;
  top: 0% !important;
  bottom: 0% !important;
  left: 0% !important;
  right: 0% !important;
  background: rgba(0, 0, 0, 0.28);
  border: 3px solid #77ba00;
}
.info-person-news .button {
  width: 100%;
}
.list-contact {
  width: 100%;
}
.btn-style-five {
  padding: 8px 29px !important;
  border-radius: 8px !important;
  background: linear-gradient(to right, #16c784, #16c784) !important;
  border: none !important;
  align-items: center;
  display: flex;
  justify-content: center;
  font-size: 20px !important;
  font-weight: 600 !important;
  cursor: pointer !important;
}
.btn-nav {
  padding: 5px 10px !important;
}
.btn-nav-phone {
  padding: 7px 10px !important;
}
.btn-style-five:hover {
  color: #fff !important;
  background: #13bb7b !important;
}
.btn-style-five.btn-extra {
  font-size: 13px !important;
  background: #fff !important;
  color: #000 !important;
  border: 1px solid #000 !important;
}
.btn-style-five.btn-extra:hover {
  text-decoration: underline !important;
}
.rating li i {
  font-size: 18px;
  color: #ffd454;
  margin-right: 2px;
}
.title-news p {
  font-size: 20px;
  color: #ff6600;
  font-weight: 700;
  margin-left: 6px;
  margin-bottom: 0px;
}
.category-news span {
  font-weight: bold;
  color: #ff6600;
  text-decoration: underline;
  cursor: pointer;
}
.category-news span:hover {
  color: #77ba00;
}
.list-info-news li {
  font-size: 15px !important;
  margin-top: 10px;
  font-weight: 500;
  display: flex;
  align-items: center;
}
.price-news {
  font-size: 20px;
  font-weight: bold;
  color: #77ba00;
}
.detail-info-room {
  margin: 0 35px;
  display: flex;
  align-items: center;
}
.title-content {
  font-size: 20px;
  font-weight: 600;
}
.table-striped {
  font-size: 15px;
}
.btn:hover {
  background: transparent !important;
  text-decoration: underline;
  color: #007bff;
}
.col-news-extra {
  width: 100%;
  background: #f5f5f5;
  padding: 15px;
  border-radius: 8px;
  border: 1px solid #dedede;
}
.page-detail.mainmenu-area {
  background: #fff !important;
}
#navbarNav {
  background: #fff !important;
  color: #000 !important;
  padding: 15px;
}
.sticky {
  position: fixed;
  top: 40px;
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
    top: 20px;
    opacity: 0;
  }
  to {
    top: 40px;
    opacity: 1;
  }
}
iframe {
  width:100%!important;
  height: 400px!important;
}

</style>
