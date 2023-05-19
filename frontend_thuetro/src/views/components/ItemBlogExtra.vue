<template>
  <div class="single-item list single-item-extra d-flex mb-3" v-if="item">
    <div class="img-box" style="width: 30%">
      <img @click="showDetail(item)" :src="item.image" alt="awesome-image" />
    </div>
    <div class="service-content pt-0 pb-0 ps-2 pe-2" style="width: 70%">
      <div class="title-post d-flex">
        <p
          @click="showDetail(item)"
          data-toggle="tooltip"
          data-placement="top"
          :title="item.title"
        >
          {{ item.title }}
        </p>
      </div>
      <div class="text" style="width: 100%">
        <div class="row">
          <div class="col-7 price_room">
            <div class="link-btn5 mt-0">
              <a
                @click="showDetail(item)"
                class="btn-style-eight hvr-icon-wobble-horizontal"
                >Read More</a
              >
            </div>
          </div>
          <div class="col-5 time_submit_room" style="text-align: right">
            {{ item.updateAt }}
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
  methods: {
    showDetail(item) {
      this.$router.push({
        name: "blog-detail",
        params: {
          blogDetail: item.slug + "-" + item.id,
        },
      });
    },
  },
};
</script>

<style scoped>
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
  color: #000;
}
.title-post p:hover {
  color: #000;
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
.img-box {
  width: 26% !important;
  height: 70px !important;
}
</style>
