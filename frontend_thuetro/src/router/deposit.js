export default [
  {
    path: "/nap-tien-vao-tai-khoan",
    name: "nap-tien",
    component: () => import("@/views/Deposit/PostManager.vue"),
    meta: {
      type: "ghost/room",
      layout: "deposit",
      requiresAuth: true,
    },
  },
  {
    path: "/quan-ly-dang-tin",
    name: "quan-ly-dang-tin",
    component: () => import("@/views/Deposit/PostManager.vue"),
    meta: {
      type: "ghost/room",
      layout: "deposit",
      requiresAuth: true,
    },
  },
  {
    path: "/sua-thong-tin-ca-nhan",
    name: "sua-thong-tin-ca-nhan",
    component: () => import("@/views/Deposit/UpdateAccount.vue"),
    meta: {
      layout: "deposit",
      requiresAuth: true,
    },
  },
  {
    path: "/nap-tien-vao-tai-khoan",
    name: "nap-tien-vao-tai-khoan",
    component: () => import("@/views/Deposit/Deposit.vue"),
    meta: {
      layout: "deposit",
      requiresAuth: true,
    },
  },
  {
    path: "/lich-su-nap-tien",
    name: "lich-su-nap-tien",
    component: () => import("@/views/Deposit/PostManager.vue"),
    meta: {
      type: "user/deposit",
      layout: "deposit",
      requiresAuth: true,
    },
  },
  {
    path: "/lich-su-thanh-toan",
    name: "lich-su-thanh-toan",
    component: () => import("@/views/Deposit/PostManager.vue"),
    meta: {
      type: "user/balances",
      layout: "deposit",
      requiresAuth: true,
    },
  },
  // {
  //   path: "/bang-gia-dich-vu",
  //   name: "bang-gia-dich-vu",
  //   component: () => import("@/views/Deposit/PostManager.vue"),
  //   meta: {
  //     layout: "deposit",
  //     requiresAuth: true,
  //   },
  // },
];
