import { ProFormSelect } from '@ant-design/pro-components';
import React from 'react';

const UserSelect: React.FC = () => {
  return (
    <>
      <ProFormSelect
        label="选择顾客："
        showSearch
        placeholder="请选择顾客（支持顾客ID、姓名、手机号搜索）"
      />
    </>
  );
};

export default UserSelect;
