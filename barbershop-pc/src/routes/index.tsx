import React, { ReactNode } from 'react';
import { RouteObject } from 'react-router-dom';
import pageRoutes from './pages';
import MainLayout from '@/components/layout/main';
import LoginPage from '@/pages/login';

export interface RouteObjectWithInfo extends RouteObject {
  hiddenInMenu?: boolean;
  menuName?: string;
  menuIcon?: ReactNode;
  children?: RouteObjectWithInfo[];
}

const routes: RouteObjectWithInfo[] = [
  {
    path: '/',
    element: <LoginPage />,
  },
  {
    path: '/admin',
    element: <MainLayout />,
    children: pageRoutes,
  },
];

export default routes;
