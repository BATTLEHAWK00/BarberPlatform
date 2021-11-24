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
      affix: true,
    },
    children: [
      {
        path: 'index',
        name: 'Index',
        component: () => import('@/views/index'),
        meta: {
          title: '首页',
          icon: 'home-4-line',
          affix: true,
        },
      },
    ],
  },
  {
    path: '/staff',
    component: Layout,
    redirect: '/staff',
    meta: {
      title: '管理员管理',
      icon: 'home-4-line',
      affix: true,
    },
    children: [
      {
        path: 'staffManage',
        name: 'staffManage',
        component: () => import('@/views/pages/AdminManage'),
        meta: {
          title: '管理员列表',
          icon: 'home-4-line',
          affix: true,
        },
      },
      {
        path: 'staffStatistics',
        name: 'staffStatistics',
        component: () => import('@/views/pages/AdminManage'),
        meta: {
          title: '管理员列表',
          icon: 'home-4-line',
          affix: true,
        },
      },
    ],
  },
  {
    path: '/error',
    name: 'Error',
    component: Layout,
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
