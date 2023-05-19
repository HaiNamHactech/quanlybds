<template>
  <div class="single-item list single-item-extra d-flex mb-3" v-if="item">
    <div class="img-box" style="width: 30%">
      <img @click="showDetail(item)" :src="item.avatar" alt="awesome-image" />
    </div>
    <div class="service-content pt-0 pb-0 ps-2 pe-2" style="width: 70%">
      <div class="title-post d-flex">
        <p
          @click="showDetail(item)"
          :style="{ color: item.colorTilteService }"
          class="mb-1"
          data-toggle="tooltip"
          data-placement="top"
          :title="item.title"
        >
          {{ item.title }}
        </p>
      </div>
      <div class="text" style="width: 100%">
        <div class="row">
          <div class="col-6 price_room">
            {{
              new Intl.NumberFormat("vi-VN", { currency: "VND" }).format(
                item.price
              )
            }}/tháng
          </div>
          <div class="col-6 time_submit_room" style="text-align: right">
            {{ item.dateSubmited }}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    item: {},
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
};
</script>

<style scoped>
.img-box {
  width: 26% !important;
  height: 70px !important;
}
.single-item-extra .img-box img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 8px;
  cursor: pointer;
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
  font-size: 15px;
  cursor: pointer;
  font-weight: 600;
  color: #ff6600;
}
.title-post p:hover {
  text-decoration: underline;
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
</style>
