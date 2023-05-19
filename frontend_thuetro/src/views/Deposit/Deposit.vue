<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div>
    <div class="update-account col-lg-12 grid-margin stretch-card">
      <div class="card" style="border: none; color: #fff">
        <div
          class="card-body ps-2 pe-2 pt-2 pb-2"
          style="background-color: #191c24; border-radius: 5px; height: 70vh"
        >
          <div
            class="card-title d-flex justify-content-between align-items-center"
          >
            <span class="title-table">Nạp tiền vào tài khoản</span>
          </div>
          <div class="row">
            <div class="col-9">
              <div class="row">
                <div class="col-4">
                  <div
                    class="interface-deposit"
                    @click="showModalDesposit('DEMO')"
                  >
                    <div class="img-type-deposit">
                      <img
                        style="width: 100%"
                        src="https://res.cloudinary.com/dqdar3g4l/image/upload/v1684082099/panama-bank-account_fcwe6z.jpg"
                        alt=""
                      />
                    </div>
                    <div class="title-type-deposit">Demo Test Bank</div>
                  </div>
                </div>
                <div class="col-4">
                  <div
                    @click="showModalDesposit('CHUYEN_KHOAN')"
                    class="interface-deposit"
                  >
                    <div class="img-type-deposit">
                      <img
                        style="width: 100%"
                        src="https://res.cloudinary.com/dqdar3g4l/image/upload/v1684083838/bank-transfer-payment-method-620x388_zettxr.jpg"
                        alt=""
                      />
                    </div>
                    <div class="title-type-deposit">Chuyển Khoản</div>
                  </div>
                </div>
                <div class="col-4">
                  <div
                    @click="showModalDesposit('MOMO')"
                    class="interface-deposit"
                  >
                    <div class="img-type-deposit">
                      <img
                        style="width: 100%"
                        src="https://res.cloudinary.com/dqdar3g4l/image/upload/v1684083973/MoMo_Logo_gtfrrw.png"
                        alt=""
                      />
                    </div>
                    <div class="title-type-deposit">MOMO</div>
                  </div>
                </div>
                <div class="col-4">
                  <div class="interface-deposit">
                    <div class="img-type-deposit">
                      <img
                        style="width: 100%"
                        src="https://res.cloudinary.com/dqdar3g4l/image/upload/v1684084115/chlctvfexgdbyfwqu6rn_fqy25q.jpg"
                        alt=""
                      />
                    </div>
                    <div class="title-type-deposit">VN PAY</div>
                  </div>
                </div>
                <div class="col-4">
                  <div class="interface-deposit">
                    <div class="img-type-deposit">
                      <img
                        style="width: 100%"
                        src="https://res.cloudinary.com/dqdar3g4l/image/upload/v1684084162/agiletechvietnam-zalopay_cvzazj.png"
                        alt=""
                      />
                    </div>
                    <div class="title-type-deposit">ZALO PAY</div>
                  </div>
                </div>
                <div class="col-4">
                  <div class="interface-deposit">
                    <div class="img-type-deposit">
                      <img
                        style="width: 100%"
                        src="https://res.cloudinary.com/dqdar3g4l/image/upload/v1684084438/gfXcCyd98v83B3_OAr77S3EwsIGg8LyCYLuItRXhyi3SCHEeQg0kfL4M9uuksPL_rD8_gn1dis.png"
                        alt=""
                      />
                    </div>
                    <div class="title-type-deposit">Quét mã Qr</div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-3">
              <div class="amount">
                <span>Số dư tài khoản</span>
                <h3 v-if="balance >= 0">
                  {{
                    new Intl.NumberFormat("vi-VN", { currency: "VND" }).format(
                      this.balance
                    )
                  }}
                  đ
                </h3>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="modal fade" ref="showModal" tabindex="-1" role="dialog">
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
              <h5 class="modal-title">Nạp tiền vào tài khoản</h5>
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
                <h4 style="color: #fff">{{ getTitle() }}</h4>
                <div class="info-give-extra">
                  <p>
                    Tặng thêm +10% cho giá trị nạp từ 50.000đ đến dưới
                    1.000.000đ
                  </p>
                  <p>
                    Tặng thêm +20% cho giá trị nạp từ 1.000.000đ đến 2.000.000đ
                  </p>
                  <p>Tặng thêm +25% cho giá trị nạp trên 2.000.000đ</p>
                </div>
                <div
                  :class="['col-' + (i.cols != null ? i.cols : 6)]"
                  v-for="i in allFields"
                  :key="i.field"
                >
                  <FormKit
                    :name="i.field"
                    class="form-control form-control-sm"
                    :label-class="i.required ? 'required' : null"
                    :type="i.type ? i.type : 'text'"
                    :validation="getValidation(i)"
                    validation-visibility="dirty"
                    :validation-messages="{
                      validation: showErrors(i),
                      min: showErrors(i),
                      length: showErrors(i),
                      required: i.label + ' không được bỏ trống ',
                    }"
                    :label="i.label"
                    :disabled="i.disabled || i.key"
                    v-model="currentItem[i.field]"
                    :placeholder="'Nhập ' + i.label"
                  />
                </div>
              </div>
            </div>
            <div class="modal-footer">
              <button
                type="button"
                class="btn"
                data-dismiss="modal"
                data-bs-dismiss="modal"
              >
                <i class="flaticon-cancel-12"></i> Discard
              </button>
              <button class="btn btn-primary" type="submit">save</button>
            </div>
          </FormKit>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import "vue-good-table-next/dist/vue-good-table-next.css";
import { Modal } from "bootstrap";

export default {
  components: {},

  mounted() {
    this.userData = localStorage.userData
      ? JSON.parse(localStorage.userData)
      : null;
    this.showModal = new Modal(this.$refs.showModal);
  },
  unmounted() {
    this.showModal.hide();
  },
  watch: {
    "currentItem.amountDeposit": function (value) {
      this.currentItem.giveExtra = this.getExtraByAmountDeposit(value) + "%";
    },
  },
  computed: {
    balance() {
      return this.$store.getters.getBalance;
    },
  },
  methods: {
    getExtraByAmountDeposit(value) {
      if (value < 500000) {
        return 0;
      }
      if (value >= 500000 && value < 1000000) {
        return 10;
      }
      if (value >= 1000000 && value < 2000000) {
        return 20;
      }
      if (value >= 2000000) {
        return 25;
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
        case "amountDeposit":
          if (i.min) {
            return (
              i.label +
              " có giá trị >= " +
              new Intl.NumberFormat("vi-VN", { currency: "VND" }).format(
                i.min
              ) +
              " đ"
            );
          }
          return "";
        default:
          break;
      }
    },
    showModalDesposit(value) {
      this.allFields = this.options.filter((i) => i.value == value)[0].fields;
      this.typeMethodDeposit = value;
      this.showModal.show();
    },
    async handleOk() {
      this.reality =
        Number(this.currentItem.amountDeposit) +
        Number(
          this.currentItem.amountDeposit *
            (this.getExtraByAmountDeposit(this.currentItem.amountDeposit) / 100)
        );
      this.$store.dispatch("updateUser", {
        vm: this,
        mess_sucess:
          "Nạp tiền thành công số dư hiện tại : " +
          (this.balance + this.reality).toString() +
          "VNĐ",
        mess_error: "Thanh toán thất bại ",
        params: {
          id: this.userData.id,
          accountBalance: this.balance + this.reality,
        },
      });
    },
    historyDeposit() {
      this.currentItem = {
        ...this.currentItem,
        methodDeposit: this.typeMethodDeposit,
        amountReality: this.reality,
        status: 1,
        userId: this.userData.id,
      };
      this.$store.dispatch("newDeposit", {
        vm: this,
        params: this.currentItem,
      });
    },
    getTitle() {
      return this.options.filter((i) => i.value === this.typeMethodDeposit)[0]
        .text;
    },
    loadBalance() {
      this.$store.dispatch("fetchBalance");
    },
  },
  data() {
    return {
      checkDeposit: true,
      checkReloadDataUser: false,
      currentItem: {},
      allFields: [],
      typeMethodDeposit: "DEMO",
      userData: null,
      showModal: null,
      reality: null,
      options: [
        {
          value: "DEMO",
          text: "Test Demo Deposit",
          fields: [
            {
              field: "amountDeposit",
              type: "number",
              filterOptions: {},
              label: "Số tiền cần nạp (vnđ)",
              width: "200px",
              required: true,
              min: 100000,
              length: false,
              cols: 8,
            },
            {
              field: "giveExtra",
              type: "text",
              filterOptions: {},
              label: "Phần trăm tặng thêm",
              width: "200px",
              disabled: true,
              cols: 4,
            },
          ],
        },
        {
          value: "CHUYEN_KHOAN",
          text: "Chuyển khoản (đang được cập nhật thêm)",
          // fields: [{}],
        },
        {
          value: "MOMO",
          text: "MO MO (đang được cập nhật thêm)",
          // fields: [{}],
        },
      ],
    };
  },
};
</script>

<style>
.sidebar.sidebar-offcanvas {
  position: fixed;
  width: 16%;
}
.update-account .title-table {
  font-size: 20px !important;
}
.update-account .modal-title {
  color: #b5beca !important;
  font-weight: 500 !important;
}

.update-account .modal-footer {
  background: #0e1726 !important;
}
.update-account .form-control {
  background: #3b3f5c !important;
  border-color: #3b3f5c !important ;
  color: #d3d3d3 !important;
}
.update-account .form-label {
  color: #b5beca !important;
  margin-bottom: 3px !important;
  display: block !important;
  font-size: 15px !important ;
  font-weight: lighter !important;
}
.update-account .formkit-wrapper {
  margin-top: 10px !important;
}
.update-account .form-control.form-control-sm {
  padding: 9px 16px !important;
  font-size: 13px !important;
}

.update-account .form-control:focus {
  color: #d3d3d3 !important;
  border-color: none !important;
  box-shadow: none !important;
}

.update-account .formkit-messages {
  padding: 2px 0 0 3px;
  margin: 0 0 6px 0;
  list-style-type: none;
  color: #dc3545;
  font-size: 0.8rem;
}
.update-account .custom-mess-error {
  padding-left: 0px !important;
}
.update-account .custom-mess-error li {
  padding: 2px 0 0 3px !important;
  margin: 0 0 6px 0 !important;
  list-style-type: none !important;
  color: #dc3545 !important;
  font-size: 0.8rem !important;
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

/*  */
.img-type-deposit {
  width: 24%;
  margin: 0 auto;
}
.img-type-deposit img {
  border-radius: 5px;
  height: 72px;
  object-fit: cover;
}
img {
  max-width: 100% !important;
}
.interface-deposit {
  display: flex;
  justify-content: space-between;
  flex-direction: column;
}
.title-type-deposit {
  text-align: center;
  background: #3b3f5c;
  border-radius: 0px 0px 10px 10px;
  margin-top: 25px;
  padding: 10px 0px;
}
.interface-deposit {
  border: 1px solid #3b3f5c !important;
  padding-top: 40px;
  border-radius: 10px;
  transition: all 0.3s ease;
  margin-top: 20px;
}
.interface-deposit:hover {
  cursor: pointer;
  border: 1px solid #007bff !important;
  -webkit-box-shadow: 0 0 3px 1px #007bff;
  -moz-box-shadow: 0 0 3px 1px #007bff;
  box-shadow: 0 0 3px 1px #007bff;
}
.interface-deposit:hover .title-type-deposit {
  background: #007bff;
}
.amount {
  border: 1px solid #3b3f5c !important;
  margin-top: 20px;
  padding: 20px;
  text-align: center;
  border-radius: 8px;
}
/* modal */
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
.info-give-extra {
  padding: 10px;
  border: 1px solid #1b2e4b !important;
  border-radius: 10px;
}
</style>
