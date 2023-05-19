<template>
  <div>
    <div class="container-scroller">
      <SideBar :reloadDataUser=" this.CheckDataUser" ></SideBar>
      <div class="container-fluid page-body-wrapper">
        <Header></Header>
        <div class="main-panel">
          <div class="content-wrapper">        
            <router-view @handleEmitDataUser="reloadDataUser" />
          </div>
          <Footer></Footer>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import SideBar from "@/components/layout/sidebar.vue";
import Footer from "@/components/layout/footerDeposit.vue";
import Header from "@/components/layout/headerDeposit.vue";
export default {
  components: {
    SideBar,
    Footer,
    Header,
  },
  methods:{
    reloadDataUser(param) {
    this.CheckDataUser = param;
    },
  },
  data() {
    return {
      CheckDataUser : null,
      title:null
    };
  },

  
};
</script>

<style src="../assets/admin/css/style.css" scoped>
</style>
