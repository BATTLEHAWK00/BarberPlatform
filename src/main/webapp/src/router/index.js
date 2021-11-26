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
  {
    name: 'firstTime',
    path: '/firstTime',
    component: () => import('@/views/firstTime'),
    hidden: true,
    meta: {
      title: '初始化',
    },
  },
]
export const asyncRoutes = [
  {
    path: '/',
    component: Layout,
    redirect: '/index',
    meta: {
      title: '首页',
      icon: 'home-4-line',
    },
    children: [
      {
        path: 'index',
        name: 'Index',
        component: () => import('@/views/index'),
        meta: {
          title: '首页',
          icon: 'home-4-line',
        },
      },
    ],
  },
  {
    path: '/staff',
    component: Layout,
    redirect: '/staff/staffManage',
    meta: {
      title: '店员管理',
      icon: 'user-2-line',
    },
    children: [
      {
        path: 'staffManage',
        name: 'staffManage',
        component: () => import('@/views/pages/Admin/AdminManage'),
        meta: {
          title: '店员列表',
          icon: 'building-line',
        },
      },
      {
        path: 'staffStatistics',
        name: 'staffStatistics',
        component: () => import('@/views/pages/Admin/AdminManage'),
        meta: {
          title: '店员统计',
          icon: 'pie-chart-line',
        },
      },
    ],
  },
  {
    path: '/user',
    component: Layout,
    redirect: '/user/userManage',
    meta: {
      title: '用户管理',
      icon: 'user-line',
    },
    children: [
      {
        path: 'userManage',
        name: 'userManage',
        component: () => import('@/views/pages/Admin/AdminManage'),
        meta: {
          title: '会员列表',
          icon: 'building-line',
        },
      },
      {
        path: 'userStatistics',
        name: 'userStatistics',
        component: () => import('@/views/pages/Admin/AdminManage'),
        meta: {
          title: '会员统计',
          icon: 'pie-chart-line',
        },
      },
    ],
  },
  {
    path: '/reservation',
    component: Layout,
    redirect: '/reservation/reservationManage',
    meta: {
      title: '预约管理',
      icon: 'user-line',
    },
    children: [
      {
        path: 'reservationManage',
        name: 'reservationManage',
        component: () => import('@/views/pages/Admin/AdminManage'),
        meta: {
          title: '预约列表',
          icon: 'building-line',
        },
      },
      {
        path: 'userStatistics',
        name: 'userStatistics',
        component: () => import('@/views/pages/Admin/AdminManage'),
        meta: {
          title: '会员统计',
          icon: 'pie-chart-line',
        },
      },
    ],
  },
  {
    path: '/message',
    component: Layout,
    redirect: '/message/messageSend',
    meta: {
      title: '消息收发',
      icon: 'user-line',
    },
    children: [
      {
        path: 'messageSend',
        name: 'messageSend',
        component: () => import('@/views/pages/Admin/AdminManage'),
        meta: {
          title: '发送消息',
          icon: 'building-line',
        },
      },
      {
        path: 'userStatistics',
        name: 'userStatistics',
        component: () => import('@/views/pages/Admin/AdminManage'),
        meta: {
          title: '会员统计',
          icon: 'pie-chart-line',
        },
      },
    ],
  },
  {
    path: '/promotion',
    component: Layout,
    redirect: '/promotion/manage',
    meta: {
      title: '促销管理',
      icon: 'user-line',
    },
    children: [
      {
        path: 'manage',
        name: 'promotionManage',
        component: () => import('@/views/pages/Admin/AdminManage'),
        meta: {
          title: '活动列表',
          icon: 'building-line',
        },
      },
    ],
  },
  {
    path: '/statistics',
    component: Layout,
    redirect: '/statistics/daily',
    meta: {
      title: '统计报表',
      icon: 'user-line',
    },
    children: [
      {
        path: 'daily',
        name: 'dailyStatistics',
        component: () => import('@/views/pages/Admin/AdminManage'),
        meta: {
          title: '统计报表(天)',
          icon: 'building-line',
        },
      },
      {
        path: 'daily',
        name: 'dailyStatistics',
        component: () => import('@/views/pages/Admin/AdminManage'),
        meta: {
          title: '统计报表(月)',
          icon: 'building-line',
        },
      },
      {
        path: 'daily',
        name: 'dailyStatistics',
        component: () => import('@/views/pages/Admin/AdminManage'),
        meta: {
          title: '统计报表(年)',
          icon: 'building-line',
        },
      },
    ],
  },
  {
    path: '/system',
    component: Layout,
    redirect: '/system/systemSettings',
    meta: {
      title: '系统选项',
      icon: 'dashboard-line',
    },
    children: [
      {
        path: 'systemSettings',
        name: 'systemSettings',
        component: () => import('@/views/pages/System/SystemSettings'),
        meta: {
          title: '系统设置',
          icon: 'settings-2-line',
        },
      },
      {
        path: 'normalSettings',
        name: 'normalSettings',
        component: () => import('@/views/pages/System/NormalSettings'),
        meta: {
          title: '常规设置',
          icon: 'settings-2-line',
        },
      },
      {
        path: 'loginRecords',
        name: 'loginRecords',
        component: () => import('@/views/pages/Admin/AdminManage'),
        meta: {
          title: '登录日志',
          icon: 'pie-chart-line',
        },
      },
      {
        path: 'securitySettings',
        name: 'securitySettings',
        component: () => import('@/views/pages/Admin/AdminManage'),
        meta: {
          title: '安全设置',
          icon: 'pie-chart-line',
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
