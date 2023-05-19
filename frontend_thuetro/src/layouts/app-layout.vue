<template>
  <div class="boxed_wrapper">
    <Header></Header>
    <router-view />
    <Footer></Footer>
  </div>
</template>

<script setup>
import Footer from "@/components/layout/footer.vue";
import Header from "@/components/layout/header.vue";
import "../assets/css/style.css";
</script>
<script>
export default {
  mounted() {
    this.$store.dispatch("fetchCategory", {
      vm: this,
      param: this.serverParams,
    });
  },
  // watch: {
  //     "$store.getters.getState": function(o,v){
  //         this.$loading(this.$store.getters.getState);
  //     }
  // },
  computed: {
    layout() {
      return this.$store.getters.layout;
    },
  },
  methods: {},
  data() {
    return {
      serverParams: {
        filter: { page: 0, pageSize: 20, columnFilters: {}, sort: [] },
      },
    };
  },
};
</script>
