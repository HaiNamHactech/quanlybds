<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div class="breadcumb-wrapper" style="border: none">
    <div class="container">
      <div class="pull-left">
        <ul class="list-inline link-list d-flex">
          <li>
            <router-link
              :to="{
                name: 'trang-chu',
              }"
            >
              Trang chủ
            </router-link>
          </li>
          <li>
            <a>{{ titlePage }}</a>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    titlePage: null,
  },
};
</script>

<style scoped>
.list-inline.link-list li a {
  cursor: pointer;
  font-size: 15px;
  font-weight: 600;
}
.list-inline.link-list li:last-child a {
  font-weight: 600;
  color: #77ba00 !important;
  text-transform: none !important;
  font-size: 18px;
  transition: 0.3s ease;
  cursor: default !important;
  font-size: 15px;
}
.breadcumb-wrapper ul.link-list li:last-child a:after {
  content: "" !important;
}
</style>
