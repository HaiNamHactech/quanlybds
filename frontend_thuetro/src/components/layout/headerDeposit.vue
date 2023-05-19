<template>
  <div>
    <nav class="navbar p-0 fixed-top d-flex flex-row">
      <div
        class="navbar-menu-wrapper flex-grow d-flex align-items-stretch"
        v-if="menus"
      >
        <button
          class="navbar-toggler navbar-toggler align-self-center"
          type="button"
          data-toggle="minimize"
        >
          <span class="mdi mdi-menu"></span>
        </button>
        <ul class="navbar-nav navbar-nav-left" v-for="m in menus" :key="m.ID">
          <li class="nav-item ms-2">
            <router-link
              :to="{
                name: m.slug,
              }"
            >
              <a class="nav-link">{{ m.name }}</a>
            </router-link>
          </li>
        </ul>
      </div>
    </nav>
  </div>
</template>

<script>

export default {
  mounted() {
    this.$store.dispatch("fetchCategory",{ vm: this, param : this.serverParams});
  },
  computed: {
    menus() {
      var x = this.$store.getters.getCategory;
      var y = [];
      if (x && x.content) {
        y = x.content.filter((m) => m.showInAdmin === 1);
      }
      return y;
    },
  },

  data() {
    return {
      serverParams: {
        filter: { page: 0, pageSize: 0, columnFilters: {}, sort: [] },
      },
    };
  },
};
</script>

<style src="@/assets/admin/css/style.css" scoped></style>
