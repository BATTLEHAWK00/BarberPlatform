import React from 'react';
import {
  ProForm,
  ProFormDatePicker,
  ProFormRadio,
  ProFormText,
  ProFormTextArea,
} from '@ant-design/pro-components';
import { Rule } from 'antd/lib/form';
import customer from '@/api/customer';

const formRules: Record<string, Rule[]> = {
  username: [{ required: true, message: '请填写姓名' }],
  gender: [{ required: true, message: '请选择性别' }],
  phone: [{ required: true, message: '请填写手机号' }],
  birthDate: [{ required: true, message: '请填写客户生日' }],
  password: [{ required: true, message: '请输入消费密码' }],
};

const CreateCustomerPage: React.FC = () => {
  customer.fuzzySearch('asd').then((data) => {
    console.log(data);
  });

  return (
    <ProForm grid rowProps={{ gutter: 50 }} style={{ width: '70%' }}>
      <ProFormText
        name="username"
        label="姓名"
        placeholder="请输入顾客姓名"
        colProps={{ xxl: 8, xl: 12 }}
        rules={formRules.username}
      />
      <ProFormRadio.Group
        name="gender"
        label="性别"
        colProps={{ xxl: 16, xl: 12 }}
        rules={formRules.gender}
        options={[
          { label: '男', value: '0' },
          { label: '女', value: '1' },
        ]}
      />
      <ProFormText
        name="phone"
        label="手机号"
        placeholder="请输入顾客手机号"
        colProps={{ xxl: 8, xl: 12 }}
        rules={formRules.phone}
      />
      <ProFormDatePicker
        name="birthDate"
        label="生日"
        placeholder="请选择生日"
        colProps={{ xxl: 16, xl: 12 }}
        rules={formRules.birthDate}
      />
      <ProFormText.Password
        name="password"
        label="消费密码"
        allowClear
        placeholder="请输入消费密码"
        colProps={{ span: 24 }}
        rules={formRules.password}
      />
      <ProFormTextArea name="remark" label="备注" colProps={{ span: 24 }} />
    </ProForm>
  );
};

export default CreateCustomerPage;
