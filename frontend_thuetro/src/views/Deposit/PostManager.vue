<!-- eslint-disable no-undef -->
<template>
  <div>
    <div class="col-lg-12 grid-margin stretch-card">
      <div class="card" style="border: none; color: #fff">
        <div
          class="card-body ps-2 pe-2 pt-2 pb-2"
          style="background-color: #191c24; border-radius: 5px"
        >
          <div
            class="card-title d-flex justify-content-between align-items-center"
          >
            <span class="title-table" v-if="this.getTitle()">{{
              "Danh Sách" + this.getTitle()
            }}</span>
            <!-- <button
              type="button"
              style="color: #fff"
              class="btn btn-danger btn-fw"
            >
              Đăng tin
            </button> -->
            <button
              style="color: #fff"
              type="button"
              class="btn btn-add btn-fw"
              v-if="options.find((i) => i.value == selected).NewEnabled"
              @click="New()"
            >
              <vue-feather type="plus" size="16" class="align-text-top" />
              {{ "Thêm mới " + this.getTitle() }}
            </button>
          </div>

          <vue-good-table
            v-if="items && items.content"
            :columns="this.fields"
            :rows="items.content"
            class="elevation-0 table"
            :line-numbers="true"
            :pagination-options="{
              perPage: serverParams.filter.pageSize,
              enabled: true,
              rowsPerPageLabel: 'Rows per page',
              nextLabel: 'Next',
              prevLabel: 'Previous',
              ofLabel: 'of',
            }"
            mode="remote"
            @page-change="onPageChange"
            @sort-change="onSortChange"
            @column-filter="onColumnFilter"
            @per-page-change="onPerPageChange"
            :totalRows="items.totalElements"
          >
            <template #table-row="props">
              <!-- Column: Action -->
              <span
                class="d-flex justify-content-center align-items-center"
                v-if="props.column.field === 'action'"
              >
                <span
                  class="me-2 btn-action"
                  role="button"
                  @click="Edit(props.row, props.row.id)"
                  v-if="options.find((i) => i.value == selected).EditEnabled"
                >
                  <vue-feather size="18px" type="edit" />
                </span>
                <span
                  role="button"
                  class="btn-action"
                  @click="Delete(props.row, props.row.id)"
                  v-if="
                    options.find((i) => i.value == selected).DeleleEnabled &&
                    props.row.status == 2 &&
                    selected == 'ghost/room'
                  "
                >
                  <vue-feather size="18px" type="eye-off" />
                </span>
                <span
                  role="button"
                  class="btn-action"
                  @click="Restore(props.row, props.row.id)"
                  v-if="
                    options.find((i) => i.value == selected).RestoreEnabled &&
                    props.row.status == 4 &&
                    selected == 'ghost/room'
                  "
                >
                  <vue-feather size="18px" type="eye" />
                </span>
                <span
                  role="button"
                  class="btn-action"
                  @click="Extend(props.row, props.row.id)"
                  v-if="
                    options.find((i) => i.value == selected).ExtendEnabled &&
                    props.row.status == 3 &&
                    selected == 'ghost/room'
                  "
                >
                  <vue-feather size="18px" type="refresh-cw" />
                </span>
                <span
                  role="button"
                  class="btn-action"
                  @click="Pay(props.row, props.row.id)"
                  v-if="
                    options.find((i) => i.value == selected).PayEnabled &&
                    props.row.status == 0 &&
                    selected == 'ghost/room'
                  "
                >
                  <vue-feather size="18px" type="dollar-sign" />
                </span>
              </span>
              <span v-else-if="props.column.types === 'image'">
                <img :src="props.row[props.column.field]" alt="" />
              </span>
              <span v-else-if="props.column.field === 'status'">
                <template v-if="props.column.types === 'status_room'">
                  <span
                    class="show_status"
                    :style="{
                      background: this.showStatus(props.row[props.column.field])
                        .color,
                    }"
                    >{{
                      this.showStatus(props.row[props.column.field]).mess
                    }}</span
                  >
                </template>
                <template v-else-if="props.column.types === 'status_balances'">
                  <span
                    class="show_status"
                    :style="{
                      background: this.showStatusBalances(
                        props.row[props.column.field]
                      ).color,
                    }"
                    >{{
                      this.showStatusBalances(props.row[props.column.field])
                        .mess
                    }}</span
                  >
                </template>
              </span>
              <span v-else-if="props.column.types === 'price'">
                {{
                  new Intl.NumberFormat("vi-VN", { currency: "VND" }).format(
                    props.row[props.column.field]
                  )
                }}
                đ
              </span>

              <span v-else>
                {{ props.row[props.column.field] }}
              </span>

              <!-- Column: Common -->
              <!-- <span
                v-else-if="
                  allFields.filter((i) => i.field == props.column.field)[0]
                    .select2 &&
                  allFields
                    .filter((i) => i.field == props.column.field)[0]
                    .select2.filter(
                      (i) => i.value == props.row[props.column.field]
                    ).length > 0
                "
              >
                {{
                  allFields
                    .filter((i) => i.field == props.column.field)[0]
                    .select2.filter(
                      (i) => i.value == props.row[props.column.field]
                    )[0].label
                }}
              </span> -->
            </template>
          </vue-good-table>
        </div>
      </div>
    </div>
    <div class="modal fade" ref="editModal" tabindex="-1" role="dialog">
      <div class="modal-dialog modal-xl" role="document">
        <div class="modal-content">
          <FormKit
            v-model="currentItem"
            :actions="false"
            :incomplete-message="false"
            type="form"
            @submit="handleOk"
          >
            <div class="modal-header">
              <h5 class="modal-title">
                {{ getTitleModal() }}
              </h5>
              <button
                type="button"
                data-dismiss="modal"
                data-bs-dismiss="modal"
                aria-label="Close"
              >
                <vue-feather type="x" stroke="#b5beca" size="46"></vue-feather>
              </button>
            </div>
            <div class="modal-body pt-1">
              <div class="row">
                <h4 style="color: #fff" v-if="showDescription">
                  <template v-if="this.checkExtend">Gia hạn tin </template>
                  <template v-else>Thanh toán tin</template>:
                  {{ this.currentItem.title }}
                </h4>
                <div
                  :class="[
                    'col-' + (i.cols != null ? i.cols : 6),
                    i.addHidden ? 'HiddenAdd' : '',
                  ]"
                  v-for="i in allFields.filter(
                    (i) => i.field !== 'action' && !i.expandedField
                  )"
                  :key="i.field"
                >
                  <template v-if="!i.addHidden">
                    <div v-show="!i.checkPayment">
                      <div v-if="i.date_time == true">
                        <FormKit
                          :name="i.field"
                          class="form-control form-control-sm"
                          :label-class="i.required ? 'required' : null"
                          validation="required"
                          :label="i.label"
                          type="date"
                          :disabled="i.disabled"
                          :placeholder="i.label"
                          v-model="currentItem[i.field]"
                        />
                      </div>
                      <div v-else-if="i.select2 != null">
                        {{ i.ref }}
                        <FormKit
                          :name="i.field"
                          class="form-control form-control-sm"
                          :label-class="i.required ? 'required' : null"
                          :validation="i.required ? 'required' : ''"
                          :validation-rules="{ validation }"
                          :validation-messages="{
                            validation: showErrors(i.field),
                            length: i.lenMessage,
                            required: i.label + ' không được bỏ trống',
                          }"
                          :label="i.label"
                          type="select"
                          :disabled="i.disabled"
                          :placeholder="'Chọn ' + i.label"
                          v-model="currentItem[i.field]"
                          :options="i.select2"
                        />
                      </div>
                      <div v-else-if="i.key">
                        <FormKit
                          :name="i.field"
                          class="form-control form-control-sm"
                          :label-class="i.required ? 'required' : null"
                          validation="required"
                          type="text"
                          :label="i.label"
                          :disabled="i.disabled || (i.key && i.editMode)"
                          v-model="currentItem[i.field]"
                          :placeholder="i.label"
                        />
                      </div>
                      <div v-else-if="i.types == 'singleFile'">
                        <label class="form-label mt-2">
                          {{ i.label }}
                        </label>
                        <div class="upload-image">
                          <div
                            class="upload__box d-flex justify-content-between"
                          >
                            <div class="upload__btn-box">
                              <label class="upload__btn">
                                <vue-feather
                                  type="upload"
                                  size="18"
                                ></vue-feather>
                                <input
                                  type="file"
                                  :multiple="i.multiple"
                                  class="upload__inputfile"
                                  @change="changeAvatar"
                                  ref="singleFile"
                                />
                              </label>
                            </div>
                            <div
                              class="upload__img-wrap"
                              v-if="this.currentItem.avatar"
                            >
                              <img :src="this.currentItem.avatar" alt="" />
                              <i
                                @click="removeAvatar()"
                                class="mdi mdi-close-circle-outline"
                              ></i>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div v-else-if="i.types == 'mutipleFile'">
                        <label class="form-label mt-2">
                          {{ i.label }}
                        </label>
                        <div class="upload-image">
                          <div class="upload__box d-flex">
                            <div class="upload__btn-box">
                              <label class="upload__btn">
                                <vue-feather
                                  type="upload"
                                  size="18"
                                ></vue-feather>
                                <input
                                  type="file"
                                  :multiple="i.multiple"
                                  class="upload__inputfile"
                                  @change="changeListImgExtra"
                                  ref="mutipleFile"
                                />
                              </label>
                            </div>
                            <template v-if="this.images">
                              <swiper
                                :slidesPerView="5"
                                :spaceBetween="5"
                                :freeMode="true"
                                class="mySwiper"
                                style="width: 100%"
                              >
                                <swiper-slide
                                  v-for="(image, index) in this.images"
                                  :key="image"
                                >
                                  <div
                                    class="upload__img-wrap upload__img-listimage ms-3"
                                  >
                                    <img :key="image" :src="image" alt="" />
                                    <i
                                      @click="removeImage(index)"
                                      class="mdi mdi-close-circle-outline"
                                    ></i>
                                  </div>
                                </swiper-slide>
                              </swiper>
                            </template>
                          </div>
                        </div>
                      </div>
                      <div v-else :ref="i.ref">
                        <FormKit
                          :name="i.field"
                          class="form-control form-control-sm"
                          :label-class="i.required ? 'required' : null"
                          :validation="getValidation(i)"
                          validation-visibility="dirty"
                          :validation-messages="{
                            validation: showErrors(i),
                            min: showErrors(i),
                            length: showErrors(i),
                            required: i.label + ' không được bỏ trống ',
                          }"
                          :type="i.type ? i.type : 'text'"
                          :label="i.label"
                          :disabled="i.disabled || (i.key && i.editMode)"
                          v-model="currentItem[i.field]"
                          :placeholder="'Nhập ' + i.label"
                          @keyup="i.validUnquie ? checkUnquie(i) : null"
                        />
                      </div>
                    </div>
                  </template>
                </div>
              </div>

              <div
                v-if="
                  this.options.filter((i) => i.value == this.selected)[0]
                    .value == 'ghost/room'
                "
              >
                <div v-if="showDescription">
                  <h6 class="mt-4 mb-4" style="color: #fff">
                    Chọn Phương Thức Thanh Toán :
                  </h6>
                  <div class="form-check">
                    <input
                      class="form-check-input"
                      type="radio"
                      name="exampleRadios"
                      id="exampleRadios1"
                      value="TTTK"
                      @click="handlePay('TTTK')"
                      :checked="this.typePay == 'TTTK'"
                    />
                    <label
                      style="color: #fff"
                      class="form-check-label"
                      for="exampleRadios1"
                      v-if="this.balance >= 0"
                    >
                      Trừ tiền trong tài khoản của bạn tại web (Bạn đang có : TK
                      Chính
                      {{
                        new Intl.NumberFormat("vi-VN", {
                          currency: "VND",
                        }).format(this.balance)
                      }}
                      VNĐ)
                    </label>
                  </div>
                  <div class="form-check">
                    <input
                      class="form-check-input"
                      type="radio"
                      name="exampleRadios"
                      id="exampleRadios2"
                      value="MOMO"
                      @click="handlePay('MOMO')"
                      :checked="this.typePay == 'MOMO'"
                    />
                    <label
                      style="color: #fff"
                      class="form-check-label"
                      for="exampleRadios2"
                    >
                      <span class="me-3">Thanh Toán qua MO MO</span>
                      <img
                        style="width: 30px; height: 30px; border-radius: 8px"
                        src="../../assets/images/momo.png"
                        alt=""
                      />
                    </label>
                  </div>
                  <div class="form-check">
                    <input
                      class="form-check-input"
                      type="radio"
                      name="exampleRadios"
                      id="exampleRadios2"
                      value="VNPAY"
                      @click="handlePay('VNPAY')"
                      :checked="this.typePay == 'VNPAY'"
                    />
                    <label
                      style="color: #fff"
                      class="form-check-label"
                      for="exampleRadios2"
                    >
                      <span class="me-3">Thanh Toán qua VNPAY</span>
                      <img
                        style="width: 30px; height: 30px; border-radius: 8px"
                        src="../../assets/images/vnpay.png"
                      />
                    </label>
                  </div>
                  <div class="form-check">
                    <input
                      class="form-check-input"
                      type="radio"
                      name="exampleRadios"
                      id="exampleRadios2"
                      value="CK"
                      @click="handlePay('CK')"
                      :checked="this.typePay == 'CK'"
                    />
                    <label
                      style="color: #fff"
                      class="form-check-label"
                      for="exampleRadios2"
                    >
                      <span class="me-3"
                        >Chuyển khoản ngân hàng <br />
                        Nội dung chuyển khoản PT THANHTOAN MATIN THUONG 5 NGAY
                        <br />
                        Số tiền chuyển khoản : 100000 đ
                      </span>
                    </label>
                  </div>
                </div>

                <div class="row" v-show="!this.showDescription">
                  <div class="col-6 mt-3">
                    <div class="d-flex align-items-center mb-2">
                      <label class="form-label mb-0 me-3"> Mô tả gắn : </label>
                      <span
                        class="showGuide"
                        @click="this.guide_vi = !this.guide_vi"
                      >
                        Nhập mô tả ngắn
                      </span>
                    </div>
                    <div v-show="this.guide_vi === true" class="w-100">
                      <QuillEditor
                        ref="contentVi"
                        style="height: 250px"
                        toolbar="full"
                        theme="snow"
                        contentType="html"
                      />
                    </div>
                  </div>
                  <div class="col-6 mt-3">
                    <div class="d-flex align-items-center mb-2">
                      <label class="form-label mb-0 me-3">
                        Mô tả chi tiết :
                      </label>
                      <span
                        class="showGuide"
                        @click="this.guide_en = !this.guide_en"
                      >
                        Nhập mô tả chi tiết
                      </span>
                    </div>
                    <div v-show="this.guide_en === true" class="w-100">
                      <QuillEditor
                        ref="contentEn"
                        style="height: 250px"
                        toolbar="full"
                        theme="snow"
                        contentType="html"
                      />
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="modal-footer">
              <p
                v-if="
                  this.selected == 'ghost/room' &&
                  this.showDescription &&
                  !this.checkPay
                "
                class="btn btn-primary btn-prev"
                @click="prev()"
              >
                Prev
              </p>
              <button
                type="button"
                class="btn"
                data-dismiss="modal"
                data-bs-dismiss="modal"
              >
                <i class="flaticon-cancel-12"></i> {{ "Discard" }}
              </button>

              <button class="btn btn-primary" type="submit">
                <template v-if="this.selected == 'ghost/room'">
                  <template v-if="this.showDescription"
                    >Thanh Toán
                    <template
                      v-if="
                        this.currentItem.packageId && this.currentItem.numberDay
                      "
                    >
                      <!-- <template v-if="this.checkMoneyPay"> -->
                      {{
                        new Intl.NumberFormat("vi-VN", {
                          currency: "VND",
                        }).format(
                          this.getPriceInPackageService(
                            this.currentItem.packageId
                          ) * this.currentItem.numberDay
                        ) + " đ"
                      }}
                      <!-- </template> -->
                      <!-- <template v-else>Lưu và Chưa Thanh Toán </template> -->
                    </template>
                  </template>
                  <template v-else>
                    <template v-if="!this.editMode">Tiếp tục</template>
                    <template v-else>Cập nhật</template>
                  </template>
                </template>
                <template v-else> Save </template>
              </button>
            </div>
          </FormKit>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import "vue-good-table-next/dist/vue-good-table-next.css";
import { VueGoodTable } from "vue-good-table-next";
import { Modal } from "bootstrap";
import { QuillEditor } from "@vueup/vue-quill";
import "@vueup/vue-quill/dist/vue-quill.snow.css";
import { $themeConfig } from "../../../theme.config";
import axios from "axios";
import "flatpickr/dist/flatpickr.css";
import "flatpickr/dist/themes/material_blue.css";
import { nextTick } from "vue";

import { Swiper, SwiperSlide } from "swiper/vue";

// Import Swiper styles
import "swiper/css";

import "swiper/css/free-mode";
import "swiper/css/pagination";
import { FreeMode, Pagination } from "swiper";

const confirm = window.Swal.mixin({
  confirmButtonClass: "btn btn-primary me-3",
  cancelButtonClass: "btn btn-dark",
  buttonsStyling: false,
});

export default {
  components: {
    VueGoodTable,
    QuillEditor,
    Swiper,
    SwiperSlide,
    // flatPickr,
  },
  mounted() {
    this.userData = localStorage.userData
      ? JSON.parse(localStorage.userData)
      : null;
    this.loadMasterData();
    if (this.selected == "ghost/room") {
      var curDate = new Date();
      var totalNumberSeconds =
        (Number(curDate.getHours()) * 3600 +
          Number(curDate.getMinutes()) * 60) *
        1000;
      this.reloadRoom(totalNumberSeconds);
    }
    this.$store.dispatch("fetchAddress");
    this.$store.dispatch("fetchPriceList", {
      filter: {
        page: 0,
        pageSize: 15,
        columnFilters: {},
        sort: [],
      },
    });
    this.editModal = new Modal(this.$refs.editModal);
  },
  unmounted() {
    this.editModal.hide();
  },

  watch: {
    "localStorage.userData": function (data) {
      this.userData = data ? JSON.parse(data) : data;
    },
    autoGetRoom: function () {
      var totalNumberSeconds = 24 * 3600 * 1000;
      this.reloadRoom(totalNumberSeconds);
    },
    $route: function (from, to) {
      try {
        if (to.matched.length > 0 && to.matched[0].meta.type) {
          this.loadMasterData();
        }
      } catch (err) {
        console.log(err);
      }
    },

    "currentItem.consciousId": function (val) {
      if (val && val != "") {
        this.currentItem.consciousName = this.conscious.filter(
          (item) => item.codename == val.split("-")[1]
        )[0].name;
      }
      this.currentItem.districtId = "";
      this.districts = this.render_option(val, this.conscious, "districts");
      this.allFields.forEach((x) => {
        if (x.field == "districtId") {
          x.select2 = this.districts;
          x.select2[x.select2.length - 1] = {
            label: "Chọn quận/huyện",
            attrs: { disabled: true },
          };
        }
      });
    },

    "currentItem.districtId": function (val) {
      this.currentItem.districtName = null;
      if (val && val != "") {
        this.currentItem.districtName = this.districts.filter(
          (item) => item.codename == val.split("-")[1]
        )[0].name;
      }
      this.currentItem.wardId = "";
      this.wards = this.render_option(val, this.districts, "wards");
      console.log(this.wards);
      this.allFields.forEach((x) => {
        if (x.field == "wardId") {
          x.select2 = this.wards;
          x.select2[x.select2.length - 1] = {
            label: "Chọn phường/xã",
            attrs: { disabled: true },
          };
        }
      });
    },

    "currentItem.wardId": function (val) {
      console.log(val);
      this.currentItem.wardName = null;
      if (val && val != "") {
        this.currentItem.wardName = this.wards.filter(
          (item) => item.codename == val.split("-")[1]
        )[0].name;
        console.log(this.currentItem.wardName);
      }
    },
    "currentItem.packageDay": function (val) {
      this.currentItem.numberDay = val;
    },
  },
  computed: {
    menus() {
      var x = this.$store.getters.getCategory;
      var y = [];
      if (x && x.content) {
        x.content = x.content.filter((m) => m.type == "duong-dan-nhanh");
        y = x.content.map((item) => ({
          value: item.id,
          label: item.name,
        }));
      }
      return y;
    },
    conscious() {
      var address = this.$store.getters.getAddress;
      var conscious_render = [];
      if (address) {
        conscious_render = address.map((item) => ({
          ...item,
          value: item.code + "-" + item.codename,
          label: item.name,
        }));
      }
      return conscious_render;
    },
    items() {
      return this.$store.getters.getMds;
    },
    packageService() {
      var x = this.$store.getters.getPriceList;
      var y = [];
      if (x && x.content) {
        y = x.content.map((item) => ({
          ...item,
          value: item.id,
          label:
            item.title +
            " (" +
            new Intl.NumberFormat("vi-VN", { currency: "VND" }).format(
              item.dayPrice
            ) +
            "đ/ ngày)",
        }));
      }
      return y;
    },
    balance() {
      return this.$store.getters.getBalance;
    },
  },
  methods: {
    handlePay(methodPay) {
      this.typePay = methodPay;
    },
    getPriceInPackageService(id) {
      return this.packageService.filter((x) => x.id == id)[0].dayPrice;
    },
    addDays(date, days) {
      var result = new Date(date);
      result.setDate(result.getDate() + days);
      return result;
    },
    changeAvatar(e) {
      if (e.target.files[0].size > 2 * 1024 * 1024) {
        this.$msg("Dữ liệu không đưuọc quá 2MB!!!", "error");
        return;
      }
      this.imgSingle = e.target.files[0];
      var reader = new FileReader();
      var this_ = this;
      reader.onloadend = function () {
        this_.currentItem.avatar = reader.result;
      };
      reader.readAsDataURL(e.target.files[0]);
    },
    changeListImgExtra(e) {
      var files = e.target.files || e.dataTransfer.files;
      if (!files.length) return;
      this.createImage(files);
    },
    createImage(files) {
      var this_ = this;
      for (var index = 0; index < files.length; index++) {
        var reader = new FileReader();
        reader.onload = function (event) {
          const imageUrl = event.target.result;
          this_.images.push(imageUrl);
        };
        reader.readAsDataURL(files[index]);
      }
    },
    removeImage(index) {
      this.images.splice(index, 1);
    },
    removeAvatar() {
      var this_ = this;
      this_.currentItem.avatar = null;
    },

    New() {
      this.currentItem = {
        id: "Auto Gennerate",
        phoneNumber: this.userData.phone,
        createBy: this.userData.name,
        userId: this.userData.id,
      };
      if (this.selected == "ghost/room") {
        this.prev();
        this.allFields.forEach((x) => {
          switch (x.field) {
            case "categoryId":
              x.select2 = this.menus;
              x.select2[x.select2.length - 1] = {
                label: "Chọn danh mục",
                attrs: { disabled: true },
              };
              break;
            case "subjectId":
              x.select2 = [
                { value: 0, label: "Tất cả" },
                { value: 1, label: "Nam" },
                { value: 2, label: "Nữ" },
                { label: "Chọn đối tượng", attrs: { disabled: true } },
              ];
              break;
            case "consciousId":
              x.select2 = this.conscious;
              x.select2[x.select2.length - 1] = {
                label: "Chọn tỉnh thành",
                attrs: { disabled: true },
              };
              break;
            case "packageId":
              x.select2 = this.packageService;
              x.select2[x.select2.length - 1] = {
                label: "Chọn loại tin",
                attrs: { disabled: true },
              };
              break;
            default:
              break;
          }
        });
      }
      this.checkPay = false;
      this.editMode = false;
      this.checkExtend = false;
      this.editModal.show();
    },
    uploadFiles(file) {
      let formData = new FormData();
      formData.append("file", file);
      this.$store.dispatch("uploadFile", formData);
    },
    Edit(item) {
      this.checkPay = false;
      this.checkExtend = false;
      this.currentItem = {};
      if (this.selected == "ghost/room") {
        this.prev();
        this.allFields.forEach((x) => {
          switch (x.field) {
            case "categoryId":
              x.select2 = this.menus;
              x.select2[x.select2.length - 1] = {
                label: "Chọn danh mục",
                attrs: { disabled: true },
              };
              break;
            case "subjectId":
              x.select2 = [
                { value: 0, label: "Tất cả" },
                { value: 1, label: "Nam" },
                { value: 2, label: "Nữ" },
                { label: "Chọn đối tượng", attrs: { disabled: true } },
              ];
              break;
            case "consciousId":
              x.select2 = this.conscious;
              x.select2[x.select2.length - 1] = {
                label: "Chọn tỉnh thành",
                attrs: { disabled: true },
              };
              break;
            case "packageId":
              x.select2 = this.packageService;
              x.select2[x.select2.length - 1] = {
                label: "Chọn loại tin",
                attrs: { disabled: true },
              };
              break;
            default:
              break;
          }
        });

        this.$refs.contentVi.setHTML(item.content_vi);
        this.$refs.contentEn.setHTML(item.content_en);
        this.currentItem.consciousId = item.consciousId;
        nextTick(() => {
          this.currentItem.districtId = item.districtId;
          nextTick(() => {
            this.currentItem = item;
          });
        });
      } else {
        this.currentItem = item;
      }
      this.editMode = true;
      this.editModal.show();
    },
    Pay(item) {
      this.showDescription = true;
      this.allFields.map((item) => {
        item.checkPayment = true;
      });
      this.allFields
        .filter((m) => m.types == "payment")
        .map((item) => {
          item.checkPayment = false;
          item.addHidden = false;
        });
      this.checkPay = true;
      this.editMode = false;
      this.checkExtend = false;
      this.currentItem = {};
      if (this.selected == "ghost/room") {
        this.allFields.forEach((x) => {
          switch (x.field) {
            case "categoryId":
              x.select2 = this.menus;
              x.select2[x.select2.length - 1] = {
                label: "Chọn danh mục",
                attrs: { disabled: true },
              };
              break;
            case "subjectId":
              x.select2 = [
                { value: 0, label: "Tất cả" },
                { value: 1, label: "Nam" },
                { value: 2, label: "Nữ" },
                { label: "Chọn đối tượng", attrs: { disabled: true } },
              ];
              break;
            case "consciousId":
              x.select2 = this.conscious;
              x.select2[x.select2.length - 1] = {
                label: "Chọn tỉnh thành",
                attrs: { disabled: true },
              };
              break;
            case "packageId":
              x.select2 = this.packageService;
              x.select2[x.select2.length - 1] = {
                label: "Chọn loại tin",
                attrs: { disabled: true },
              };
              break;
            default:
              break;
          }
        });

        this.$refs.contentVi.setHTML(item.content_vi);
        this.$refs.contentEn.setHTML(item.content_en);
        this.currentItem.consciousId = item.consciousId;
        nextTick(() => {
          this.currentItem.districtId = item.districtId;
          nextTick(() => {
            this.currentItem = item;
          });
        });
      }

      this.editModal.show();
    },
    async Delete(item) {
      this.currentItem.id = item.id;
      confirm
        .fire({
          title: "Bạn đã chắc chắn ?",
          text: "Khách thuê sẽ không thể xem lại bài viết này!",
          icon: "warning",
          showCancelButton: true,
          confirmButtonText: "Ẩn bài viết!",
          cancelButtonText: "Trở về!",
        })
        .then((result) => {
          if (result.value) {
            this.currentItem.status = 4;
            this.$store.dispatch("hidenMd", {
              vm: this,
              params: this.currentItem,
              mess_sucess: "Ẩn tin thành công",
              mess_error: "Ẩn tin thất bại  ",
            });
          }
        });
    },
    async Restore(item) {
      this.currentItem.id = item.id;
      confirm
        .fire({
          title: "Bạn đã chắc chắn ?",
          text: "Khách thuê sẽ xem được bài viết này!",
          icon: "warning",
          showCancelButton: true,
          confirmButtonText: "Hiển thị bài viết!",
          cancelButtonText: "Trở về!",
        })
        .then((result) => {
          if (result.value) {
            this.currentItem.status = 2;
            this.$store.dispatch("hidenMd", {
              vm: this,
              params: this.currentItem,
              mess_sucess: "Hiển thị lại thành công",
              mess_error: "Hiển thị lại thất bại  ",
            });
          }
        });
    },
    async handleOk() {
      if (
        this.options.filter((i) => i.value == this.selected)[0].value ==
        "ghost/room"
      ) {
        this.currentItem.sortDescription = this.$refs.contentVi.getHTML();
        this.currentItem.description = this.$refs.contentEn.getHTML();
        var listImageExtra = "";
        this.images.forEach((i) => {
          listImageExtra = i + "!" + listImageExtra;
        });
        this.currentItem.listImgExtra = listImageExtra;
        if (!this.editMode) {
          this.allFields
            .filter((m) => m.types != "payment")
            .map((item) => {
              item.checkPayment = true;
            });
          this.showDescription = true;
        }

        var z = this.allFields.filter((m) => m.types == "payment");
        if (
          z[0].addHidden == false &&
          z[0].checkPayment == false &&
          this.editMode == false
        ) {
          var today = new Date();
          var dateExpiration = this.addDays(
            today,
            Number(this.currentItem.numberDay)
          );
          this.currentItem.dateSubmited =
            today.getFullYear() +
            "-" +
            this.formatDate(today.getMonth()) +
            "-" +
            this.formatDate(today.getDate());
          this.currentItem.dateExpiration =
            dateExpiration.getFullYear() +
            "-" +
            this.formatDate(dateExpiration.getMonth()) +
            "-" +
            this.formatDate(dateExpiration.getDate());
          if (this.typePay == "TTTK") {
            var x =
              this.getPriceInPackageService(this.currentItem.packageId) *
              this.currentItem.numberDay;

            if (this.balance < x) {
              this.currentItem.status = 0;
              this.$msg(
                "Số tiền không đủ để thanh toán (Lưu và và thanh toán sau)",
                "error"
              );
              if (!this.checkPay) {
                this.saveNews();
              }
            } else {
              this.$store.dispatch("updateUser", {
                vm: this,
                mess_sucess:
                  "Thanh toán thành công số dư còn lại là : " +
                  (this.balance - x).toString() +
                  "VNĐ",
                mess_error: "Thanh toán thất bại ",
                params: {
                  id: this.userData.id,
                  accountBalance: this.balance - x,
                },
              });
              this.currentItem.status = 1;
            }
          }
        }
        if (this.editMode) {
          this.$store.dispatch("editMd", {
            vm: this,
            params: this.currentItem,
            mess_sucess: "Cập nhật thành công",
            mess_error: "Cập nhật thất bại  ",
          });
        }
        if (!this.editMode) {
          this.allFields
            .filter((m) => m.types == "payment")
            .map((item) => {
              item.addHidden = false;
              item.checkPayment = false;
            });
        }
      }
    },
    Extend(item) {
      this.checkExtend = true;
      this.showDescription = true;
      this.allFields.map((item) => {
        item.checkPayment = true;
      });
      this.allFields
        .filter((m) => m.types == "payment")
        .map((item) => {
          item.checkPayment = false;
          item.addHidden = false;
        });
      this.checkPay = true;
      this.editMode = false;
      this.currentItem = {};
      if (this.selected == "ghost/room") {
        this.allFields.forEach((x) => {
          switch (x.field) {
            case "categoryId":
              x.select2 = this.menus;
              x.select2[x.select2.length - 1] = {
                label: "Chọn danh mục",
                attrs: { disabled: true },
              };
              break;
            case "subjectId":
              x.select2 = [
                { value: 0, label: "Tất cả" },
                { value: 1, label: "Nam" },
                { value: 2, label: "Nữ" },
                { label: "Chọn đối tượng", attrs: { disabled: true } },
              ];
              break;
            case "consciousId":
              x.select2 = this.conscious;
              x.select2[x.select2.length - 1] = {
                label: "Chọn tỉnh thành",
                attrs: { disabled: true },
              };
              break;
            case "packageId":
              x.select2 = this.packageService;
              x.select2[x.select2.length - 1] = {
                label: "Chọn loại tin",
                attrs: { disabled: true },
              };
              break;
            default:
              break;
          }
        });

        this.$refs.contentVi.setHTML(item.content_vi);
        this.$refs.contentEn.setHTML(item.content_en);
        this.currentItem.consciousId = item.consciousId;
        nextTick(() => {
          this.currentItem.districtId = item.districtId;
          nextTick(() => {
            this.currentItem = item;
          });
        });
      }
      this.editModal.show();
    },
    payment() {
      this.$store.dispatch("editMd", {
        vm: this,
        params: this.currentItem,
        mess_sucess: this.checkExtend
          ? "Gia hạn thành công"
          : "Thanh toán thành công!",
        mess_error: this.checkExtend
          ? "Gia hạn thất bại"
          : "Thanh toán thất bại!",
      });
    },
    prev() {
      if (!this.editModal) {
        this.currentItem.id = "Auto Gennerate";
      }
      this.showDescription = false;
      this.allFields.map((item) => {
        item.checkPayment = false;
      });
      this.allFields
        .filter((m) => m.types == "payment")
        .map((item) => {
          item.checkPayment = true;
          item.addHidden = true;
        });
    },
    formatDate(Date) {
      if (Date.toString().length < 2) {
        return ("0" + Date).toString();
      }
      return Date.toString();
    },
    loadBalance() {
      this.$store.dispatch("fetchBalance");
    },
    returnMoney() {
      var x =
        this.getPriceInPackageService(this.currentItem.packageId) *
        this.currentItem.numberDay;
      this.$store.dispatch("returnMoney", {
        vm: this,
        mess_sucess:
          "Hoàn tiền thành công  số dư hiện tại: " +
          (this.balance + x).toString() +
          "VNĐ",
        mess_error: "Hoàn tiền thất bạt ",
        params: {
          id: this.userData.userId,
          accountBalance: this.balance + x,
        },
      });
    },
    loadMasterData() {
      this.selected = this.$route.meta.type;
      this.allFields = this.options.filter(
        (i) => i.value == this.selected
      )[0].fields;
      this.fields = JSON.parse(
        JSON.stringify(
          this.options.filter((i) => i.value == this.selected)[0].fields
        )
      );
      this.fields.forEach((i) => {
        delete i.select2;
        delete i.cols;
        delete i.autoIncrement;
        delete i.required;
      });
      if (this.selected == "ghost/room") {
        if (this.userData) {
          this.serverParams.filter.columnFilters = {
            userId: this.userData.id,
          };
          this.serverParams.filter.sort.push({
            field: "dateSubmited",
            type: "desc",
          });
          this.loadItems();
        }
      } else {
        (this.serverParams = {
          filter: {
            page: 0,
            pageSize: 10,
            columnFilters: { userId: this.userData.id },
            sort: [],
          },
        }),
          this.loadItems();
      }
    },
    saveNews() {
      if (this.currentItem.id == "Auto Gennerate") {
        this.currentItem.id = "";
      }
      this.$store.dispatch("newUser", {
        vm: this,
        params: this.currentItem,
      });
    },
    historyPayment(roomId) {
      var fee =
        this.getPriceInPackageService(this.currentItem.packageId) *
        this.currentItem.numberDay;
      if (this.balance >= fee) {
        var param = {
          typeBalances: "PAYMENT",
          surplus: this.balance + fee,
          fee: fee,
          remaining: this.balance,
          status: 1,
          categoryId: this.currentItem.categoryId,
          userId: this.userData.id,
          roomId: roomId,
          methodBalances: this.typePay,
        };
        this.$store.dispatch("newBalances", param);
      }
    },
    getTitle() {
      return this.options.filter((i) => i.value === this.selected)[0].text;
    },
    getTitleModal() {
      if (this.editMode) {
        return (
          "Cập Nhật " +
          this.options.filter((i) => i.value === this.selected)[0].text
        );
      } else {
        return (
          "Thêm Mới " +
          this.options.filter((i) => i.value === this.selected)[0].text
        );
      }
    },
    updateParams(newProps) {
      this.serverParams.filter = Object.assign(
        {},
        this.serverParams.filter,
        newProps
      );
    },
    showStatus(status) {
      switch (status) {
        case 0:
          return { color: "red", mess: "Chưa thanh toán" };
        case 1:
          return { color: "#febb02", mess: "Đang duyệt" };
        case 2:
          return { color: "#77ba00", mess: "Đang hiển thị" };
        case 3:
          return { color: "#ff6600", mess: "Đã hết hạn" };
        case 4:
          return { color: "#ea2e9d", mess: "Đã ẩn" };
      }
    },
    showStatusBalances(status) {
      switch (status) {
        case 0:
          return { color: "red", mess: "Thất Bại" };
        case 1:
          return { color: "#77ba00", mess: "Thành Công" };
      }
    },
    getValidation(item) {
      const require = "required";
      const validation = "validation:" + item.field;
      let len = "length:";
      let min = "min:";
      if (item.length) len += `${item.length},${item.length}`;
      else if (item.maxLength) len += `0,${item.maxLength}`;
      else if (item.minLength) len += `${item.minLength}`;
      else if (item.min) min += `${item.min}`;
      else len += `0,255`;
      var result = "";
      if (!item.length && item.required && item.min) {
        return result.concat(require, "|", validation, "|", min);
      }
      if (item.required) {
        return result.concat(require, "|", validation);
      }
      if (item.required && !item.min) {
        return result.concat(require, "|", validation, "|", len);
      }
      if (item.min && item.required) {
        return result.concat(require, "|", validation, "|", len, "|", min);
      }
      return result.concat(validation);
    },
    showErrors(i) {
      switch (i.field) {
        case "title":
          if (i.minLength) {
            return i.label + " dài tối thiểu " + i.minLength + " kí tự";
          }
          return "";
        case "price":
          if (i.min) {
            return (
              i.label +
              " có giá trị >= " +
              new Intl.NumberFormat("vi-VN", { currency: "VND" }).format(
                i.min
              ) +
              " đ/tháng"
            );
          }
          return "";
        case "area":
          if (i.min) {
            return i.label + " có giá trị >= " + i.min + "m2";
          }
          return "";
        case "specificAddress":
          if (i.minLength) {
            return i.label + " dài tối thiểu " + i.minLength + " kí tự";
          }
          return "";
        case "slug":
          if (i.minLength) {
            return i.label + " dài tối thiểu " + i.minLength + " kí tự";
          }
          return "";
        default:
          break;
      }
    },
    async checkUnquie(i) {
      var ul = document.createElement("ul");
      var li = document.createElement("li");
      li.innerHTML = i.label + " đã tồn tại ! ";
      ul.classList.add("custom-mess-error");
      ul.append(li);
      var mess_error =
        this.$refs[i.ref][0].getElementsByClassName("formkit-messages")[0];
      var k = this.$refs[i.ref][0].getElementsByClassName("formkit-outer")[0];
      if (mess_error == undefined) {
        if (this.currentItem[i.field] != "") {
          var columnFilters = {};
          columnFilters[i.field + "Ext"] = this.currentItem[i.field];
          axios
            .get($themeConfig.url + "ghost/room/paginations/", {
              params: {
                filter: {
                  page: 0,
                  pageSize: 1,
                  columnFilters: columnFilters,
                  sort: [],
                },
              },
            })
            .then((response) => {
              if (response.data.content.length > 0) {
                var o = this.$refs[i.ref][0]
                  .getElementsByClassName("formkit-outer")[0]
                  .getElementsByClassName("custom-mess-error")[0];
                if (o != undefined) {
                  o.remove();
                }
                k.append(ul);
                i.checkUnquie = true;
              } else {
                var z = this.$refs[i.ref][0]
                  .getElementsByClassName("formkit-outer")[0]
                  .getElementsByClassName("custom-mess-error")[0];
                if (z != undefined) {
                  i.checkUnquie = false;
                  z.remove();
                }
              }
            });
        }
      } else {
        var z = this.$refs[i.ref][0]
          .getElementsByClassName("formkit-outer")[0]
          .getElementsByClassName("custom-mess-error")[0];

        if (z != undefined) {
          i.checkUnquie = false;
          z.remove();
        }
      }
    },
    onPageChange(params) {
      this.updateParams({ page: params.currentPage - 1 });
      this.loadItems();
    },
    onPerPageChange(params) {
      this.updateParams({ pageSize: params.currentPerPage });
      this.loadItems();
    },
    onSortChange(params) {
      this.updateParams({
        sort: params,
      });

      this.loadItems();
    },
    onColumnFilter(params) {
      this.serverParams.page = 1;
      this.updateParams(params);
      this.loadItems();
    },
    loadItems() {
      this.$store.dispatch("fetchMd", {
        type: this.selected,
        params: this.serverParams,
      });
    },
    reloadRoom(totalNumberSeconds) {
      if (totalNumberSeconds > 0) {
        if (this.userData) {
          this.serverParams.filter.columnFilters = {
            userId: this.userData.userId,
          };
          this.serverParams.filter.sort = [
            {
              field: "dateSubmited",
              type: "desc",
            },
          ];
          setTimeout(() => {
            this.loadItems();
            this.autoGetRoom = !this.autoGetRoom;
          }, totalNumberSeconds);
        }
      }
    },
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
          value: item.code + "-" + item.codename,
          label: item.name,
        }));
        var string_type = type == "districts" ? "Quận/huyện" : "Phường/xã";
        result.unshift({
          value: "",
          label: "Tất Cả " + string_type,
        });
      }
      return result;
    },
  },
  data() {
    return {
      autoGetRoom: false,
      checkMoneyPay: true,
      userData: null,
      typePay: "TTTK",
      isLoading: false,
      totalRecords: 0,
      checkPay: false,
      checkExtend: false,
      selected: "ghost/room",
      editModal: null,
      img: null,
      districts: [],
      currentItem: {},
      fields: [],
      allFields: [],
      titleTable: null,
      showDescription: false,
      images: [],
      imgSingle: null,
      guide_vi: false,
      guide_en: false,
      modules: [FreeMode, Pagination],
      options: [
        {
          value: "ghost/room",
          text: " Tin Đăng",
          DeleleEnabled: true,
          RestoreEnabled: true,
          EditEnabled: true,
          ExtendEnabled: true,
          NewEnabled: true,
          PayEnabled: true,
          fields: [
            {
              field: "packageId",
              types: "payment",
              filterOptions: {},
              label: "Loại tin",
              width: "200px",
              select2: [],
              hidden: true,
              required: true,
              cols: 4,
              addHidden: true,
              checkPayment: false,
            },
            {
              field: "packageDay",
              types: "payment",
              filterOptions: {},
              label: "gói thời gian",
              width: "200px",
              select2: [
                { value: 30, label: "1 tháng" },
                { value: 7, label: "1 tuần" },
                { value: 5, label: "5 ngày" },
                { label: "Chọn gói thời gian", attrs: { disabled: true } },
              ],
              hidden: true,
              required: false,
              cols: 4,
              addHidden: true,
              checkPayment: false,
            },
            {
              field: "numberDay",
              type: "number",
              types: "payment",
              filterOptions: {},
              label: "số ngày",
              width: "200px",
              hidden: true,
              required: true,
              min: 5,
              length: false,
              cols: 4,
              addHidden: true,
              checkPayment: false,
            },
            {
              field: "id",
              filterOptions: { enabled: true, placeholder: "Lọc Mã Tin" },
              label: "Mã tin",
              disabled: true,
              hidden: true,
              width: "110px",
              checkPayment: false,
              cols: 4,
            },
            {
              field: "avatar",
              types: "image",
              filterOptions: { enabled: false, placeholder: "" },
              label: "Ảnh đại diện",
              required: true,
              width: "120px",
              addHidden: true,
              checkPayment: false,
            },
            {
              field: "categoryName",
              filterOptions: { enabled: true, placeholder: "Danh mục" },
              label: "Danh mục",
              width: "200px",
              addHidden: true,
              required: true,
              checkPayment: false,
            },
            {
              field: "categoryId",
              filterOptions: {},
              label: "Danh mục",
              width: "200px",
              select2: [],
              hidden: true,
              required: true,
              cols: 4,
              checkPayment: false,
            },
            {
              field: "title",
              filterOptions: { enabled: true, placeholder: "Lọc Tiêu Đề" },
              label: "Tiêu đề",
              width: "200px",
              minLength: 20,
              required: true,
              validUnquie: true,
              checkUnquie: false,
              ref: "titles",
              cols: 4,
              checkPayment: false,
            },
            {
              field: "slug",
              filterOptions: {},
              label: "Tên tìm kiếm",
              width: "130px",
              hidden: true,
              required: true,
              minLength: 10,
              validUnquie: true,
              ref: "slug",
              checkUnquie: false,
              cols: 4,
              checkPayment: false,
            },
            {
              field: "price",
              types: "price",
              type: "number",
              filterOptions: { enabled: true, placeholder: "" },
              label: "Giá/tháng",
              width: "130px",
              required: true,
              min: 100000,
              cols: 4,
              checkPayment: false,
            },
            {
              field: "area",
              types: "area",
              type: "number",
              filterOptions: { enabled: true, placeholder: "" },
              label: "Diện tích/m2",
              hidden: true,
              required: true,
              min: 10,
              cols: 4,
              checkPayment: false,
            },
            {
              field: "titleService",
              filterOptions: { enabled: true, placeholder: "" },
              label: "Loại gói",
              width: "100px",
              addHidden: true,
              cols: 4,
              checkPayment: false,
            },
            {
              field: "subjectId",
              filterOptions: { enabled: true, placeholder: "" },
              label: "Đối tượng cho thuê",
              width: "100px",
              hidden: true,
              select2: [],
              required: true,
              cols: 4,
              checkPayment: false,
            },
            {
              field: "videoLink",
              filterOptions: { enabled: true, placeholder: "" },
              label: "Video Link (Youtube)",
              width: "100px",
              hidden: true,
              required: false,
              cols: 4,
              checkPayment: false,
            },
            {
              field: "map",
              filterOptions: { enabled: true, placeholder: "" },
              label: "Bản đồ <iframe/>",
              width: "100px",
              hidden: true,
              required: true,
              cols: 4,
              checkPayment: false,
            },
            {
              field: "consciousId",
              filterOptions: { enabled: true, placeholder: "" },
              label: "Tỉnh thành",
              width: "100px",
              hidden: true,
              select2: [],
              maxLength: 15,
              required: true,
              cols: 4,
              checkPayment: false,
            },
            {
              field: "districtId",
              filterOptions: { enabled: true, placeholder: "" },
              label: "Quận/huyện",
              width: "100px",
              hidden: true,
              maxLength: 15,
              select2: [],
              required: true,
              cols: 4,
              checkPayment: false,
            },
            {
              field: "wardId",
              filterOptions: { enabled: true, placeholder: "" },
              label: "Phường/xã",
              width: "100px",
              hidden: true,
              maxLength: 15,
              select2: [],
              required: true,
              cols: 4,
              checkPayment: false,
            },
            {
              field: "specificAddress",
              filterOptions: { enabled: true, placeholder: "" },
              label: "Địa chỉ cụ thể (số nhà - tên đường)",
              width: "100px",
              hidden: true,
              minLength: 20,
              required: true,
              cols: 4,
              checkPayment: false,
            },
            {
              field: "phoneNumber",
              filterOptions: { enabled: true, placeholder: "" },
              disabled: true,
              label: "Số điện thoại liên hệ",
              width: "100px",
              hidden: true,
              cols: 4,
              checkPayment: false,
            },
            {
              field: "createBy",
              filterOptions: { enabled: true, placeholder: "" },
              disabled: true,
              label: "Người liên hệ",
              width: "100px",
              hidden: true,
              cols: 4,
              checkPayment: false,
            },
            {
              field: "dateSubmited",
              filterOptions: {
                enabled: true,
                placeholder: "",
                filterFn: this.dateRangeFilter,
              },
              label: "Ngày bắt đầu",
              dateInputFormat: "MM-DD-YYYY",
              dateOutputFormat: "MM-DD-YYYY",
              required: true,
              addHidden: true,
              cols: 3,
              checkPayment: false,
            },
            {
              field: "dateExpiration",
              dateInputFormat: "MM-DD-YYYY",
              dateOutputFormat: "MM-DD-YYYY",
              addHidden: true,
              filterOptions: {
                enabled: true,
                placeholder: "",
                filterFn: this.dateRangeFilter,
              },
              label: "Ngày hết hạn",
              required: true,
              cols: 3,
              checkPayment: false,
            },
            {
              field: "avatar",
              types: "singleFile",
              filterOptions: { enabled: false, placeholder: "" },
              label: "Ảnh đại diện",
              required: true,
              hidden: true,
              maxLength: 10,
              method: "uploadAvatar",
              multiple: false,
              cols: 3,
              checkPayment: false,
            },
            {
              field: "listImgExtra",
              types: "mutipleFile",
              filterOptions: { enabled: false, placeholder: "" },
              label: "Danh sách ảnh xem thêm",
              required: true,
              hidden: true,
              multiple: true,
              maxLength: 10,
              cols: 9,
              checkPayment: false,
            },
            {
              field: "status",
              types: "status_room",
              width: "150px",
              addHidden: true,
              filterOptions: {
                enabled: true,
                placeholder: "Tất Cả",
                filterDropdownItems: [
                  { value: "0", text: "Chưa thanh toán" },
                  { value: "1", text: "Đang duyệt" },
                  { value: "2", text: "Đang hiển thị" },
                  { value: "3", text: "Đã hết hạn" },
                  { value: "4", text: "Đã ẩn" },
                ],
              },
              label: "Trạng thái",
              required: true,
              tdClass: "text-center",
              checkPayment: false,
            },
            {
              field: "action",
              label: "Action",
              width: "90px",
              tdClass: "text-center",
            },
          ],
        },
        {
          value: "user/balances",
          text: " Lịch Sử Thanh Toán",
          DeleleEnabled: false,
          EditEnabled: false,
          NewEnabled: false,
          fields: [
            {
              field: "id",
              filterOptions: {
                enabled: true,
                placeholder: "Lọc Mã Thanh Toán",
              },
              label: "Mã thanh toán",
              disabled: false,
              width: "110px",
              cols: 4,
            },
            {
              field: "methodBalances",
              filterOptions: {
                enabled: true,
                placeholder: "Lọc Loại Hoạt Động",
              },
              label: "Loại hoạt động",
              width: "110px",
              cols: 4,
            },
            {
              field: "roomName",
              filterOptions: {
                enabled: true,
                placeholder: "Lọc Tiêu Đề Tin",
              },
              label: "Tiêu đề tin",
              width: "110px",
              cols: 4,
            },
            {
              field: "categoryName",
              filterOptions: {
                enabled: true,
                placeholder: "Lọc Loại Tin",
              },
              label: "Loại tin",
              width: "110px",
              cols: 4,
            },
            {
              field: "surplus",
              types: "price",
              filterOptions: {
                enabled: true,
                placeholder: "Lọc Số Dư",
              },
              label: "Số dư",
              width: "110px",
              cols: 4,
            },
            {
              field: "fee",
              types: "price",
              filterOptions: {
                enabled: true,
                placeholder: "Lọc Phí Thanh Toán",
              },
              label: "Phí",
              width: "110px",
              cols: 4,
            },
            {
              field: "remaining",
              types: "price",
              filterOptions: {
                enabled: true,
                placeholder: "Lọc Tiền Còn Lại",
              },
              label: "Tiền Còn Lại",
              width: "110px",
              cols: 4,
            },
            {
              field: "createDate",
              filterOptions: {
                enabled: true,
                placeholder: "Lọc Ngày Thanh Toán",
              },
              label: "Ngày Thanh Toán",
              width: "110px",
              cols: 4,
            },
            {
              field: "status",
              types: "status_balances",
              filterOptions: {
                enabled: true,
                placeholder: "Tất cả",
                filterDropdownItems: [
                  { value: "0", text: "Thất bại" },
                  { value: "1", text: "Thành công" },
                ],
              },
              label: "Trạng Thái",
              tdClass: "text-center",
              width: "110px",
              cols: 4,
            },
          ],
        },
        {
          value: "user/deposit",
          text: " Lịch Sử Nạp Tiền",
          DeleleEnabled: false,
          EditEnabled: false,
          NewEnabled: false,
          fields: [
            {
              field: "id",
              filterOptions: {
                enabled: true,
                placeholder: "Lọc Mã Nạp tiền",
              },
              label: "Mã nạp tiền",
              disabled: false,
              width: "110px",
              cols: 4,
            },
            {
              field: "methodDeposit",
              filterOptions: {
                enabled: true,
                placeholder: "Lọc Phương Thức Nạp",
              },
              label: "Phương thức nạp",
              width: "110px",
              cols: 4,
            },
            {
              field: "amountDeposit",
              types: "price",
              filterOptions: {
                enabled: true,
                placeholder: "Lọc Số Tiền",
              },
              label: "Số tiền",
              width: "110px",
              cols: 4,
            },
            {
              field: "giveExtra",
              filterOptions: {
                enabled: true,
                placeholder: "% khuyến mãi",
              },
              label: "Loại % khuyến mãi",
              width: "110px",
              cols: 4,
            },
            {
              field: "amountReality",
              types: "price",
              filterOptions: {
                enabled: true,
                placeholder: "Lọc Số Tiền Nhận Thực",
              },
              label: "Số Tiền Nhận Thực",
              width: "110px",
              cols: 4,
            },
            {
              field: "createAt",
              filterOptions: {
                enabled: true,
                placeholder: "Lọc Ngày Nạp tiền",
              },
              label: "Ngày Nạp Tiền",
              width: "110px",
              cols: 4,
            },
            {
              field: "description",
              filterOptions: {
                enabled: true,
                placeholder: "Lọc Mô Tả",
              },
              label: "Mô tả",
              width: "110px",
              cols: 4,
            },
            {
              field: "status",
              types: "status_balances",
              filterOptions: {
                enabled: true,
                placeholder: "Tất cả",
                filterDropdownItems: [
                  { value: "0", text: "Thất bại" },
                  { value: "1", text: "Thành công" },
                ],
              },
              label: "Trạng Thái",
              tdClass: "text-center",
              width: "110px",
              cols: 4,
            },
          ],
        },
      ],
      serverParams: {
        filter: { page: 0, pageSize: 10, columnFilters: {}, sort: [] },
      },
    };
  },
};
</script>

<style>
input[type="number"]::-webkit-inner-spin-button,
input[type="number"]::-webkit-outer-spin-button {
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  margin: 0;
}
.showGuide {
  cursor: pointer;
  border-bottom: 1px solid blue;
  color: blue;
  font-weight: bold;
}

.title-table {
  font-size: 20px !important;
}
.vgt-table td,
.vgt-table th {
  color: #fff !important;
  padding: 5px !important;
  vertical-align: middle !important;
  font-size: 0.85rem !important;
  /* font-family: Nunito, sans-serif !important; */
}
.vgt-table td img {
  max-width: 100% !important;
  width: 100% !important;
  height: 60px !important;
  object-fit: cover !important;
}
.vgt-table th {
  text-align: center !important;
  font-size: 14px !important;
  font-weight: 545 !important;
}
.vgt-wrap__footer {
  padding: 6px 12px !important;
}
.vgt-wrap__footer .footer__row-count__label,
.vgt-wrap__footer .footer__row-count__select,
.vgt-wrap__footer .footer__navigation,
.vgt-wrap__footer .footer__navigation__page-btn span {
  font-size: 0.85rem !important;
  color: #fff !important;
}

.vgt-wrap__footer .footer__row-count__select {
  background: #0e1726;
}

.vgt-table,
.vgt-wrap__footer.vgt-clearfix {
  background: #0e1726 !important;
  border-color: #3b3f5c !important;
  color: #fff !important;
}
.vgt-table.bordered td,
.vgt-table.bordered th {
  border: 1px solid #3b3f5c !important;
}
.vgt-table.bordered td,
.vgt-table.bordered th {
  background: #0e1726 !important;
}
.vgt-input,
.vgt-select {
  background-color: #3b3f5c !important;
  color: #d3d3d3 !important;
  border-color: #3b3f5c !important;
}
::placeholder {
  color: #d3d3d3 !important;
}
.btn-action {
  display: flex;
  justify-content: center;
  align-items: center;
  background: #4600b9;
  padding: 3px;
  border-radius: 4px;
}
.sidebar.sidebar-offcanvas {
  position: fixed;
  width: 16%;
}

.btn-add {
  font-size: 13px !important;
  background: #4600b9 !important;
  cursor: pointer;
}
.btn-add:hover {
  background: #253883 !important;
}
h1,
h2,
h3,
h4,
h5 {
  font-family: Nunito, sans-serif !important;
}
.modal-title {
  color: #b5beca !important;
  font-weight: 500 !important;
}
.modal-header,
.modal-body,
.modal-footer {
  background: #0e1726 !important;
}
.modal-content .modal-footer {
  border-top: 0.5px solid #191e3a;
}
.modal-content .modal-header {
  border-bottom: 0.5px solid #191e3a;
}
.modal-backdrop {
  background-color: #1b2e4b !important;
  opacity: 0.8 !important;
}
.formkit-input {
  background: #3b3f5c !important;
  border-color: #3b3f5c !important ;
  color: #d3d3d3 !important;
}
.form-label {
  color: #b5beca !important;
  margin-bottom: 3px !important;
  display: block !important;
  font-size: 15px !important ;
  font-weight: lighter !important;
}
.formkit-wrapper {
  margin-top: 10px !important;
}
.form-control.form-control-sm {
  padding: 9px 16px !important;
  font-size: 13px !important;
}

.form-control:focus {
  color: #d3d3d3 !important;
  border-color: none !important;
  box-shadow: none !important;
}
.modal-content .modal-footer button.btn[data-dismiss="modal"] {
  border: 1px solid #1b2e4b !important;
  background-color: #1b2e4b !important;
  color: #bfc9d4 !important;
  font-weight: 500 !important;
  font-size: 14px !important;
}
.modal-content .modal-footer button.btn {
  font-weight: 500 !important;
  padding: 10px 25px !important;
  letter-spacing: 1px !important;
  font-size: 14px !important;
}
.modal-footer .btn-primary {
  box-shadow: 0 10px 20px -10px rgba(27, 85, 226, 0.59) !important;
}
.modal-footer .btn-primary:focus,
.btn-primary:hover {
  box-shadow: none !important;
}
.formkit-messages {
  padding: 2px 0 0 3px;
  margin: 0 0 6px 0;
  list-style-type: none;
  color: #dc3545;
  font-size: 0.8rem;
}
.custom-mess-error {
  padding-left: 0px !important;
}
.custom-mess-error li {
  padding: 2px 0 0 3px !important;
  margin: 0 0 6px 0 !important;
  list-style-type: none !important;
  color: #dc3545 !important;
  font-size: 0.8rem !important;
}
.HiddenAdd {
  display: none !important;
}
.ShowEdit {
  display: block !important;
}
.upload-image {
  height: 100px;
  border: 1px dotted #3b3f5c;
  border-radius: 5px;
}
.upload__img-wrap {
  width: 125px;
  height: 80px;
}
.upload__img-wrap img {
  max-width: 100% !important;
  width: 100% !important;
  height: 100%;
  border-radius: 5px;
  object-fit: cover;
}
.upload__img-wrap {
  position: relative;
}
.upload__img-wrap i {
  position: absolute;
  z-index: 100;
  font-size: 23px;
  color: #000;
  right: 3%;
  cursor: pointer;
}
.upload__img-wrap i:hover {
  color: #fff;
}
.ql-editor {
  color: #fff !important;
}
.btn-prev {
  padding: 8px 15px !important;
}
.form-check-label {
  font-size: 14px;
}
.form-check {
  margin-bottom: 10px;
}
.show_status {
  padding: 5px;
  border-radius: 10px;
  font-weight: 500;
}
</style>
