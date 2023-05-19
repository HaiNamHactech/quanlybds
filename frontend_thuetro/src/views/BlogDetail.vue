<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div>
    <section class="service-section padd-1">
      <div class="container">
        <div class="row">
          <div class="col-8">
            <section class="blog-section" v-if="blog && blog.content">
              <div
                class="large-blog-news single-blog-post single-blog wow fadeInUp animated"
                style="visibility: visible; animation-name: fadeInUp"
              >
                <div class="img-box">
                  <img :src="blog.content[0].image" alt="News" />
                </div>
                <div class="lower-content">
                  <div class="post-meta">
                    By {{ blog.content[0].createBy }} /
                    {{ blog.content[0].updateAt }} / 03 Comments
                  </div>
                  <h4>
                    {{ blog.content[0].title }}
                  </h4>

                  <div class="text">
                    {{ blog.content[0].detailDescription }}
                  </div>
                </div>
              </div>

              <!--Comments Area-->
              <div class="comments-area">
                <div class="inner-title">
                  <h3>(3) Bình luận</h3>
                </div>

                <!--Comment Box-->
                <div class="comment-box">
                  <div class="comment">
                    <div class="author-thumb">
                      <img
                        src="../../src/assets/images/blog/author-1.jpg"
                        alt=""
                      />
                    </div>
                    <div class="comment-inner">
                      <div class="comment-info clearfix">
                        <strong>Sunzida Akon</strong><br />
                        <div class="comment-time">
                          March 22, 2018 at 11:45 AM /
                          <a class="comment-reply" href="#">Reply</a>
                        </div>
                      </div>

                      <div class="text">
                        Ne erat velit invidunt his. Eum in dicta veniam
                        interesset, harum fuisset te nam, ea cum lupta
                        definitionem. Vocibus suscipit prodesset vim ei, equidem
                        perpetua eu per.
                      </div>
                    </div>
                  </div>
                </div>

                <!--Reply Comment Box-->
                <div class="comment-box reply-comment">
                  <div class="comment">
                    <div class="author-thumb">
                      <img
                        src="../../src/assets/images/blog/author-2.jpg"
                        alt=""
                      />
                    </div>
                    <div class="comment-inner">
                      <div class="comment-info clearfix">
                        <strong>Robert Hafi </strong><br />
                        <div class="comment-time">
                          March 22, 2018 at 11:45 AM /
                          <a class="comment-reply" href="#">Reply</a>
                        </div>
                      </div>

                      <div class="text">
                        Ne erat velit invidunt his. Eum in dicta veniam
                        interesset, harum fuisset te nam, ea cum lupta
                        definitionem. Vocibus suscipit prodesset vim ei, equidem
                        perpetua eu per.
                      </div>
                    </div>
                  </div>
                </div>

                <!--Reply Comment Box-->
                <div class="comment-box reply-comment three">
                  <div class="comment">
                    <div class="author-thumb">
                      <img
                        src="../../src/assets/images/blog/author-3.jpg"
                        alt=""
                      />
                    </div>
                    <div class="comment-inner">
                      <div class="comment-info clearfix">
                        <strong>Juthi Sarra </strong><br />
                        <div class="comment-time">
                          March 22, 2018 at 11:45 AM /
                          <a class="comment-reply" href="#">Reply</a>
                        </div>
                      </div>

                      <div class="text">
                        Ne erat velit invidunt his. Eum in dicta veniam
                        interesset, harum fuisset te nam, ea cum lupta
                        definitionem. Vocibus suscipit prodesset vim ei, equidem
                        perpetua eu per.
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <div class="add_your_review">
                <div class="inner-title">
                  <h3>Để lại bình luận</h3>
                </div>
                <form action="#">
                  <div class="row">
                    <div class="col-md-6 col-sm-12 col-xs-12">
                      <label>Họ Và Tên</label>
                      <input type="text" placeholder="" />
                    </div>

                    <div class="col-md-6 col-sm-12 col-xs-12">
                      <label>Email</label>
                      <input type="email" placeholder="" />
                    </div>
                    <div class="col-md-12 col-sm-12 col-xs-12">
                      <label>Nội dung</label>
                      <textarea placeholder=""></textarea>
                    </div>
                  </div>
                  <button class="btn-style-two">Gửi đi</button>
                </form>
              </div>
            </section>
          </div>
          <div class="col-4">
            <div class="sidebar_search sidebar-padd mb-4">
              <input
                type="text"
                v-model="this.serverParams.filter.columnFilters.title"
                placeholder="Search title..."
              />
              <button
                type="button"
                class="tran3s color1_bg"
                @click="filterByTitle()"
              >
                <i class="fa fa-search" aria-hidden="true"></i>
              </button>
            </div>

            <div class="list-price-filter mb-4">
              <div class="sec-title mb-4">
                <h2 style="font-size: 18px">Danh mục <span> cho thuê</span></h2>
              </div>
              <div class="row">
                <ul v-if="categoryType">
                  <li
                    class="d-flex align-items-center text-category mb-2"
                    v-for="cate in categoryType"
                    :key="cate.ID"
                  >
                    <router-link
                      :to="{
                        name: cate.slug,
                      }"
                    >
                      <vue-feather
                        type="chevron-right"
                        class="me-1"
                        stroke="gray"
                        stroke-width="1"
                        size="16"
                      ></vue-feather>
                      {{ cate.name }}
                    </router-link>
                  </li>
                </ul>
              </div>
            </div>
            <div class="list-price-filter">
              <div class="sec-title mb-4">
                <h2 style="font-size: 18px">Tin <span> mới đăng</span></h2>
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
            <div class="list-price-filter mb-4">
              <div class="sec-title mb-4">
                <h2 style="font-size: 18px">
                  Có thể <span> bạn quan tâm</span>
                </h2>
              </div>
              <div class="row"></div>
            </div>
          </div>
        </div>
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
import ItemRoomExtra from "@/views/components/ItemRoomExtra.vue";
import { Waypoint } from "vue-waypoint";

export default {
  components: {
    Waypoint,
    ItemRoomExtra,
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
    this.getBlog();
    this.getRoomLater();
  },
  computed: {
    category() {
      return this.$store.getters.getCategory;
    },
    categoryType() {
      var categorys = this.$store.getters.getCategory;
      if (categorys && categorys.content) {
        return categorys.content.filter((m) => m.type === "duong-dan-nhanh");
      }
      return null;
    },

    blog() {
      return this.$store.getters.getBlog;
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
    filterByTitle() {
      this.getBlog();
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
    getBlog() {
      var slugRoom = this.$route.params.blogDetail.split("-");
      if (slugRoom.length > 0) {
        this.serverParams.filter.columnFilters.id =
          slugRoom[slugRoom.length - 1];
        this.$store.dispatch("fetchBlogs", this.serverParams);
      }
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
.img-box img {
  width: 100% !important;
  max-width: none !important;
  height: 500px;
  object-fit: cover;
}
</style>
