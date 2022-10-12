import { Select } from 'antd';
import React from 'react';

interface IUserSelectProps {
  onChange?: (data: unknown) => void | Promise<void>;
}

const UserSelect: React.FC<IUserSelectProps> = ({ onChange }) => {
  return (
    <>
      <Select
        style={{ width: '100%', marginBottom: '1rem' }}
        showSearch
        onChange={onChange ? (value) => onChange(value) : undefined}
        placeholder="请选择顾客（支持顾客ID、姓名、手机号搜索）"
      />
    </>
  );
};

export default UserSelect;
