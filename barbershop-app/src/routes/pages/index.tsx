import React from 'react';
import { RouteObjectWithBreadcrumbs } from '..';
import MainPage from '../../pages/main/index';

const routes: RouteObjectWithBreadcrumbs[] = [
  {
    path: '',
    breadcrumb: '管理主页',
    element: <MainPage />,
  },
];

export default routes;
