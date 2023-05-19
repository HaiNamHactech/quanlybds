<template>
  <div>
    <component v-bind:is="layout">{{layout}}</component>
  </div>
</template>
<script setup>
import { computed } from "vue";
import { useStore } from "vuex";
const store = useStore();

const layout = computed(() => {
  return store.getters.layout;
});
</script>
<script>
import axios from "axios";
// layouts
import appLayout from "./layouts/app-layout.vue";
import authLayout from "./layouts/auth-layout.vue";
import depositLayout from "./layouts/deposit-layout.vue";

export default {
  components: {
    app: appLayout,
    auth: authLayout,
    deposit: depositLayout

  },
  data() {
    return {
      refCount: 0,
      isLoading: false
    }
  },
  methods: {
    setLoading(isLoading) {
      if (isLoading) {
        this.refCount++;
        this.isLoading = true;
        this.$loading(this.isLoading);
      } else if (this.refCount > 0) {
        this.refCount--;
        this.isLoading = (this.refCount > 0);
        this.$loading(this.isLoading);
      }
    }
  },
  created() {
    axios.interceptors.request.use((config) => {
      this.setLoading(true);
      return config;
    }, (error) => {
      this.setLoading(false);
      return Promise.reject(error);
    });

    axios.interceptors.response.use((response) => {
      this.setLoading(false);
      return response;
    }, (error) => {
      this.setLoading(false);
      return Promise.reject(error);
    });
  }

};
</script>
