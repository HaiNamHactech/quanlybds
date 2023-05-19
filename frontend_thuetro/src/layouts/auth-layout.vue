<template>
  <div class="boxed_wrapper">
    <Header></Header>
    <router-view></router-view>
    <Footer></Footer>
  </div>
</template>
<script setup>
import Header from "@/components/layout/header.vue";
import Footer from "@/components/layout/footer.vue";
</script>
<script>
export default {};
</script>
