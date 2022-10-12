import Nav from '@/components/nav';
import { MenuFoldOutlined, MenuUnfoldOutlined } from '@ant-design/icons';
import { PageContainer } from '@ant-design/pro-components';
import { Button, Layout } from 'antd';
import React, { useEffect, useState } from 'react';
import { Outlet, useNavigate, useLocation } from 'react-router-dom';
// import useReactRouterBreadcrumbs from 'use-react-router-breadcrumbs';

import styles from './index.module.less';

const MainLayout: React.FC = () => {
  //   const breadcrumbs = useReactRouterBreadcrumbs();
  const navigate = useNavigate();
  const location = useLocation();
  const [canGoBack, setCanGoBack] = useState(false);
  const [menuCollapsed, setMenuCollapsed] = useState(false);

  const onBack = () => navigate(-1);

  useEffect(() => {
    if (history.length === 1 || location.pathname === '/admin') setCanGoBack(false);
    else setCanGoBack(true);
  }, [canGoBack, location]);

  return (
    <>
      <Layout className={styles.layout}>
        <Layout.Sider className={styles.sider} collapsed={menuCollapsed}>
          <div className={styles.logo}>
            <h1>理发店管理系统</h1>
          </div>
          <Nav />
        </Layout.Sider>
        <Layout>
          <Layout.Header>
            <Button
              ghost
              icon={menuCollapsed ? <MenuUnfoldOutlined /> : <MenuFoldOutlined />}
              onClick={() => setMenuCollapsed(!menuCollapsed)}
            />
          </Layout.Header>
          <Layout.Content className={styles.content}>
            <PageContainer title="asdas" header={{ onBack: canGoBack ? onBack : undefined }}>
              <Outlet />
            </PageContainer>
          </Layout.Content>
        </Layout>
      </Layout>
    </>
  );
};
export default MainLayout;
