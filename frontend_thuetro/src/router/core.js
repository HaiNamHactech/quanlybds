export default [

    //auth
    {
      path: "/auth/login",
      name: "login",
      component: () => import("../views/auth/Login.vue"),
      meta: { layout: "auth" },
    }, 
    
  //home
  {
    path: "/",
    name: "trang-chu",
    component: () => import("../views/Home.vue"),
    meta: {
      layout :"full",
      requiresAuth: true,
    },
  },
  //page
  {
    path: "/cho-thue-phong-tro",
    name: "cho-thue-phong-tro",
    component: () => import("../views/ListItem.vue"),
    meta: {
      layout :"full",
      requiresAuth: true,
    },
  },
  {
    path: "/cho-thue-nha",
    name: "cho-thue-nha",
    component: () => import("../views/ListItem.vue"),
    meta: {
      layout :"full",
      requiresAuth: true,
    },
  },
  {
    path: "/cho-thue-can-ho",
    name: "cho-thue-can-ho",
    component: () => import("../views/ListItem.vue"),
    meta: {
      layout :"full",
      requiresAuth: true,
    },
  },
  {
    path: "/cho-thue-mat-bang",
    name: "cho-thue-mat-bang",
    component: () => import("../views/ListItem.vue"),
    meta: {
      layout :"full",
      requiresAuth: true,
    },
  },
  {
    path: "/tim-nguoi-o-ghep",
    name: "tim-nguoi-o-ghep",
    component: () => import("../views/ListItem.vue"),
    meta: {
      layout :"full",
      requiresAuth: true,
    },
  },
  {
    path: "/tin-tuc",
    name: "tin-tuc",
    component: () => import("../views/ListBlog.vue"),
    meta: {
      layout :"full",
      requiresAuth: true,
    },
  },
  {
    path: "/:slugPageBefore/:slugPageAfter",
    name: "news-detail",
    component: () => import("../views/PageDetail.vue"),
    meta: {
      layout :"full",
      requiresAuth: true,
    },
  },
  {
    path: "/tin-tuc/:blogDetail",
    name: "blog-detail",
    component: () => import("../views/BlogDetail.vue"),
    meta: {
      layout :"full",
      pathMapActive: "tin-tuc",
      requiresAuth: true,
    },
  },
  {
    path: "/huong-dan",
    name: "huong-dan",
    component: () => import("../views/Instruct.vue"),
    meta: {
      layout :"full",
      requiresAuth: true,
    },
  },
  // {
  //   path: "/nap-tien",
  //   name: "nap-tien",
  //   component: () => import("../views/Home.vue"),
  //   meta: {
  //     requiresAuth: true,
  //   },
  // },
  {
    path: "/lien-he",
    name: "lien-he",
    component: () => import("../views/Contact.vue"),
    meta: {
      layout :"full",
      requiresAuth: true,
    },
  },
  {
    path: "/bang-gia",
    name: "bang-gia",
    component: () => import("../views/PriceList.vue"),
    meta: {
      layout :"full",
      requiresAuth: true,
    },
  },

];
