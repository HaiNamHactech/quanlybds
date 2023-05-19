<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <!--Main Footer-->
  <footer class="main-footer">
    <!--Widgets Section-->
    <div class="widgets-section">
      <div class="container">
        <div class="row">
          <!--Big Column-->
          <div class="big-column col-md-6 col-sm-12 col-xs-12">
            <div class="row clearfix">
              <!--Footer Column-->
              <div class="footer-column col-md-6 col-sm-6 col-xs-12">
                <div class="footer-widget about-widget">
                  <figure class="footer-logo">
                    <a href="index-2.html"
                      ><img
                        src="../../../src/assets/images/logo/logo2.png"
                        alt=""
                    /></a>
                  </figure>
                  <div class="widget-content">
                    <ul class="contact-info">
                      <li>134-Phúc Tân - Hoàn Kiếm - Hà Nội</li>
                      <li>Phone: +(84) 0372 140 949</li>
                      <li>nguyennamhai2k2pc@gmail.com</li>
                    </ul>
                    <ul class="social">
                      <li>
                        <a href="#"><i class="fa fa-facebook"></i></a>
                      </li>
                      <li>
                        <a href="#"><i class="fa fa-twitter"></i></a>
                      </li>
                      <li>
                        <a href="#"><i class="fa fa-linkedin"></i></a>
                      </li>
                      <li>
                        <a href="#"><i class="fa fa-skype"></i></a>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>

              <!--Footer Column-->
              <div class="footer-column col-md-6 col-sm-6 col-xs-12">
                <div class="footer-widget service-widget">
                  <div class="section-title">
                    <h3>Dịch Vụ Của Chúng Tôi</h3>
                  </div>
                  <div class="widget-content" v-if="menus">
                    <ul class="list" v-for="m in menus.content" :key="m.ID">
                      <li v-if="m.type == 'dich-vu'">
                        <router-link :to="{ name: m.slug }">
                          <a>{{ m.name }}</a>
                        </router-link>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <!--Big Column-->
          <div class="big-column col-md-6 col-sm-12 col-xs-12">
            <div class="row clearfix">
              <!--Footer Column-->
              <div class="footer-column col-md-6 col-sm-6 col-xs-12">
                <div class="footer-widget links-widget">
                  <div class="section-title">
                    <h3>Đường dẫn nhanh</h3>
                  </div>
                  <div class="widget-content" v-if="menus">
                    <ul class="list" v-for="m in menus.content" :key="m.ID">
                      <li v-if="m.type == 'duong-dan-nhanh'">
                        <router-link :to="{ name: m.slug }">
                          <a>{{ m.name }}</a>
                        </router-link>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>

              <!--Footer Column-->
              <div class="footer-column col-md-6 col-sm-6 col-xs-12">
                <div class="footer-widget contact-widget">
                  <div class="section-title">
                    <h3>Instagram</h3>
                  </div>
                  <div class="footer-widget gallery-widget">
                    <figure class="image">
                      <img
                        src="../../../src/assets/images/resource/i1.jpg"
                        alt=""
                      />
                      <div class="link">
                        <a href="#"
                          ><i class="fa fa-link" aria-hidden="true"></i
                        ></a>
                      </div>
                    </figure>

                    <figure class="image">
                      <img
                        src="../../../src/assets/images/resource/i2.jpg"
                        alt=""
                      />
                      <div class="link">
                        <a href="#"
                          ><i class="fa fa-link" aria-hidden="true"></i
                        ></a>
                      </div>
                    </figure>

                    <figure class="image">
                      <img
                        src="../../../src/assets/images/resource/i3.jpg"
                        alt=""
                      />
                      <div class="link">
                        <a href="#"
                          ><i class="fa fa-link" aria-hidden="true"></i
                        ></a>
                      </div>
                    </figure>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!--Footer Bottom-->
    <div class="footer-bottom">
      <div class="container">
        <div class="copy-text center">
          <p>
            Copyrights © 2023 All Rights Reserved by
            <a href="index-2.html"> Nam Hải.</a>
          </p>
        </div>
      </div>
    </div>
  </footer>
</template>

<script>
export default {
  components: {},
  mounted() {},
  unmounted() {},
  watch: {},
  computed: {
    menus() {
      return this.$store.getters.getCategory;
    },
  },
  methods: {},
  data() {
    return {
      serverParams: {
        filter: { page: 0, pageSize: 0, columnFilters: {}, sort: [] },
      },
    };
  },
};
</script>

<style scoped>
.router-link-active a {
  color: #77ba00 !important;
}
</style>
