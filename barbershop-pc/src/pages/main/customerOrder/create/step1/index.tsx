import { ProDescriptions, StepsForm } from '@ant-design/pro-components';
import React from 'react';
import UserSelect from '@components/userSelect';
const CreateCustomerOrderStep1: React.FC = () => {
  return (
    // eslint-disable-next-line @typescript-eslint/no-empty-function
    <StepsForm.StepForm name="selectUser" title="选择消费用户" onReset={() => {}}>
      <UserSelect />
      <ProDescriptions title="顾客信息" column={4} bordered>
        <ProDescriptions.Item span={4} valueType="digit" label="用户ID">
          121
        </ProDescriptions.Item>
        <ProDescriptions.Item span={4} valueType="text" label="姓名">
          杨旭龙
        </ProDescriptions.Item>
        <ProDescriptions.Item span={2} valueType="radio" label="性别">
          男
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
        <ProDescriptions.Item span={4} valueType="money" label="余额">
          200.00
        </ProDescriptions.Item>
        <ProDescriptions.Item span={4} valueType="money" label="总消费">
          200.00
        </ProDescriptions.Item>
        <ProDescriptions.Item span={4} valueType="textarea" label="客户备注">
          200.00
        </ProDescriptions.Item>
      </ProDescriptions>
    </StepsForm.StepForm>
  );
};
export default CreateCustomerOrderStep1;
