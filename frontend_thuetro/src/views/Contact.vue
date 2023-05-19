<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div>
    <div class="inner-banner has-base-color-overlay text-center mt-3">
      <div class="container">
        <div class="box">
          <h3 :class="this.zoom ? 'zoom-in-zoom-out' : ''">
            <template v-if="this.menu"> {{ this.menu.name }}</template>
          </h3>
        </div>
      </div>
    </div>
    <Breadcrumb :titlePage="'Liên hệ'"></Breadcrumb>

    <section class="contact_us mt-5">
      <div class="container">
        <div class="row">
          <div class="col-md-8 col-sm-12 col-xs-12">
            <div class="sec-title">
              <h2>Liên hệ <span> trực tuyến</span></h2>
            </div>
            <div class="default-form-area">
              <form
                id="contact-form"
                name="contact_form"
                class="default-form"
                action="http://wp.hostlin.com/nilachol/inc/sendmail.php"
                method="post"
              >
                <div class="row clearfix">
                  <div class="col-md-6 col-sm-6 col-xs-12">
                    <div class="form-group">
                      <input
                        type="text"
                        name="form_name"
                        class="form-control"
                        value=""
                        placeholder="Your Name *"
                        required=""
                      />
                    </div>
                  </div>
                  <div class="col-md-6 col-sm-6 col-xs-12">
                    <div class="form-group">
                      <input
                        type="email"
                        name="form_email"
                        class="form-control required email"
                        value=""
                        placeholder="Your Mail *"
                        required=""
                      />
                    </div>
                  </div>
                  <div class="col-md-6 col-sm-6 col-xs-12">
                    <div class="form-group">
                      <input
                        type="text"
                        name="form_phone"
                        class="form-control"
                        value=""
                        placeholder="Phone"
                      />
                    </div>
                  </div>
                  <div class="col-md-6 col-sm-6 col-xs-12">
                    <div class="form-group">
                      <input
                        type="text"
                        name="form_subject"
                        class="form-control"
                        value=""
                        placeholder="Subject"
                      />
                    </div>
                  </div>
                  <div class="col-md-12 col-sm-12 col-xs-12">
                    <div class="form-group">
                      <textarea
                        name="form_message"
                        class="form-control textarea required"
                        placeholder="Your Message...."
                      ></textarea>
                    </div>
                  </div>
                  <div class="col-md-12 col-sm-12 col-xs-12">
                    <div class="form-group">
                      <input
                        id="form_botcheck"
                        name="form_botcheck"
                        class="form-control"
                        type="hidden"
                        value=""
                      />
                      <button
                        class="btn-style-one"
                        type="submit"
                        data-loading-text="Please wait..."
                      >
                        send message
                      </button>
                    </div>
                  </div>
                </div>
              </form>
            </div>
          </div>

          <div class="col-md-4 col-sm-8 col-xs-12">
            <div class="sec-title">
              <h2>Liên hệ <span> chúng tôi</span></h2>
            </div>
            <div class="text">
              <p>
                Lorem ipsum dolor sit amet, vix an natum labitur eleifend, mel
                amet a laorit menandri.
              </p>
            </div>
            <br />
            <div class="default-cinfo">
              <div class="acc-content collapsed">
                <ul class="contact-infos">
                  <li>
                    <div class="icon_box">
                      <i class="fa fa-map-marker"></i>
                    </div>
                    <!-- /.icon-box -->
                    <div class="text-box">
                      <p>
                        <b>Address:</b> <br />
                        121 Rain St, Melbourne 3000, Australia.
                      </p>
                    </div>
                    <!-- /.text-box -->
                  </li>
                  <li>
                    <div class="icon_box">
                      <i class="fa fa-phone"></i>
                    </div>
                    <!-- /.icon-box -->
                    <div class="text-box">
                      <p>
                        <b>Call Us:</b> <br />
                        +222 4444 88 999
                      </p>
                    </div>
                    <!-- /.text-box -->
                  </li>
                  <li>
                    <div class="icon_box">
                      <i class="fa fa-envelope"></i>
                    </div>
                    <!-- /.icon-box -->
                    <div class="text-box">
                      <p>
                        <b>Mail Us:</b> <br />
                        Yourmail@info.com
                      </p>
                    </div>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <div class="sec-title center mt-5">
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
import { Waypoint } from "vue-waypoint";
import Breadcrumb from "@/views/components/breadcrumb.vue";

export default {
  components: {
    Waypoint,
    Breadcrumb
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

  mounted() {},
  computed: {
    category() {
      return this.$store.getters.getCategory;
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

    animationZoom() {
      setTimeout(() => {
        this.zoom = false;
      }, 1800);
      this.zoom = true;
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
.inner-banner {
  background: url(../../src/assets/images/background/1.jpg);
}
.fact-counter {
  background-image: url("../../src/assets/images/background/2.jpg");
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
</style>
