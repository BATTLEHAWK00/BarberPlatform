import React from 'react';
import { RouteObject } from 'react-router-dom';
import pageRoutes from './pages';
import LoginPage from '../pages/login';
import MainLayout from '../components/layout/main';

export interface RouteObjectWithBreadcrumbs extends RouteObject {
  breadcrumb?: string;
}

const routes: RouteObjectWithBreadcrumbs[] = [
  {
    path: '/',
    breadcrumb: '登录',
    element: <LoginPage />,
  },
  {
    path: '/main',
    breadcrumb: '管理主页',
    element: <MainLayout />,
    children: pageRoutes,
  },
];

export default routes;
