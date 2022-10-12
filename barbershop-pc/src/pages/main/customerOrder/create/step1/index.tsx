import { ProDescriptions, StepsForm } from '@ant-design/pro-components';
import React from 'react';
import UserSelect from '@components/userSelect';
import { useState } from 'react';
import { Button, Form } from 'antd';
const CreateCustomerOrderStep1: React.FC = () => {
  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  const [loading, setLoading] = useState(false);
  return (
    // eslint-disable-next-line @typescript-eslint/no-empty-function
    <StepsForm.StepForm name="selectUser" title="选择消费用户" onReset={() => {}}>
      <Form.Item label="选择顾客：">
        <UserSelect />
      </Form.Item>
      <ProDescriptions
        title="顾客信息"
        column={4}
        bordered
        loading={loading}
        extra={<Button>重置</Button>}
      >
        <ProDescriptions.Item span={4} valueType="text" label="用户ID" copyable>
          123123
        </ProDescriptions.Item>
        <ProDescriptions.Item span={4} valueType="text" label="姓名" copyable>
          杨旭龙
        </ProDescriptions.Item>
        <ProDescriptions.Item
          span={2}
          valueType="radio"
          label="性别"
          valueEnum={{
            '0': '男',
            '1': '女',
          }}
        >
          0
        </ProDescriptions.Item>
        <ProDescriptions.Item span={2} valueType="date" label="生日">
          2001.4.28
        </ProDescriptions.Item>
        <ProDescriptions.Item span={2} valueType="date" label="上次消费日期">
          2001.4.28
        </ProDescriptions.Item>
        <ProDescriptions.Item span={2} valueType="money" label="上次消费金额">
          200.00
        </ProDescriptions.Item>
        <ProDescriptions.Item span={2} valueType="money" label="总消费">
          200.00
        </ProDescriptions.Item>
        <ProDescriptions.Item span={2} valueType="money" label="余额">
          200.00
        </ProDescriptions.Item>
        <ProDescriptions.Item span={4} valueType="textarea" label="客户备注" copyable>
          备注
        </ProDescriptions.Item>
      </ProDescriptions>
    </StepsForm.StepForm>
  );
};
export default CreateCustomerOrderStep1;
