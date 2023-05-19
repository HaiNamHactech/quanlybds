<!-- eslint-disable no-undef -->
<template>
  <div>
    <div class="update-account col-lg-12 grid-margin stretch-card">
      <div class="card" style="border: none; color: #fff">
        <div
          class="card-body ps-2 pe-2 pt-2 pb-2"
          style="background-color: #191c24; border-radius: 5px"
        >
          <div
            class="card-title d-flex justify-content-between align-items-center"
          >
            <span class="title-table">Sửa thông tin cá nhân</span>
          </div>
          <div style="width: 70%; margin: 0 auto">
            <FormKit
              v-model="currentItem"
              :actions="false"
              :incomplete-message="false"
              type="form"
              @submit="handleOk"
            >
              <div class="row">
                <div class="col-12 mb-3">
                  <FormKit
                    :name="'id'"
                    class="form-control form-control-sm"
                    :label-class="'required'"
                    type="text"
                    label="Mã thành viên"
                    v-model="this.currentItem.id"
                    :disabled="true"
                    :placeholder="'Mã thành viên'"
                  />
                </div>
                <div class="col-12 mb-3">
                  <FormKit
                    :name="'phone'"
                    class="form-control form-control-sm"
                    :validation="[
                      ['required'],
                      ['matches', /(0[3|5|7|8|9])+([0-9]{8})\b/g],
                    ]"
                    :validation-messages="{
                      matches: 'Số điện thoại không đúng định dạng!',
                      required: 'Số điện thoại không được bỏ chống!',
                    }"
                    :label-class="'required'"
                    :type="'text'"
                    :label="'Số điện thoại'"
                    v-model="this.currentItem.phone"
                    :placeholder="'Số điện thoại '"
                  />
                </div>
                <div class="col-12 mb-3">
                  <FormKit
                    :name="'name'"
                    class="form-control form-control-sm"
                    :label-class="'required'"
                    :validation="[
                      ['required'],
                      ['length', '6,45'],
                      ['matches', /^[a-z0-9 ]*$/],
                    ]"
                    :validation-messages="{
                      matches: 'Tên hiển thị không chứa kí tự đặc biệt!',
                      required: 'Tên hiển thị không được bỏ chống!',
                      length:
                        'Tên hiển thị có độ dài lớn hơn 6 và nhỏ hơn 45 ký tự!',
                    }"
                    :type="'text'"
                    :label="'Tên hiển thị'"
                    v-model="this.currentItem.name"
                    :placeholder="'Tên hiển thị '"
                  />
                </div>
                <div class="col-12 mb-3">
                  <FormKit
                    :name="'email'"
                    class="form-control form-control-sm"
                    :label-class="'required'"
                    validation="*email"
                    :validation-messages="{
                      email: 'Chưa đúng định dạng email!',
                    }"
                    :type="'text'"
                    :label="'Email'"
                    v-model="this.currentItem.email"
                    :placeholder="'Email '"
                  />
                </div>
                <div>
                  <div>
                    <label class="form-label mt-2"> Ảnh đại diện </label>
                    <div class="upload-image">
                      <div class="upload__box d-flex justify-content-between">
                        <div class="upload__btn-box">
                          <label class="upload__btn">
                            <vue-feather type="upload" size="18"></vue-feather>
                            <input
                              type="file"
                              class="upload__inputfile"
                              :multiple="false"
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
                </div>
              </div>

              <button
                style="width: 100%"
                class="btn btn-primary mt-4 mb-5"
                type="submit"
              >
                Cập nhật
              </button>
            </FormKit>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import "vue-good-table-next/dist/vue-good-table-next.css";
export default {
  components: {},
  mounted() {
    this.userData = localStorage.userData
      ? JSON.parse(localStorage.userData)
      : null;
    if (this.userData) {
      this.currentItem = this.userData;
      delete this.currentItem.code;
    }
  },

  methods: {
    changeAvatar(e) {
      if (e.target.files[0].size > 2 * 1024 * 1024) {
        this.$msg("Dữ liệu không đưuọc quá 2MB!!!", "error");
        return;
      }
      var reader = new FileReader();
      var this_ = this;
      reader.onloadend = function () {
        this_.currentItem.avatar = reader.result;
      };
      reader.readAsDataURL(e.target.files[0]);
    },
    removeAvatar() {
      var this_ = this;
      this_.currentItem.avatar = null;
    },
    async handleOk() {
      this.$store.dispatch("updateAccountUser", {
        vm: this,
        params: this.currentItem,
      });
    },
    handleEmitDataUser() {
      this.checkReloadDataUser = !this.checkReloadDataUser;
      this.$emit("handleEmitDataUser", this.checkReloadDataUser);
    },
  },
  data() {
    return {
      checkReloadDataUser: false,
      imgSingle: null,
      currentItem: {},
      userData: null,
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
</style>
