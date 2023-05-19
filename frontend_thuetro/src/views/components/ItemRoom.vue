<template>
  <div :class="this.zoom ? 'zoom-in-zoom-out' : ''" v-if="showType">
    <div class="single-item grid" v-if="item">
      <div class="img-box">
        <img @click="showDetail(item)" :src="item.avatar" alt="awesome-image" />
        <vue-feather
          data-toggle="tooltip"
          data-placement="top"
          title="Yêu thích"
          type="heart"
          size="22"
          stroke="#f73859"
          stroke-width="2"
          class="me-2 icon-heart"
        ></vue-feather>
      </div>
      <div class="service-content">
        <div class="title-post d-flex">
          <ul class="rating ms-0">
            <li v-for="i in item.rateService" :key="i">
              <i class="fa fa-star"></i>
            </li>
          </ul>
          <p @click="showDetail(item)">
            <a
              :style="{ color: item.colorTilteService }"
              data-toggle="tooltip"
              data-placement="top"
              :title="item.title"
              >{{ item.title }}
            </a>
          </p>
        </div>
        <div class="text" style="width: 100%">
          <div class="row">
            <div class="col-4 price_room">
              {{
                new Intl.NumberFormat("vi-VN", { currency: "VND" }).format(
                  item.price
                )
              }}/tháng
            </div>
            <div class="col-2">{{ item.area }}m²</div>
            <div class="col-6">
              <p
                class="address"
                data-placement="top"
                :title="item.districtName + ',' + item.consciousName + '.'"
              >
                {{ item.districtName }}, {{ item.consciousName }}.
              </p>
            </div>
          </div>
        </div>
        <div class="d-flex justify-content-between">
          <div class="d-flex">
            <img
              style="width: 30px; height: 30px; border-radius: 50%"
              :src="item.avatarUser"
            />
            <p class="ms-1 mb-2 name-person">{{ item.updateBy }}</p>
          </div>
          <p class="name-person" style="font-size: 14px">
            {{ item.dateSubmited }}
          </p>
        </div>
      </div>
    </div>
  </div>
  <div :class="this.zoom ? 'zoom-in-zoom-out' : ''" v-else>
    <div class="single-item list" v-if="item">
      <div class="img-box">
        <img @click="showDetail(item)" :src="item.avatar" alt="awesome-image" />
        <vue-feather
          data-toggle="tooltip"
          data-placement="top"
          title="Yêu thích"
          type="heart"
          size="22"
          stroke="#f73859"
          stroke-width="2"
          class="me-2 icon-heart"
        ></vue-feather>
      </div>
      <div class="service-content">
        <div class="title-post d-flex">
          <ul class="rating ms-0">
            <li v-for="i in item.rateService" :key="i">
              <i class="fa fa-star"></i>
            </li>
          </ul>
          <p @click="showDetail(item)">
            <a
              :style="{ color: item.colorTilteService }"
              data-toggle="tooltip"
              data-placement="top"
              :title="item.title"
              >{{ item.title }}
            </a>
          </p>
        </div>
        <div class="text" style="width: 100%">
          <div class="row">
            <div class="col-4 price_room">
              {{
                new Intl.NumberFormat("vi-VN", { currency: "VND" }).format(
                  item.price
                )
              }}/tháng
            </div>
            <div class="col-2">{{ item.area }}m²</div>
            <div class="col-6">
              <p
                class="address"
                data-placement="top"
                :title="item.districtName + ',' + item.consciousName + '.'"
              >
                {{ item.districtName }}, {{ item.consciousName }}.
              </p>
            </div>
          </div>
        </div>
        <div class="description">
          <p class="description-room">
            {{ item.sortDescription }}
          </p>
        </div>
        <div class="d-flex justify-content-between">
          <div class="d-flex">
            <img
              style="width: 30px; height: 30px; border-radius: 50%"
              :src="item.avatarUser"
              alt=""
            />
            <p class="ms-1 mb-2 name-person">{{ item.updateBy }}</p>
          </div>
          <p class="name-person" style="font-size: 14px">
            {{ item.dateSubmited }}
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  props: {
    showType: { default: true },
    item: {},
  },
  created() {
    this.slugBefore = this.$route.name;
  },
  watch: {
    $route: function () {
      this.slugBefore = this.$route.name;
    },
    item: function () {
      setTimeout(() => {
        this.zoom = false;
      }, 1500);
      this.zoom = true;
    },
    showType: function () {
      setTimeout(() => {
        this.zoom = false;
      }, 1500);
      this.zoom = true;
    },
  },
  computed: {
    category() {
      return this.$store.getters.getCategory;
    },
  },
  methods: {
    showDetail(item) {
      this.categorySlug = this.category.content.filter(
        (x) => x.id == item.categoryId
      );
      this.$router.push({
        name: "news-detail",
        params: {
          slugPageBefore: this.categorySlug[0].slug,
          slugPageAfter: item.slug + "-" + item.id,
        },
      });
    },
  },
  data() {
    return {
      triggerFades: true,
      zoom: true,
      slugBefore: null,
      categorySlug: null,
    };
  },
};
</script>

<style scoped>
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
.grid .img-box {
  position: relative;
  height: 250px;
}
.list .img-box {
  width: 40%;
  position: relative;
}
.icon-heart {
  position: absolute;
  bottom: 4%;
  right: 1%;
}

img {
  max-width: 100%;
  display: inline-block;
  object-fit: cover;
}
.service-section .service-content {
  position: relative;
  padding: 12px 12px 12px 12px;
  z-index: 1;
}
.service-section .single-item .rating:before {
  position: absolute;
  content: "";
  background: #d9d9d9;
  height: 20px;
  width: 1px;
  left: 108% !important;
  top: 5px;
}
.title-post .rating {
  margin-right: 10px;
}
.title-post p {
  width: 80%;
  margin-bottom: 0px;
}
.title-post p a {
  white-space: nowrap;
  width: 100%;
  text-overflow: ellipsis;
  overflow: hidden;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.5s ease;
}
.title-post p a:hover {
  text-decoration: underline !important;
}
.address {
  white-space: nowrap;
  width: 100%;
  text-overflow: ellipsis;
  overflow: hidden;
  cursor: pointer;
}
.price_room {
  color: #77ba00;
  font-weight: bold;
}
.single-item:hover .price_room,
.single-item:hover .name-person,
.single-item:hover .description p {
  color: #fff;
}
.name-person {
  color: #918d91;
}
.icon-heart :hover {
  cursor: pointer;
}
.list {
  display: flex;
  height: 180px;
}
/* .list .img-box {
  width: 100%;
} */
.list .service-content {
  width: 70%;
}
.service-section .single-item img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.description p {
  margin-bottom: 8px;
  color: #8a8d91;
  font-size: 14px;
}
.description-room {
  display: -webkit-box !important;
  -webkit-line-clamp: 2 !important;
  -webkit-box-orient: vertical !important;
  overflow: hidden !important;
  overflow-wrap: break-word;
}
</style>
