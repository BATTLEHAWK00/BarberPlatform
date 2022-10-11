import auth from '@/api/auth';
import { LockOutlined, UserOutlined } from '@ant-design/icons';
import { Form, Layout, Input, Button } from 'antd';
import React, { useState } from 'react';
import styles from './index.module.less';

interface LoginData {
  username: string;
  password: string;
}

function LoginPage() {
  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);
  const onFinish = (values: LoginData) => {
    const { username, password } = values;
    console.log(values);
    setLoading(true);
    auth.login(username, password);
  };
  return (
    <>
      <Layout className={styles.loginLayout}>
        <Layout.Header className={styles.header}>
          <h1>登录</h1>
        </Layout.Header>
        <Layout.Content className={styles.loginForm}>
          <Form autoComplete="off" onFinish={onFinish}>
            <Form.Item name="username">
              <Input
                prefix={<UserOutlined className="site-form-item-icon" />}
                placeholder="用户名"
                allowClear
              />
            </Form.Item>
            <Form.Item name="password">
              <Input.Password
                prefix={<LockOutlined className="site-form-item-icon" />}
                placeholder="密码"
                allowClear
                visibilityToggle={false}
              />
            </Form.Item>
            <Form.Item>
              <Button
                type="primary"
                htmlType="submit"
                className={styles.loginButton}
                loading={loading}
              >
                登录
              </Button>
            </Form.Item>
          </Form>
        </Layout.Content>
      </Layout>
    </>
  );
}

export default LoginPage;
