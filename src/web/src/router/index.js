import { createRouter, createWebHashHistory } from 'vue-router'
import Layout from '@/layout'

export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login'),
    hidden: true,
  },
  {
    path: '/403',
    name: '403',
    component: () => import('@/views/403'),
    hidden: true,
  },
  {
    path: '/404',
    name: '404',
    component: () => import('@/views/404'),
    hidden: true,
  },
]
export const asyncRoutes = [
  {
    path: '/',
    component: Layout,
    meta: {
      title: '概览',
      icon: 'home-4-line',
      affix: true,
    },
    children: [
      {
        path: 'index',
        name: 'Index',
        component: () => import('@/views/index'),
        meta: {
          title: '概览',
          icon: 'home-4-line',
          affix: true,
        },
      },
    ],
  },
  {
    path: '/user',
    component: Layout,
    meta: {
      title: '会员管理',
      icon: 'user-line',
      affix: true,
    },
    children: [
      {
        path: 'register',
        name: 'user-register',
        component: () => import('@/views/usermanage/register'),
        meta: {
          title: '注册会员',
          icon: 'user-add-fill',
          affix: true,
        },
      },
      {
        path: 'list',
        name: 'user-list',
        component: () => import('@/views/usermanage/list'),
        meta: {
          title: '会员列表',
          icon: 'file-user-fill',
          affix: true,
        },
      },
      {
        path: 'storedvalue',
        name: 'user-storedvalue',
        component: () => import('@/views/usermanage/storedvalue'),
        meta: {
          title: '储值管理',
          icon: 'money-cny-circle-fill',
          affix: true,
        },
      },
      {
        path: 'storedvaluelog',
        name: 'user-storedvaluelog',
        component: () => import('@/views/usermanage/storedvaluelog'),
        meta: {
          title: '储值日志',
          icon: 'checkbox-multiple-fill',
          affix: true,
        },
      },
    ],
  },
  {
    path: '/order',
    component: Layout,
    meta: {
      title: '订单管理',
      icon: 'file-list-2-line',
      affix: true,
    },
    children: [
      {
        path: 'create',
        name: 'order-create',
        component: () => import('@/views/ordermanage/create'),
        meta: {
          title: '创建订单',
          icon: 'menu-add-fill',
          affix: true,
        },
      },
      {
        path: 'list',
        name: 'order-list',
        component: () => import('@/views/ordermanage/list'),
        meta: {
          title: '订单列表',
          icon: 'list-check',
          affix: true,
        },
      },
    ],
  },
  {
    path: '/barberitem',
    component: Layout,
    meta: {
      title: '消费项目管理',
      icon: 'stack-line',
      affix: true,
    },
    children: [
      {
        path: 'create',
        name: 'barberitem-create',
        component: () => import('@/views/barberitem/create'),
        meta: {
          title: '创建项目',
          icon: 'menu-add-fill',
          affix: true,
        },
      },
      {
        path: 'list',
        name: 'barberitem-list',
        component: () => import('@/views/barberitem/list'),
        meta: {
          title: '项目列表',
          icon: 'list-check',
          affix: true,
        },
      },
    ],
  },
  {
    path: '/reservation',
    component: Layout,
    meta: {
      title: '预约管理',
      icon: 'calendar-check-line',
      affix: true,
      badgeCount: 1,
    },
    children: [
      {
        path: 'list',
        name: 'reservation-list',
        component: () => import('@/views/reservation'),
        meta: {
          title: '预约列表',
          icon: 'calendar-check-line',
          affix: true,
          badgeCount: 1,
        },
      },
    ],
  },
  {
    path: '/finance',
    component: Layout,
    meta: {
      title: '财务报表',
      icon: 'file-list-3-line',
      affix: true,
    },
    children: [
      {
        path: 'finance',
        name: 'finance-charts',
        component: () => import('@/views/finance/charts'),
        meta: {
          title: '图表概览',
          icon: 'line-chart-line',
          affix: true,
        },
      },
      {
        path: 'list',
        name: 'finance-list',
        component: () => import('@/views/ordermanage/list'),
        meta: {
          title: '报表详情',
          icon: 'database-fill',
          affix: true,
        },
      },
    ],
  },
  {
    path: '/admin',
    component: Layout,
    redirect: 'admin/list',
    meta: {
      title: '管理员',
      icon: 'admin-line',
      affix: true,
    },
    children: [
      {
        path: 'register',
        name: 'admin-register',
        component: () => import('@/views/adminmanage/register'),
        meta: {
          title: '注册管理员',
          icon: 'user-add-fill',
          affix: true,
        },
      },
      {
        path: 'list',
        name: 'admin-list',
        component: () => import('@/views/adminmanage/list'),
        meta: {
          title: '管理员列表',
          icon: 'file-user-fill',
          affix: true,
        },
      },
    ],
  },
  {
    path: '/vab',
    component: Layout,
    redirect: '/vab/table',
    alwaysShow: true,
    hidden: true,
    meta: {
      title: '组件',
      icon: 'apps-line',
    },
    children: [
      {
        path: 'table',
        name: 'Table',
        component: () => import('@/views/vab/table'),
        meta: {
          title: '表格',
          icon: 'table-2',
        },
      },
      {
        path: 'icon',
        name: 'Icon',
        component: () => import('@/views/vab/icon'),
        meta: {
          title: '图标',
          icon: 'remixicon-line',
        },
      },
    ],
  },
  {
    path: '/error',
    name: 'Error',
    component: Layout,
    hidden: true,
    redirect: '/error/403',
    meta: {
      title: '错误页',
      icon: 'error-warning-line',
    },
    children: [
      {
        path: '403',
        name: 'Error403',
        component: () => import('@/views/403'),
        meta: {
          title: '403',
          icon: 'error-warning-line',
        },
      },
      {
        path: '404',
        name: 'Error404',
        component: () => import('@/views/404'),
        meta: {
          title: '404',
          icon: 'error-warning-line',
        },
      },
    ],
  },
  {
    path: '/*',
    redirect: '/404',
    hidden: true,
  },
]
const router = createRouter({
  history: createWebHashHistory(),
  routes: constantRoutes,
})

export default router
