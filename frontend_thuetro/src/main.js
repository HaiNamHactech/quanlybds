import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import VueFeather from "vue-feather";
import "bootstrap/dist/css/bootstrap.min.css";
import VueAnimateOnScroll from "vue3-animate-onscroll";
import sequentialEntrance from "vue3-sequential-entrance";
import "vue3-sequential-entrance/vue-sequential-entrance.css";
import VueNumber from "vue-number-animation";
import { plugin, defaultConfig } from "@formkit/vue";

//Sweetalert
import Swal from 'sweetalert2';
window.Swal = Swal;
import "bootstrap";
// modals
import "../src/assets/admin/css/custom-modal.scss";

const app = createApp(App);

app.component(VueFeather.name, VueFeather);

const showMessage = (msg = "", type = "success") => {
  const toast = window.Swal.mixin({
    toast: true,
    position: "top",
    showConfirmButton: false,
    timer: 3000,
  });
  toast.fire({ icon: type, title: msg, padding: "10px 20px" });
};

app.config.globalProperties.$msg = showMessage;

app.config.globalProperties.$isLoading = false;

app.config.globalProperties.$loading = function (type) {
  if (type) {
    document.getElementById("loading_id").style.display = "block";
  } else {
    document.getElementById("loading_id").style.display = "none";
  }
};

app
  .use(
    plugin,
    defaultConfig({
      config: {
        classes: {
          input: "form-control form-control-sm",
          label: "form-label",
        },
      },
    })
  )
  .use(store)
  .use(router)
  .use(VueFeather.name, VueFeather)
  .use(VueAnimateOnScroll)
  .use(sequentialEntrance)
  .use(VueNumber)
  .mount("#app");
