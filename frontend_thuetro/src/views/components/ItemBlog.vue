<template>
  <div
    class="default-blog-news"
    style="visibility: visible"
    :class="this.zoom ? 'zoom-in-zoom-out' : ''"
  >
    <div class="img-box">
      <img :src="item.image" alt="News" />
    </div>
    <div class="lower-content">
      <div class="post-meta">{{ item.createBy }} | {{ item.createAt }}</div>
      <h4>
        <a @click="showDetail(item)">{{ item.title }}</a>
      </h4>
      <div class="text-description" v-html="item.shortDescription">
      </div>
      <div class="link-btn5">
        <a
          @click="showDetail(item)"
          class="btn-style-eight hvr-icon-wobble-horizontal"
          >Read More</a
        >
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    showType: { default: true },
    item: {},
  },
  watch: {
    item: function () {
      setTimeout(() => {
        this.zoom = false;
      }, 1500);
      this.zoom = true;
    },
  },
  methods: {
    showDetail(item) {
      this.$router.push({
        name: "blog-detail",
        params: {
          blogDetail: item.slug + '-' + item.id,
        },
      });
    },
  },
  data() {
    return {
      zoom: true,
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
img {
  max-width: 100% !important;
  height: 100%;
}
.img-box {
  height: 240px!important;
  object-fit: cover;
}
.text-description {
  display: -webkit-box !important;
  -webkit-line-clamp: 3 !important;
  -webkit-box-orient: vertical !important;
  overflow: hidden !important;
  overflow-wrap: break-word;

}
.default-blog-style-two .overlay .dtc .date-box span,
.default-blog-list .img-box .overlay .dtc .date-box span {
  display: block;
  font-size: 23px;
  font-weight: 500;
  font-style: italic;
}
.lower-content {
  height: 230px !important;
}
.default-blog-news .lower-content h4 {
  cursor: pointer;
}
.hvr-icon-wobble-horizontal {
  cursor: pointer;
}
</style>
