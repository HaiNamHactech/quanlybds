<template>
  <div class="nav-sort mb-3">
    <!-- <span class="mb-0">Sắp Xếp : </span> -->
    <section
      class="gallery-section fullwidth-gallery d-flex justify-content-between"
    >
      <div class="auto-container d-flex">
        <p class="title-sort mb-0 me-2">Sắp xếp :</p>
        <!--Filter-->
        <div class="filters text-center">
          <ul class="filter-tabs filter-btns clearfix d-flex ps-0">
            <li
              :class="this.checkSortType == 'default' ? 'active' : ''"
              class="filter"
              data-role="button"
              data-filter="all"
              @click="sortType('default')"
            >
              <span class="txt">Mặc định</span>
            </li>
            <li
              :class="this.checkSortType == 'latest' ? 'active' : ''"
              class="filter"
              data-role="button"
              data-filter=".bedroom"
              @click="sortType('latest')"
            >
              <span class="txt">Mới nhất</span>
            </li>
            <li
              :class="this.checkSortType == 'video' ? 'active' : ''"
              class="filter"
              data-role="button"
              data-filter=".delux"
              @click="sortType('video')"
            >
              <span class="txt">Có video </span>
            </li>
          </ul>
        </div>
      </div>
      <div class="d-flex" style="width: 22%">
        <div>
          <div class="input-group">
            <div class="input-group-prepend">
              <label class="input-group-text" for="inputGroupSelect01"
                >Hiển thị lượng tin</label
              >
            </div>
            <select
              ref="perPage"
              class="custom-select"
              id="inputGroupSelect01"
              @change="onPerPageChange()"
            >
              <option value="3">3</option>
              <option selected value="6">6</option>
              <option value="9">9</option>
              <option value="12">12</option>
            </select>
          </div>
        </div>

        <div class="ms-3 d-flex align-items-center">
          <vue-feather
            @click="showType(false)"
            type="list"
            size="18"
            stroke-width="2"
            class="me-2"
            :stroke="this.checkShowType ? 'gray' : '#77ba00'"
            data-toggle="tooltip"
            data-placement="top"
            title="Danh sách"
          >
          </vue-feather>
          <vue-feather
            @click="showType(true)"
            type="grid"
            size="18"
            stroke-width="2"
            class="me-2"
            :stroke="this.checkShowType ? '#77ba00' : 'gray'"
            data-toggle="tooltip"
            data-placement="top"
            title="Lưới"
          >
          </vue-feather>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  props: {
    page: null,
    type:null,
  },
  watch: {
    page: function (val) {
      this.pages = val;
    },
  },
  methods: {
    showType(data) {
      this.checkShowType = data;
      this.$emit("showType", data);
    },
    sortType(data) {
      this.checkSortType = data;
      this.$emit("sortType", data);
    },
    updateParams(newProps) {
      this.serverParams.filter = Object.assign(
        {},
        this.serverParams.filter,
        newProps
      );
    },
    onPerPageChange() {
      this.$emit("onPerPageChange", this.$refs.perPage.value);
    },
  },
  data() {
    return {
      checkShowType: true,
      checkSortType: "default",
      pages: null,
      serverParams: {
        filter: { page: 0, pageSize: 3, columnFilters: {}, sort: [] },
      },
    };
  },
};
</script>

<style scoped>
.nav-sort {
  width: 100%;
  background: #f5f5f5;
  padding: 10px 10px 0px 10px;
  border-radius: 10px;
}
.gallery-section .filters {
  margin-bottom: 0px;
}
.gallery-section .filters li {
  padding: 4px 10px;
  line-height: 15px;
  font-size: 14px;
  margin: 1px 2px 8px;
}
.title-sort {
  font-weight: 500;
}
.vue-feather {
  cursor: pointer;
}
.input-group-text {
  padding: 0.175rem 0.75rem;
  font-size: 14px;
  background: #77ba00;
  border-radius: 0% !important;
  color: #fff;
}
.input-group {
  font-size: 14px;
}
.custom-select {
  border-color: #77ba00;
  color: #181818;
  border-radius: 0px 6px 6px 0px;
}
.custom-select:focus {
  outline: none !important;
}
.input-group-prepend {
  border: 1px solid #77ba00;
}
</style>
