import { PageContainer } from '@ant-design/pro-components';
import { Layout } from 'antd';
import React from 'react';
import { Outlet, useNavigate } from 'react-router-dom';
// import useReactRouterBreadcrumbs from 'use-react-router-breadcrumbs';
import Nav from '../../nav';

import styles from './index.module.less';

const MainLayout: React.FC = () => {
  //   const breadcrumbs = useReactRouterBreadcrumbs();
  const navigate = useNavigate();
  const onBack = () => navigate(-1);
  return (
    <>
      <Layout className={styles.layout}>
        <Layout.Sider className={styles.sider}>
          <div className={styles.logo}>
            <h1>理发店管理系统</h1>
          </div>
          <Nav />
        </Layout.Sider>
        <Layout>
          <Layout.Header></Layout.Header>
          <Layout.Content className={styles.content}>
            <PageContainer
              title="asdas"
              header={{ onBack: history.length !== 1 ? onBack : undefined }}
            >
              <Outlet />
            </PageContainer>
          </Layout.Content>
        </Layout>
      </Layout>
    </>
  );
};
export default MainLayout;
