import {
  AccountBookOutlined,
  AreaChartOutlined,
  ControlOutlined,
  HomeOutlined,
  MoneyCollectOutlined,
  ProfileOutlined,
  SettingOutlined,
  SolutionOutlined,
  TeamOutlined,
  UserAddOutlined,
  UserOutlined,
} from '@ant-design/icons';
import React from 'react';
import { RouteObjectWithInfo } from '..';
import CreateCustomerOrder from '@pages/main/customerOrder/create';
import MainPage from '@pages/main/index';

const routes: RouteObjectWithInfo[] = [
  {
    path: '',
    menuName: '主页',
    menuIcon: <HomeOutlined />,
    element: <MainPage />,
  },
  {
    path: 'consume',
    menuName: '消费订单',
    menuIcon: <MoneyCollectOutlined />,
    children: [
      {
        path: 'create',
        menuName: '创建订单',
        menuIcon: <i className="ri-file-add-line" />,
        element: <CreateCustomerOrder />,
      },
      {
        path: 'records',
        menuName: '消费记录',
        menuIcon: <i className="ri-history-line" />,
      },
    ],
  },
  {
    path: 'customer',
    menuName: '顾客信息',
    menuIcon: <UserOutlined />,
    children: [
      {
        path: 'detail',
        menuName: '顾客详情',
        menuIcon: <SolutionOutlined />,
      },
      {
        path: 'list',
        menuName: '顾客列表',
        menuIcon: <TeamOutlined />,
      },
      {
        path: 'create',
        menuName: '顾客录入',
        menuIcon: <UserAddOutlined />,
      },
    ],
  },
  {
    path: 'itemManage',
    menuName: '项目管理',
    menuIcon: <ProfileOutlined />,
    children: [
      {
        path: 'barberItem',
        menuName: '消费项目',
        menuIcon: <i className="ri-money-cny-circle-line" />,
      },
      {
        path: 'expenseItem',
        menuName: '支出项目',
        menuIcon: <AccountBookOutlined />,
      },
    ],
  },
  {
    path: 'stats',
    menuName: '统计报表',
    menuIcon: <AreaChartOutlined />,
    children: [
      {
        path: 'daily',
        menuName: '日报表',
        menuIcon: <i className="ri-bar-chart-2-line" />,
      },
      {
        path: 'monthly',
        menuName: '月报表',
        menuIcon: <i className="ri-bar-chart-2-line" />,
      },
      {
        path: 'yearly',
        menuName: '年报表',
        menuIcon: <i className="ri-bar-chart-2-line" />,
      },
    ],
  },
  {
    path: 'settings',
    menuName: '内部配置',
    menuIcon: <ControlOutlined />,
    children: [
      {
        path: 'adminManage',
        menuName: '管理员管理',
        menuIcon: <i className="ri-admin-line" />,
      },
      {
        path: 'system',
        menuName: '系统设置',
        menuIcon: <SettingOutlined />,
      },
    ],
  },
];

export default routes;
