<template>
  <div class="col-10 navbar-filter">
    <div class="row">
      <div class="col-md-2 pe-0" v-if="categorys">
        <Multiselect
          :searchable="true"
          valueProp="id"
          label="name"
          placeholder="Chọn danh mục"
          class="form-control form-control-sm"
          :options="categorys"
          :canClear="true"
          @change="onColumnRouter()"
          v-model="slugCategory"
        ></Multiselect>
      </div>
      <div class="col-md-2 pe-0" v-if="conscious">
        <Multiselect
          :searchable="true"
          valueProp="id"
          label="name"
          placeholder="Chọn tỉnh thành"
          class="form-control form-control-sm"
          :options="conscious"
          @change="onColumnFilter()"
          v-model="serverParams.filter.columnFilters.consciousId"
        ></Multiselect>
      </div>
      <div class="col-md-2 pe-0">
        <Multiselect
          ref="districtId"
          :searchable="true"
          valueProp="id"
          label="name"
          placeholder="Chọn quận/huyện"
          class="form-control form-control-sm"
          :options="districts"
          @change="onColumnFilter()"
          v-model="serverParams.filter.columnFilters.districtId"
        ></Multiselect>
      </div>
      <div class="col-md-2 pe-0">
        <Multiselect
          ref="wardId"
          :searchable="true"
          valueProp="id"
          label="name"
          placeholder="Chọn phường/xã"
          class="form-control form-control-sm"
          :options="wards"
          @change="onColumnFilter()"
          v-model="serverParams.filter.columnFilters.wardId"
        ></Multiselect>
      </div>
      <div class="col-md-2 pe-0" v-if="prices">
        <Multiselect
          :searchable="true"
          valueProp="id"
          label="name"
          placeholder="Chọn giá"
          class="form-control form-control-sm"
          :options="prices"
          @change="onColumnFilter()"
          v-model="this.priceId"
        ></Multiselect>
      </div>
      <div class="col-md-2" v-if="acreages">
        <Multiselect
          :searchable="true"
          valueProp="id"
          label="name"
          placeholder="Chọn diện tích"
          class="form-control form-control-sm"
          :options="acreages"
          @change="onColumnFilter()"
          v-model="this.areaId"
        ></Multiselect>
      </div>
    </div>
  </div>
</template>
<script>
import "vue-next-select/dist/index.min.css";
import Multiselect from "@vueform/multiselect";
import "@vueform/multiselect/themes/default.css";
import { nextTick } from "vue";
export default {
  name: "NavbarFilter",
  components: {
    Multiselect,
  },
  props: {
    priceFilter: null,
    areaFilter: null,
  },
  mounted() {
    this.slugCategory = this.$route.name;
    this.$store.dispatch("fetchAddress", this.serverParams);
    this.$store.dispatch("fetchPriceOrArea", {
      filter: {
        page: 0,
        pageSize: 0,
        columnFilters: {},
        sort: [
          { field: "priceForm", type: "asc" },
          { field: "types", type: "asc" },
        ],
      },
    });
  },
  watch: {
    priceFilter: function (val) {
      this.priceId = val;
    },
    areaFilter: function (val) {
      this.areaId = val;
    },
    $route: function () {
      this.slugCategory = this.$route.name;
    },
    "serverParams.filter.columnFilters.consciousId": function (val) {
      this.$refs.districtId.clear();
      this.districts = this.render_option(val, this.conscious, "districts");
    },

    "serverParams.filter.columnFilters.districtId": function (val) {
      this.wards = this.render_option(val, this.districts, "wards");
      this.$refs.wardId.clear();
    },
  },
  computed: {
    prices() {
      var prices = this.$store.getters.getPriceOrArea;
      var prices_render = null;
      if (prices && prices.content) {
        prices_render = prices.content.filter((m) => m.types == "PRICE");
        prices_render = prices_render.map((item) => ({
          ...item,
          id: item.id + "-" + item.priceForm + "-" + item.priceTo,
          name: item.title,
        }));
        prices_render.unshift({
          id: "",
          name: "Tất Cả Các Giá",
        });
      }
      return prices_render;
    },
    acreages() {
      var acreages = this.$store.getters.getPriceOrArea;
      var acreages_render = null;
      if (acreages && acreages.content) {
        acreages_render = acreages.content.filter((m) => m.types == "AREA");
        acreages_render = acreages_render.map((item) => ({
          ...item,
          id: item.id + "-" + item.areaFrom + "-" + item.areaTo,
          name: item.title,
        }));
        acreages_render.unshift({
          id: "",
          name: "Tất Cả loại m2",
        });
      }
      return acreages_render;
    },
    categorys() {
      var categorys = this.$store.getters.getCategory;
      var category_render = null;
      if (categorys && categorys.content) {
        category_render = categorys.content.filter(
          (m) => m.type === "duong-dan-nhanh"
        );
        category_render = category_render.map((item) => ({
          id: item.slug,
          name: item.name,
        }));
        category_render.unshift({
          id: "trang-chu",
          name: "Tất Cả Danh Mục",
        });
      }
      return category_render;
    },
    conscious() {
      var address = this.$store.getters.getAddress;
      var conscious_render = null;
      if (address) {
        conscious_render = address.map((item) => ({
          ...item,
          id: item.code + "-" + item.codename,
          name: item.name,
        }));
        conscious_render.unshift({
          id: "",
          name: "Tất Cả Tỉnh Thành",
        });
      }
      return conscious_render;
    },
    rooms() {
      return this.$store.getters.getRooms;
    },
  },
  methods: {
    render_option(val, parrent_data, type) {
      var result = [];
      if (val != null) {
        var parrent_data_filter = [];
        if (val != "") {
          var id = val.split("-")[0];
          parrent_data_filter = parrent_data.filter((c) => c.code == id)[0][
            type
          ];
        } else {
          const getMembers = (members) => {
            let children = [];
            const flattenMembers = members.map((m) => {
              if (m[type] && m[type].length) {
                children = [...children, ...m[type]];
              }
              return m;
            });
            return flattenMembers.concat(
              children.length ? getMembers(children) : children
            );
          };
          parrent_data_filter = getMembers(parrent_data).filter(
            (e) => !Object.keys(e).includes(type)
          );
          parrent_data_filter.shift();
        }
        result = parrent_data_filter.map((item) => ({
          ...item,
          id: item.code + "-" + item.codename,
          name: item.name,
        }));
        var string_type = type == "districts" ? "Quận/huyện" : "Phường/xã";
        result.unshift({
          id: "",
          name: "Tất Cả " + string_type,
        });
      }
      return result;
    },
    updateParams(newProps) {
      this.serverParams.filter = Object.assign(
        {},
        this.serverParams.filter,
        newProps
      );
    },
    onColumnFilter() {
      nextTick(() => {
        this.serverParams.filter.columnFilters.price = this.priceId;
        this.serverParams.filter.columnFilters.acreage = this.areaId;
      });
      nextTick(() => {
        this.$emit("columnFilter", this.serverParams.filter.columnFilters);
      });
    },
    onColumnRouter() {
      nextTick(() => {
        this.$router.push({ name: this.slugCategory });
      });
    },
  },
  data() {
    return {
      validation_now: false,
      districts: null,
      addresss: null,
      wards: null,
      priceId: null,
      areaId: null,
      slugCategory: null,
      serverParams: {
        filter: { page: 0, pageSize: 0, columnFilters: {}, sort: [] },
      },
    };
  },
};
</script>
<style lang="css" scoped>
.multiselect.form-control.form-control-sm {
  font-size: 13px !important;
}
.multiselect {
  min-height: auto !important;
}
.col-md-3 {
  width: 20% !important;
}
.multiselect-search {
  cursor: pointer !important;
  background: #f5f5f5 !important;
}
</style>
